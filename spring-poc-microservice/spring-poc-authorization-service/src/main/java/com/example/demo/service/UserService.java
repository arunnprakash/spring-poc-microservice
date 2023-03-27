/**
 * 
 */
package com.example.demo.service;

import com.example.demo.dto.UserRequest.UserRequestBody;
import com.example.demo.dto.UserResponse.UserResponseHolder;

/**
 * @author __ArunPrakash__
 *
 */
public interface UserService {

	UserResponseHolder getAllUsers();

	UserResponseHolder addUser(UserRequestBody body);

	UserResponseHolder updateUser(UserRequestBody body);

}
