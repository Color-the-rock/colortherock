package org.anotherclass.colortherock.domain.memberrecord.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.member.repository.MemberRepository;
import org.anotherclass.colortherock.domain.memberrecord.entity.MemberRecord;
import org.anotherclass.colortherock.domain.memberrecord.exception.UserNotFoundException;
import org.anotherclass.colortherock.domain.memberrecord.repository.RecordRepository;
import org.anotherclass.colortherock.domain.memberrecord.response.*;
import org.anotherclass.colortherock.domain.video.dto.DateLevelDto;
import org.anotherclass.colortherock.domain.video.exception.VideoNotFoundException;
import org.anotherclass.colortherock.domain.video.repository.VideoReadRepository;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.video.entity.Video;
import org.anotherclass.colortherock.domain.video.request.MyVideoRequest;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final VideoRepository videoRepository;
    private final RecordRepository recordRepository;
    private final VideoReadRepository videoReadRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<LevelStatResponse> getColorRecords(Member member) {
        List<LevelStatResponse> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new LevelStatResponse(i + 1));
        }
        List<Video> videos = videoRepository.findAllByMember(member);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            LevelStatResponse dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }

    @Transactional(readOnly = true)
    public List<LevelStatResponse> getDateRecords(Member member, LocalDate videoDate) {
        List<LevelStatResponse> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(new LevelStatResponse(i + 1));
        }
        List<Video> videos = videoRepository.findAllByMemberAndShootingDate(member, videoDate);
        videos.forEach(video -> {
            Integer videoLevel = video.getLevel();
            LevelStatResponse dto = list.get(videoLevel - 1);
            dto.totalIncrement();
            if(video.getIsSuccess()) dto.successIncrement();
        });
        return list;
    }

    @Transactional(readOnly = true)
    public TotalStatResponse getTotalRecords(Member member) {
        MemberRecord memberRecord = recordRepository.findByMember(member);
        Integer visitCount = videoReadRepository.searchTotalVisit(member);
        return TotalStatResponse.builder()
                .visitCount(visitCount)
                .videoCount(memberRecord.getVideoCount())
                .successCount(memberRecord.getSuccessCount()).build();
    }

    @Transactional(readOnly = true)
    public List<VideoListResponse> getMyVideos(Member member, MyVideoRequest request) {
        Pageable pageable = Pageable.ofSize(15);

        Slice<Video> slices = videoReadRepository.searchBySlice(pageable, request, member);

        if(slices.isEmpty()) return new ArrayList<>();

        return slices.toList().stream()
                .map(video ->
                    VideoListResponse.builder()
                            .thumbnailURL(video.getThumbnailURL())
                            .id(video.getId())
                            .color(video.getColor())
                            .gymName(video.getGymName())
                            .level(video.getLevel()).build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VideoDetailResponse getVideoDetail(Long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(GlobalErrorCode.VIDEO_NOT_FOUND));

        return VideoDetailResponse.builder()
                .s3URL(video.getS3URL())
                .isSuccess(video.getIsSuccess())
                .shootingDate(video.getShootingDate().toString())
                .level(video.getLevel())
                .color(video.getColor())
                .gymName(video.getGymName())
                .id(video.getId()).build();
    }

    @Transactional
    public void addVideoCount(Member member, Boolean isSuccess) {
        MemberRecord record = recordRepository.findByMember(member);
        record.addVideoCount();
        if(isSuccess) {
            record.addSuccessCount();
        }
        recordRepository.save(record);
    }

    @Transactional
    public void subVideoCount(Member member, Boolean isSuccess) {
        MemberRecord record = recordRepository.findByMember(member);
        record.subVideoCount();
        if(isSuccess) {
            record.subSuccessCount();
        }
        recordRepository.save(record);
    }

    @Transactional
    public void saveNewRecord(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);
        MemberRecord memberRecord = new MemberRecord(member);
        recordRepository.save(memberRecord);
    }

    @Transactional(readOnly = true)
    public VisitResponse getVisitList(Member member) {
        List<VisitListDto> visitListResponse = videoReadRepository.searchVisitCount(member);
        Long totalCount = 0L;
        for (VisitListDto dto:
             visitListResponse) {
            totalCount += dto.getCount();
        }
        visitListResponse.sort((r1, r2) -> (int)(r2.getCount() - r1.getCount()));
        return VisitResponse.builder().totalCount(totalCount).data(visitListResponse).build();
    }

    @Transactional(readOnly = true)
    public List<DailyColorResponse> getCalendarColor(Member member, String yearMonth) {
        YearMonth month = YearMonth.parse(yearMonth);
        LocalDate firstDate = month.atDay(1);
        LocalDate lastDate = month.atEndOfMonth();
        List<DailyColorResponse> dailyColors = new ArrayList<>();
        LocalDate currentDate = null;
        Set<Integer> levels = new HashSet<>();
        List<DateLevelDto> dtos = videoReadRepository.searchDailyColor(member, firstDate, lastDate);
        for (DateLevelDto dto : dtos) {
            if(currentDate == null) currentDate = dto.getDate();
            if(currentDate != null && !dto.getDate().isEqual(currentDate)) {
                dailyColors.add(dtoToResponse(currentDate, levels));
                levels = new HashSet<>();
                currentDate = dto.getDate();
            }
            if(levels.size() == 3) continue;
            else levels.add(dto.getLevel());
        }
        dailyColors.add(dtoToResponse(currentDate, levels));
        return dailyColors;
    }

    public DailyColorResponse dtoToResponse(LocalDate currentDate, Set<Integer> levels) {
        List<String> colors = new ArrayList<>();
        levels.forEach(level -> colors.add(ColorCode.getColor(level)));
        return DailyColorResponse.builder()
                .date(currentDate)
                .colors(colors).build();
    }
}
