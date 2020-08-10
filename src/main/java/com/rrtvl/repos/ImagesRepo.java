package com.rrtvl.repos;

import com.rrtvl.domain.Images;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImagesRepo extends PagingAndSortingRepository<Images, Long> {
    @Override
    Iterable<Images> findAllById(Iterable<Long> iterable);
}
