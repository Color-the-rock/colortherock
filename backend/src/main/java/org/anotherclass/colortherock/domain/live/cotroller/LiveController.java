package org.anotherclass.colortherock.domain.live.cotroller;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.live.service.LiveService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LiveController {

    private final LiveService liveService;
}
