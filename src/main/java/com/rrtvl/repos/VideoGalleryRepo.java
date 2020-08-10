package com.rrtvl.repos;

import com.rrtvl.domain.VideoGallery;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VideoGalleryRepo extends PagingAndSortingRepository<VideoGallery, Long> {
}
