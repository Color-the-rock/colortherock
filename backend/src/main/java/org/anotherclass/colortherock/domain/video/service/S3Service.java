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
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

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

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();

        log.log(Level.INFO, "s3Client 생성완료");
    }

    public String upload(MultipartFile file, String videoName) throws IOException {
        s3Client.putObject(new PutObjectRequest(bucket, videoName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, videoName).toString();
    }

    public void deleteFile(String videoName) {
        s3Client.deleteObject(bucket, videoName);
    }

    public String uploadThumbnail(MultipartFile videoFile, String thumbnailName) throws IOException, JCodecException {

        // Convert the MultipartFile to a File
        File file = convertMultipartFileToFile(videoFile);
        // Get image from video
        FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
        Picture picture = grab.seekToSecondPrecise(1.0).getNativeFrame();
        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);

        // Convert the image to a JPEG and write it to a ByteArrayOutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "JPEG", baos);
        baos.flush();
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        baos.close();

        // Upload the object to S3
        s3Client.putObject(new PutObjectRequest(bucket, thumbnailName, is, null));
        return s3Client.getUrl(bucket, thumbnailName).toString();
    }

    private static File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        //file.transferTo(convFile);
        return convFile;
    }


}
