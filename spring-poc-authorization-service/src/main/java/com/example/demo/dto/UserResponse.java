package com.example.demo.dto;

import java.util.List;

import io.github.mhagnumdw.beaninfogenerator.GenerateBeanMetaInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author __ArunPrakash__
 *
 */
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true)
public class UserResponse extends BaseResponse<UserResponse.UserResponseHolder> {

	@Getter
	@Setter
	@SuperBuilder(toBuilder = true)
	@NoArgsConstructor(access = AccessLevel.PUBLIC)
	@ToString(callSuper = true)
	public static class UserResponseHolder extends Response<UserResponse.UserResponseBody> {
	}

	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor(access = AccessLevel.PUBLIC)
	@AllArgsConstructor(access = AccessLevel.PUBLIC)
	@GenerateBeanMetaInfo
	public static class UserResponseBody {
		@Schema(description = "App User List")
		private List<AppUserDTO> appUsers;
	}

}
