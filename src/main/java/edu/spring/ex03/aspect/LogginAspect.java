package edu.spring.ex03.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogginAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(LogginAspect.class);
	
	@Before("execution(* edu.spring.ex03.service.CustomerServiceImple.*Customer(..))")
	public void beforeAdvice(JoinPoint jp) {
		LOGGER.info("===== beforeAdvice");
		LOGGER.info("target : " + jp.getTarget());
		LOGGER.info("signature : " + jp.getSignature());
	}
}
