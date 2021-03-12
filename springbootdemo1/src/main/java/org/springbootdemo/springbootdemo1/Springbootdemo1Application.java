package org.springbootdemo.springbootdemo1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * springboot核心类  项目入口
 * */
@RestController
@SpringBootApplication
public class Springbootdemo1Application {

	@Value(value = "${book.author}")
	private String bookAuthor;
	@Value("${book.name}")
	private String bookName;
	@Value("${book.pinyin}")
	private String bookPinYin;

	public static void main(String[] args) {
		SpringApplication.run(Springbootdemo1Application.class, args);
	}

	@RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
	String index(){
		System.out.println("bookAuthor="+bookAuthor);
		System.out.println("bookName="+bookName);
		System.out.println("bookPinYin="+bookPinYin);
		return "Hello Spring Boot! The BookName is "+bookName+";and Book Author is "+bookAuthor+";and Book PinYin is "+bookPinYin;
	}
}
