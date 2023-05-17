package com.example.demo.dao;

import com.example.demo.model.AppUser;

import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
public interface AppUserRepository extends BaseRepository<AppUser> {
	public Mono<AppUser> findByUserNameAndPassword(String userName, String password);
}