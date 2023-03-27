package com.example.demo.dao;

import com.example.demo.model.AppUser;

/**
 * @author __ArunPrakash__
 *
 */
public interface AppUserRepository extends BaseRepository<AppUser> {
	public AppUser findByUserNameAndPassword(String userName, String password);
}