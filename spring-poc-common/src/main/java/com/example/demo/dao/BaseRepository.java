package com.example.demo.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.BaseModel;

/**
 * @author __ArunPrakash__
 *
 */
@NoRepositoryBean
public interface BaseRepository<M extends BaseModel> extends CrudRepository<M, Long>, PagingAndSortingRepository<M, Long> {

}
