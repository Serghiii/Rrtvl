package com.rrtvl.repos;

import com.rrtvl.domain.Visits;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface VisitsRepo extends PagingAndSortingRepository<Visits, Long> {
    @Query("select v from Visits v where v.date = DATE(:date)")
    Iterable<Visits> findByDate(@Param("date") Date date);
}
