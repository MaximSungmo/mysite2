package com.cafe24.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {

	@Around("execution(* *..dao.*.*(..)) || execution(* *..service.*.*(..)) || execution(* *..controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch sw = new StopWatch();
		sw.start();
		// method 실행
		Object result = pjp.proceed();

		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		//메소드가 실행하는 클래스의 이름 확인 
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		//어떤 메소드가 실행되면서 시간이 얼마나 걸렸는 지 확인 가능 
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time]["+taskName+"]"+ totalTime + "mills");

		return result;
	}

}
