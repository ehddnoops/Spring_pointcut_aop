package edu.spring.ex03.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Proxy ��ü�� ����(injection)�ϱ� ���� ����
@Aspect // Aspect = Advice + Pointcut
public class HomeAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(HomeAspect.class);
	// * Aspect
	// - �Ϲ������� �޼ҵ忡 Ư�� ����� �����Ŵ
	// - �޼ҵ� ���� ���Ŀ� Ư�� ����� �����ų �� ����
	
	// * pointcut�� �����ϴ� ���
	// 1. @Before, @After, ... ������̼� �ȿ��� ����
	// 2. @Pointcut ������̼� �ȿ��� ����
	// -> ������ pointcut�� �ݺ��Ǵ� ���� ���� �� ����
	// -> �� �� ������ pointcut�� ���� advice �޼ҵ忡�� ����
	
	@Pointcut("execution(* edu.spring.ex03.HomeController.home(..))")
	public void pcHome() {}
	
	@Around("pcHome()") //����Ʈ�� �޼ҵ带 ����
	public Object homeAdvice(ProceedingJoinPoint jp) {
		Object result = null; 
		
		LOGGER.info("==== homeAdvice");
		try {
			LOGGER.info("===== home() ȣ�� ��"); // @before
			result = jp.proceed(); // HomeController.home url ����
			LOGGER.info("===== home() ���� ��"); // @afterReturning
		} catch (Throwable e) {
			//@afterThrowing
			LOGGER.info("===== ���� �߻� : " + e.getMessage());
		} finally {
			//@after
			LOGGER.info("===== finally");
		}
		return "reply"; // return result; �ϸ� home���� hellow ??�� ??��
	}
}














