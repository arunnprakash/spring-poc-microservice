package com.example.demo.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.dto.LoginRequest.LoginRequestBody;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.LoginResponse.LoginResponseBody;
import com.example.demo.dto.LoginResponse.LoginResponseHolder;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.ResponseHeader;
import com.example.demo.exception.ApiException;
import com.example.demo.jwt.TokenManager;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private TokenManager tokenManager;

	@Override
	public Mono<LoginResponse> doLogin(LoginRequest loginRequest) {
		LoginRequestBody body = loginRequest.getRequest().getBody();
		return appUserRepository.findByUserNameAndPassword(body.getUsername(), body.getPassword())
			.map(appUser -> {
				try {
					String jwtToken = tokenManager.generateJwtToken(appUser.getUserName());
					return LoginResponse
							.builder()
								.response(LoginResponseHolder.builder()
									.header(ResponseHeader.builder().responseId(null).build())
									.body(LoginResponseBody.builder().token(jwtToken).build())
								.build())
							.build();	
				} catch (Exception e) {
					log.error("Error while login", e);
					return LoginResponse
							.builder()
								.response(LoginResponseHolder.builder()
									.header(ResponseHeader.builder().responseId(null).build())
									.body(LoginResponseBody.builder().build())
								.build())
							.build();
				}
				
			});//Mono.error(ApiException::resourceNotFound);
	}

}
