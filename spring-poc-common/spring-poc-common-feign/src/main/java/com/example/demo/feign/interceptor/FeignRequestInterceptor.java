package com.example.demo.feign.interceptor;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

	/* (non-Javadoc)
	 * @see feign.RequestInterceptor#apply(feign.RequestTemplate)
	 */
	@Override
	public void apply(RequestTemplate template) {
		if (template.headers().containsKey(AUTHORIZATION)) {
			log.warn("The Authorization token has been already set");
		} else if (SecurityContextHolder.getContext().getAuthentication() == null) {
			log.warn("Can not obtain existing token for request, if it is a non secured request, ignore.");
		} else {
			Object authenticationPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (authenticationPrincipal instanceof String) {
				String token = "test123"; //Not yet implemented
				template.header(AUTHORIZATION, token);
			}
		}
		log.info("Authorization token interceptor completed");
	}


}
