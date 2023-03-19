package com.example.demo.aop;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash.B__
 *
 */
@Component
@Aspect
@Slf4j
public class RepositoryLoggerAop {
	
	@Around("execution(public * com.example.demo.dao.BaseRepository+.*(..))")
	public Object aspectController(ProceedingJoinPoint jp) throws Throwable {
		String methodName = jp.getSignature().toShortString();
		Instant startTime = Instant.now();
		log.info("Called {}", methodName);
		Object result = jp.proceed();
		Instant endTime = Instant.now();
		Duration interval = Duration.between(startTime, endTime);
		if (interval.getSeconds() <= 0) {
			log.info("Completed {} on : {} MilliSeconds", methodName, interval.toMillis());
		} else {
			log.info("Completed {} on : {} Seconds", methodName, interval.getSeconds());
		}
		return result;
	}
}