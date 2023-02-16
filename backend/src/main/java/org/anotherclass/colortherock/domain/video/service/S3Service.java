package org.anotherclass.colortherock.domain.video.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Log4j2
@Service
public class S3Service {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${CLOUDFRONT_URL}")
    private String cloudFrontUrl;

    /**
     * 빈 주입이 끝나면 s3Client를 생성한다.
     */
    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();

        log.log(Level.INFO, "s3Client 생성완료");
    }

    /**
     * 파일을 s3에다 업로드 한다.
     *
     * @param file      저장할 영상 파일
     * @param videoName 파일 이름
     * @return 저장에 성공하면 url을 돌려받는다.
     */
    public String upload(MultipartFile file, String videoName) {
        try (InputStream inputStream = file.getInputStream()) {
            s3Client.putObject(new PutObjectRequest(bucket, videoName, inputStream, null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return cloudFrontUrl + videoName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 영상 썸네일을 s3에 업로드 한다.
     *
     * @param videoFile     영상 파일
     * @param thumbnailName 저장할 썸네일 제목
     * @return 썸네일 url
     */
    public String uploadThumbnail(MultipartFile videoFile, String thumbnailName) {
        File file = convertMultipartFileToFile(videoFile);
        String name = file.getName();
        log.info("name : {}", name);
        int index = name.lastIndexOf(".");
        String extension = name.substring(index + 1).toLowerCase();
        String newName = name.substring(0, index + 1) + extension;
        log.info("new name : {}", newName);
        File dest = new File(newName);
        boolean b = file.renameTo(dest);
        log.info("is rename {}", b);
        log.info(file.getName());
        log.info(dest.getName());
        String thumbnailURL = getThumbnailURL(thumbnailName, dest);
        try {
            Files.delete(Path.of(dest.getPath()));
        } catch (IOException e) {
            log.info("파일이 삭제되지 않았습니다.");
            throw new RuntimeException(e);
        }


        return thumbnailURL;
    }

    /**
     * 오픈비두 서버에서 영상을 s3에 업로드 한다.
     *
     * @param dir       저장 되어 있는 파일 경로
     * @param videoName 저장할 영상 이름
     * @return 저장된 cloudfront url
     */
    public String uploadFromOV(String dir, String videoName) {

        Path filePath = Paths.get(dir);
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            s3Client.putObject(new PutObjectRequest(bucket, videoName, inputStream, null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return cloudFrontUrl + videoName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 오픈비두 서버에서 영상을 통해 썸네일을 업로드 한다.
     *
     * @param dir           파일 경로
     * @param thumbnailName 썸네일 제목
     * @return 생성된 썸네일 url
     */
    public String uploadThumbnailFromOV(String dir, String thumbnailName) {
        File file = Paths.get(dir).toFile();
        return getThumbnailURL(thumbnailName, file);
    }

    /**
     * s3에서 파일 삭제
     *
     * @param videoName 삭제할 파일 제목
     */
    public void deleteFile(String videoName) {
        s3Client.deleteObject(bucket, videoName);
    }


    /**
     * 영상에서 썸네일을 가져와서 s3에 저장한다.
     *
     * @param thumbnailName 썸네일 제목
     * @param file          파일 경로
     * @return 썸네일이 저장된 cloudfront url
     */
    private String getThumbnailURL(String thumbnailName, File file) {
        // Get image from video
        try (FileChannelWrapper fileChannelWrapper = NIOUtils.readableChannel(file);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            FrameGrab grab = FrameGrab.createFrameGrab(fileChannelWrapper);
            Picture picture = grab.seekToSecondPrecise(1.0).getNativeFrame();
            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
            // Convert the image to a JPEG and write it to a ByteArrayOutputStream

            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            BufferedImage outputImage = new BufferedImage(height, width, bufferedImage.getType());

            Graphics2D g2d = outputImage.createGraphics();
            AffineTransform at = new AffineTransform();
            at.translate(height, 0);
            at.rotate(Math.PI / 2);
            g2d.setTransform(at);
            g2d.drawImage(bufferedImage, 0, 0, null);
            g2d.dispose();

            ImageIO.write(outputImage, "JPEG", baos);
            baos.flush();
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            // Upload the object to S3
            s3Client.putObject(new PutObjectRequest(bucket, thumbnailName, is, null));
            return cloudFrontUrl + thumbnailName;
        } catch (JCodecException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * MultiparFile -> File 변환
     *
     * @param file 변환할 MultipartFile
     * @return 변환된 file
     */
    private File convertMultipartFileToFile(MultipartFile file) {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return convFile;
    }
}
