package com.lijiangde.spring_boot_1.controller;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
public class EmailController {
	
	@Autowired
	JavaMailSenderImpl javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;

	@RequestMapping(value="/sendtemplatemail")
	public String sendEmail() throws MessagingException{
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		//可以把这个看做是一个空白的纸张
		Context context=new Context();
		//设置一个变量，可以在themeleaf中随时可以根据id取值，而且这个id应该是用户
		//注册时动态生成的，这里为了简单起见，设定一个常数3
		context.setVariable("id", 3);
		//模板引擎去解析这个模板名，找到真正的视图，解析完后放在一个白纸上（也就是context）
		String process=templateEngine.process("emailTemplates",context);
		//发送的内容用html修饰(process)，后面要设置为true，默认是关闭的
		helper.setText(process,true);
		helper.setSubject("spring boot 模板验证测试邮件");
		//发件人
		helper.setFrom("1043103230@qq.com");
		//收件人
		helper.setTo("jiangde_li@163.com");
		//发送器发送消息
		javaMailSender.send(mimeMessage);
		return "模板测试邮件发送成功";
	} 
	
	@RequestMapping(value="/qqsendemail")
	public String sendqqto163email() throws MessagingException{
		SimpleMailMessage message=new SimpleMailMessage();
		//没有附件
		/*//邮件标题
		message.setSubject("Spring boot 测试邮件发送");
		//邮件内容
		message.setText("今晚要去吃好吃的，你去吗？");
		//发件人
		message.setFrom("1043103230@qq.com");
		//收件人
		message.setTo("jiangde_li@163.com");
		javaMailSender.send(message);*/
		
		
		//添加附件
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		//发送的内容还可以用html修饰，后面要设置为true，默认是关闭的
		helper.setSubject("spring boot 测试邮件");
		helper.setText("<b><h1 style='color:red'>今晚要去吃好吃的，你去吗</h1></b>",true);
		//给附件取个名字,附件位置
		helper.addAttachment("附件名字",new File("F:\\java\\手把手教你如何玩转Activiti工作流.txt"));
		//发件人
		helper.setFrom("1043103230@qq.com");
		//收件人
		helper.setTo("jiangde_li@163.com");
		
		//发送器发送消息
		javaMailSender.send(mimeMessage);
		return "发送成功";
	}
	
	@RequestMapping(value="/validateEmail/{id}")
	@ResponseBody
	public String validateEmail(@PathVariable("id") Integer id){
		//这里拿到id后，可以到数据库查询，进行逻辑处理。这里省略。。。。。。
		return "激活成功，你的id号是："+id;
	}
	

}
