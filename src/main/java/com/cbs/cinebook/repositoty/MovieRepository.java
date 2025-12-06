package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
