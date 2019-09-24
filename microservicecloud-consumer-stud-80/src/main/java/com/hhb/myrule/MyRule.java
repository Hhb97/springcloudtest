package com.hhb.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MyRule {
	@Bean(name = "myRandomRule")
	  public IRule myRule()
	  {
	   return new MyRandomRule();//Ribbon默认是轮询，我自定义为随机 并切每个服务访问三次
	  }

}
