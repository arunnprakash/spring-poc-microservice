/**
 * 
 */
package com.example.demo.service;

import com.example.demo.dto.LoginRequest.LoginRequestBody;
import com.example.demo.dto.LoginResponse.LoginResponseHolder;

/**
 * @author __ArunPrakash__
 *
 */
public interface LoginService {

	LoginResponseHolder doLogin(LoginRequestBody body);

}
