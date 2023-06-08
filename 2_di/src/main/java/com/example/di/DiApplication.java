package com.example.di;

import com.example.di.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DiApplication.class, args);
		MyController controller = ctx.getBean(MyController.class);

		ConfigurableApplicationContext ctx2 = SpringApplication.run(DiApplication.class, args);
		MyController controller2 = ctx2.getBean(MyController.class);

		System.out.println("In main method");
		System.out.println(controller.sayHello());

		System.out.println(controller2.sayHello());
	}
}
