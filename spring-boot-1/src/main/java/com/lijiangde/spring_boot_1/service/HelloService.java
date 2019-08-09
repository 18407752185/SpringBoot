package com.lijiangde.spring_boot_1.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

//	注解里cron里面的很多星星意思是：每个月、周一到周日 、每个小时、每一分、每一秒都执行一次这个方法。
	
	@Scheduled(cron="* * * * * 1-7")
	public void say(){
		DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=simpleDateFormat.format(new Date(System.currentTimeMillis()));
		System.out.println("time="+time);
	}
}
