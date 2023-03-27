package com.example.demo.dto;

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
public class LoginRequest extends BaseRequest<LoginRequest.LoginRequestHolder> {

	@Getter
	@Setter
	@SuperBuilder(toBuilder = true)
	@NoArgsConstructor(access = AccessLevel.PUBLIC)
	@ToString(callSuper = true)
	public static class LoginRequestHolder extends Request<LoginRequest.LoginRequestBody> {
	}

	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor(access = AccessLevel.PUBLIC)
	@AllArgsConstructor(access = AccessLevel.PUBLIC)
	@GenerateBeanMetaInfo
	public static class LoginRequestBody {
		@Schema(description = "UserName", minLength = 4, maxLength = 20)
		private String username;

		@Schema(description = "Password", minLength = 4, maxLength = 20)
		private String password;
	}

}