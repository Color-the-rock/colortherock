package org.anotherclass.colortherock.domain.live.service;

import lombok.RequiredArgsConstructor;
import org.anotherclass.colortherock.domain.live.repository.LiveRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final LiveRepository liveRepository;

}
