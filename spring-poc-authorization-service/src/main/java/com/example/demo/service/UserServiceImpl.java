package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.dto.ResponseHeader;
import com.example.demo.dto.AppUserDTO;
import com.example.demo.dto.UserRequest.UserRequestBody;
import com.example.demo.dto.UserResponse.UserResponseBody;
import com.example.demo.dto.UserResponse.UserResponseHolder;
import com.example.demo.exception.ApiException;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.model.AppUser;
import com.google.common.collect.Lists;

/**
 * @author __ArunPrakash__
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private AppUserMapper appUserMapper;
	
	@Override
	public UserResponseHolder getAllUsers() {
		List<AppUser> appUsers = Lists.newArrayList(appUserRepository.findAll());
		List<AppUserDTO> appUserList = appUsers.stream().map(appUserMapper::toDTO).collect(Collectors.toList());
		return UserResponseHolder.builder()
				.header(ResponseHeader.builder().responseId(null).build())
				.body(UserResponseBody.builder().appUsers(appUserList).build())
			.build();
	}

	@Override
	public UserResponseHolder addUser(UserRequestBody body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseHolder updateUser(UserRequestBody body) {
		// TODO Auto-generated method stub
		return null;
	}

}
