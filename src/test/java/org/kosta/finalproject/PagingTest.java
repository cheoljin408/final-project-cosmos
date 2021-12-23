package org.kosta.finalproject;

import org.junit.jupiter.api.Test;
import org.kosta.finalproject.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PagingTest {

    private final PagingService pagingService;

    @Autowired
    public PagingTest(PagingService pagingService) {
        this.pagingService = pagingService;
    }

    @Test
    void getTotalCountTest() {
        // given
        int count = 13;

        // when
        int totalCount = pagingService.getTotalCountOfStudyList();

        // then
        assertThat(count).isEqualTo(totalCount);
    }
}
