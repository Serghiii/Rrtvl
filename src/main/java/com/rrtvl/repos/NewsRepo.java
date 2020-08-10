package com.rrtvl.repos;

import com.rrtvl.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsRepo extends PagingAndSortingRepository<News, Long> {
    Page<News> findAllByTitleContainingOrDescriptionContainingOrContentContainingIgnoreCaseOrderByDateDesc(String filter, String filter2, String filter3, Pageable pageable);
}