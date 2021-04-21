package edu.spring.ex03.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Proxy 객체에 주입(injection)하기 위해 선언
@Aspect // Aspect = Advice + Pointcut
public class HomeAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(HomeAspect.class);
	// * Aspect
	// - 일반적으로 메소드에 특정 기능을 적용시킴
	// - 메소드 실행 전후에 특정 기능을 적용시킬 수 있음
	
	// * pointcut을 지정하는 방법
	// 1. @Before, @After, ... 어노테이션 안에서 지정
	// 2. @Pointcut 어노테이션 안에서 지정
	// -> 동일한 pointcut이 반복되는 것을 피할 수 있음
	// -> 한 번 지정한 pointcut을 여러 advice 메소드에서 참조
	
	@Pointcut("execution(* edu.spring.ex03.HomeController.home(..))")
	public void pcHome() {}
	
	@Around("pcHome()") //포인트컷 메소드를 적용
	public Object homeAdvice(ProceedingJoinPoint jp) {
		Object result = null; 
		
		LOGGER.info("==== homeAdvice");
		try {
			LOGGER.info("===== home() 호출 전"); // @before
			result = jp.proceed(); // HomeController.home url 실행
			LOGGER.info("===== home() 리턴 후"); // @afterReturning
		} catch (Throwable e) {
			//@afterThrowing
			LOGGER.info("===== 예외 발생 : " + e.getMessage());
		} finally {
			//@after
			LOGGER.info("===== finally");
		}
		return "reply"; // return result; 하면 home나옴 hellow ??시 ??분
	}
}














