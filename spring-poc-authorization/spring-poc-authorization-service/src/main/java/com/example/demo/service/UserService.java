/**
 * 
 */
package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;

import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
public interface UserService {

	Mono<UserResponse> getAllUsers();

	Mono<UserResponse> addUser(UserRequest userRequest);

	Mono<UserResponse> updateUser(UserRequest userRequest);

}
