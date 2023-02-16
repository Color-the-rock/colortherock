package org.anotherclass.colortherock.domain.report.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.report.entity.Report;
import org.anotherclass.colortherock.domain.report.exception.ReportOneselfException;
import org.anotherclass.colortherock.domain.report.repository.ReportReadRepository;
import org.anotherclass.colortherock.domain.report.repository.ReportRepository;
import org.anotherclass.colortherock.domain.report.request.PostReportRequest;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Profile("local")
public class LocalReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final ReportReadRepository reportReadRepository;
    private final VideoBoardRepository videoBoardRepository;

    public void reportPost(Member member, PostReportRequest request) {
        VideoBoard videoBoard = videoBoardRepository.findById(request.getVideoBoardId())
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        // 해당 게시글의 작성자가 member일 경우 오류 발생
        if (videoBoard.getMember().getId().equals(member.getId())) {
            throw new ReportOneselfException();
        }
        // 모든 조건을 통과할 경우 새로운 신고 객체를 생성하여 저장
        Report newReport = Report.builder()
                .categoryName(request.getCategory())
                .videoBoard(videoBoard)
                .member(member)
                .build();
        reportRepository.save(newReport);
        if (checkReportNum(request.getVideoBoardId()) >= 5) {
            videoBoard.changeToHidden();
            videoBoardRepository.save(videoBoard);
        }
    }

    // 해당 게시글이 몇 명의 유저로부터 신고 당했는지 확인
    public Long checkReportNum(Long videoBoardId) {
        return reportReadRepository.countReport(videoBoardId);
    }
}
