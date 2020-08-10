package com.rrtvl.repos;

import com.rrtvl.domain.PhotoGallery;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhotoGalleryRepo extends PagingAndSortingRepository<PhotoGallery, Long> {
}
