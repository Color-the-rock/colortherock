package org.anotherclass.colortherock.domain.videoboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videoboard.request.VideoBoardSearchRequest;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryResponse;
import org.anotherclass.colortherock.domain.videoboard.service.VideoBoardService;
import org.anotherclass.colortherock.global.common.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/video")
public class VideoBoardController {

    private final VideoBoardService videoBoardService;

    /**
     * 완등 영상 전체 리스트 조회
     *
     * @param condition 현재 페이지의 마지막 아이디, 암장, 색상 검색 조건을 담고 있는 객체
     * @param pageable  Pageable 객체 (컨트롤러에서 생성)
     */
    @GetMapping("/board")
    public BaseResponse<List<VideoBoardSummaryResponse>> getVideoTemp
    (VideoBoardSearchRequest condition, @PageableDefault(size = 16, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<VideoBoardSummaryResponse> successVideoList = videoBoardService.getSuccessVideos(condition, pageable);
        return new BaseResponse<>(successVideoList);
    }


}
