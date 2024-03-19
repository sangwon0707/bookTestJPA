package com.example.demo;

import java.lang.reflect.Method;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class BookTestJpaApplication {
	public static void main(String[] args) {
		String methodName ="sub";
		Test test = new Test();
		int r = 0;
		try {
			Class<?> cls = Class.forName(test.getClass().getName());
			Method method = cls.getDeclaredMethod(methodName, Integer.class);
			r = (Integer)method.invoke(test, 5);
			System.out.println(r);
		} catch (Exception e) {
			System.out.println("예외발생: "+e.getMessage());
		}

		
		SpringApplication.run(BookTestJpaApplication.class, args);
	}

}
