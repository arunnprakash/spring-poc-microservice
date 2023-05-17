/**
 * 
 */
package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;

import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
public interface LoginService {

	Mono<LoginResponse> doLogin(LoginRequest loginRequest);

}
