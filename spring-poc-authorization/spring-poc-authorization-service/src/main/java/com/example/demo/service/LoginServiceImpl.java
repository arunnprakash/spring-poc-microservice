package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.dto.LoginRequest.LoginRequestBody;
import com.example.demo.dto.LoginResponse.LoginResponseBody;
import com.example.demo.dto.LoginResponse.LoginResponseHolder;
import com.example.demo.dto.ResponseHeader;
import com.example.demo.exception.ApiException;
import com.example.demo.jwt.TokenManager;
import com.example.demo.model.AppUser;

/**
 * @author __ArunPrakash__
 *
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private TokenManager tokenManager;

	@Override
	public LoginResponseHolder doLogin(LoginRequestBody body) {
		AppUser appUser = appUserRepository.findByUserNameAndPassword(body.getUsername(), body.getPassword());
		if (appUser != null) {
			String jwtToken = tokenManager.generateJwtToken(appUser.getUserName());
			return LoginResponseHolder.builder()
					.header(ResponseHeader.builder().responseId(null).build())
					.body(LoginResponseBody.builder().token(jwtToken).build())
				.build();
		} else {
			throw ApiException.resourceNotFound();
		}

	}

}
