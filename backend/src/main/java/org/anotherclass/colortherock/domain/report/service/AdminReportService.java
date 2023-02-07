package org.anotherclass.colortherock.domain.report.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.report.entity.Report;
import org.anotherclass.colortherock.domain.report.repository.ReportRepository;
import org.anotherclass.colortherock.domain.report.response.AdminReportDetailResponse;
import org.anotherclass.colortherock.domain.report.response.AdminReportedPostResponse;
import org.anotherclass.colortherock.domain.video.repository.VideoRepository;
import org.anotherclass.colortherock.domain.videoboard.entity.VideoBoard;
import org.anotherclass.colortherock.domain.videoboard.exception.PostNotFoundException;
import org.anotherclass.colortherock.domain.videoboard.repository.VideoBoardRepository;
import org.anotherclass.colortherock.global.error.GlobalErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminReportService {

    private final ReportRepository reportRepository;
    private final VideoBoardRepository videoBoardRepository;
    private final VideoRepository videoRepository;

    // 숨김처리된 영상 불러오기
    public List<AdminReportedPostResponse> getReportedVideoBoard() {
        List<VideoBoard> videoBoardList = videoBoardRepository.findAllByIsHiddenTrue();
        return videoBoardList.stream()
                .map(vb -> AdminReportedPostResponse.builder()
                        .videoBoardId(vb.getId())
                        .title(vb.getTitle())
                        .build()).collect(Collectors.toList());
    }

    // 영상게시글의 신고 내용 불러오기
    public List<AdminReportDetailResponse> getReportDetail(Long videoBoardId) {
        List<Report> reportList = reportRepository.findAllByVideoBoardId(videoBoardId);
        return reportList.stream()
                .map(rp -> AdminReportDetailResponse.builder()
                        .memberId(rp.getMember().getId())
                        .category(rp.getCategory())
                        .build()).collect(Collectors.toList());
    }

    // 영상게시글 숨김 처리 해제 및 신고 내용 삭제
    public void cancelHiddenStatus(Long videoBoardId) {
        VideoBoard videoBoard = videoBoardRepository.findById(videoBoardId)
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        videoBoard.changeToPublic();
        reportRepository.deleteAllByVideoBoardId(videoBoardId);
    }

    // 영상게시글 삭제(영상 자체를 삭제)
    public void deleteReportedVideo(Long videoBoardId) {
        VideoBoard videoBoard = videoBoardRepository.findById(videoBoardId)
                .orElseThrow(() -> new PostNotFoundException(GlobalErrorCode.POST_NOT_FOUND));
        videoRepository.delete(videoBoard.getVideo());
        videoBoardRepository.delete(videoBoard);
    }
}