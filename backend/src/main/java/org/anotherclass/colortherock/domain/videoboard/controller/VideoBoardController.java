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

    @GetMapping("/board")
    public BaseResponse<List<VideoBoardSummaryResponse>> getVideoTemp
            (@PageableDefault(size=16, sort="id", direction = Sort.Direction.DESC) Pageable pageable, VideoBoardSearchRequest cond) {
        List<VideoBoardSummaryResponse> successVideoList = videoBoardService.getSuccessVideos(cond, pageable);
        return new BaseResponse<>(successVideoList);
    }


}
