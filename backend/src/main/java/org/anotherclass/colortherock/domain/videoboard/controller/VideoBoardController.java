package org.anotherclass.colortherock.domain.videoboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryDto;
import org.anotherclass.colortherock.domain.videoboard.service.VideoBoardService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoBoardController {

    private final VideoBoardService videoBoardService;

    @GetMapping("/board")
    public ResponseEntity<List<VideoBoardSummaryDto>> getVideoBoardList
            (@PageableDefault(size=15, sort="videoBoardId", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok()
                .body(videoBoardService.getSuccessVideoList(pageable));
    }

}
