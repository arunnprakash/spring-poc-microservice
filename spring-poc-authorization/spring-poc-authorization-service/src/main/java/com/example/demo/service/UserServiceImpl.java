package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.dto.ResponseHeader;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.AppUserDTO;
import com.example.demo.dto.UserRequest.UserRequestBody;
import com.example.demo.dto.UserResponse.UserResponseBody;
import com.example.demo.dto.UserResponse.UserResponseHolder;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.model.AppUser;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private AppUserMapper appUserMapper;
	
	@Override
	public Mono<UserResponse> getAllUsers() {
		return appUserRepository.findAll().map(appUserMapper::toDTO).collectList().flatMap(list -> {
			return Mono.fromCallable(() -> {
				return UserResponse
						.builder().response(UserResponseHolder.builder()
							.header(ResponseHeader.builder().responseId(null).build())
							.body(UserResponseBody.builder().appUsers(list).build())
						.build())
					.build();
			});
		});
	}

	@Override
	public Mono<UserResponse> addUser(UserRequest userRequest) {
		return appUserRepository.save(appUserMapper.toModel(userRequest.getRequest().getBody().getUser()))
				.map(saveAppUser -> {
					log.info("AppUser Saved with id {}", saveAppUser.getId());
					return UserResponse
							.builder().response(UserResponseHolder.builder()
								.header(ResponseHeader.builder().responseId(null).build())
								.body(UserResponseBody.builder().build())
							.build())
						.build();
				});
	}

	@Override
	public Mono<UserResponse> updateUser(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
