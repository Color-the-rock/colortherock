package org.anotherclass.colortherock.domain.videoboard;

import org.anotherclass.colortherock.domain.videoboard.response.VideoBoardSummaryDto;
import org.anotherclass.colortherock.domain.videoboard.service.VideoBoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VideoBoardServiceTest {

    @Autowired
    VideoBoardService videoBoardService;

    @Test
    public void 목록조회() throws Exception {

        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").descending());
        List<VideoBoardSummaryDto> successVideoList = videoBoardService.getSuccessVideoList(pageable);
        for(int i = 0; i < successVideoList.size(); i++) {
            System.out.println(successVideoList.get(i));
        }
    }

}
