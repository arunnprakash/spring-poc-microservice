package com.example.demo.dao;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.example.demo.model.BaseModel;

/**
 * @author __ArunPrakash__
 *
 */
@NoRepositoryBean
public interface BaseRepository<M extends BaseModel> extends ReactiveCrudRepository<M, Long>, ReactiveSortingRepository<M, Long> {

}
