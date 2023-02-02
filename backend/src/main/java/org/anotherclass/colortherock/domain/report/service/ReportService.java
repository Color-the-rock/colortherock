package org.anotherclass.colortherock.domain.report.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.member.entity.Member;
import org.anotherclass.colortherock.domain.report.entity.Report;
import org.anotherclass.colortherock.domain.report.repository.ReportRepository;
import org.anotherclass.colortherock.domain.report.request.PostReportRequest;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final VideoBoardRepository videoBoardRepository;
    public void reportPost(Member member, PostReportRequest request) {
        VideoBoard videoBoard = videoBoardRepository.findById(request.getVideoBoardId())
                .orElseThrow(()-> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        Report newReport = Report.builder()
                .categoryName(request.getCategory())
                .videoBoard(videoBoard)
                .member(member)
                .build();
        reportRepository.save(newReport);
        if (checkReportNum(request.getVideoBoardId())) {
            videoBoard.changeToHidden();
        }
    }

    // 해당 게시글이 몇 명의 유저로부터 신고 당했는지 확인
    private Boolean checkReportNum(Long videoBoardId) {
        Long reportCnt = reportRepository.countReport(videoBoardId);
        if(reportCnt >= 5) {
            return true;
        }
        return false;
    }
}
