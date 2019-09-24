package com.hhb.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhb.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.pojo.Dept;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DeptController {
	@Autowired
	private DeptService service;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	 //@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = service.get(id);
		if (dept == null) {
			throw new RuntimeException();
		}
		return dept;
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		log.info("=====getall====");
		return service.list();
	}
	 public Dept processHystrix_Get(@PathVariable("id") Long id)
	  {
	   return new Dept().setDeptno(id)
	           .setDname("该ID："+id+"没有没有对应的信息,null--@HystrixCommand")
	           .setDb_source("no this database in MySQL");
	  }

}
