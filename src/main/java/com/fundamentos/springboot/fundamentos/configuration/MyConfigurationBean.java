package com.fundamentos.springboot.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanImpl;
import com.fundamentos.springboot.fundamentos.bean.MyBeanImpl2;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependencyImpl;
import com.fundamentos.springboot.fundamentos.bean.MyOperation;
import com.fundamentos.springboot.fundamentos.bean.MyOperationImpl;

@Configuration
public class MyConfigurationBean {
	
	@Bean
	public MyBean beanOperation() {
		return new MyBeanImpl2();
	}
	
	@Bean
	public MyOperation beanOperationOperation() {
		return new MyOperationImpl();
	}
	
	@Bean
	public MyBeanWithDependency beanOperationWithDependency( MyOperation myOperation) {
		return new MyBeanWithDependencyImpl(myOperation);
	}
	
}
