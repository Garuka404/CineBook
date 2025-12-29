package com.cbs.cinebook.repositoty;

import com.cbs.cinebook.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    boolean existsByName(String name);
}
