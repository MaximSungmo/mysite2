package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.management.relation.Role;

/* 타입은 클래스 메소드는 클래스 내 메소드에서 사용할 수 있다.*/ 
@Target( {ElementType.TYPE,ElementType.METHOD})
/*메소드에 붙여놓고 컴파일하는 것, Runtime  Override같은 경우는 source*/
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	/* Role이 vaule값으로 default 설정이 된다.*/
//	String value() default "user";

//	int test() default 1;
	
	/*권한의 경우 String으로 작성하는 것은 좋지 않다.*/
//	String role() default "USER";
	
	public enum Role {USER, ADMIN};  
	public Role role() default Role.USER;
	
	
	
	
}
