package org.springbootdemo.springbootdemo02;

import org.springbootdemo.springbootdemo02.bean.BookBean;
import org.springbootdemo.springbootdemo02.bean.PersonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Springbootdemo02Application {

//	@Value(value = "${book.author}")
//	private String bookAuthor;
//	@Value("${book.name}")
//	private String bookName;
//	@Value("${book.pinyin}")
//	private String bookPinYin;

	@Autowired
	private BookBean bookBean;

	@Autowired
	private PersonBean personBean;

	public static void main(String[] args) {
		SpringApplication.run(Springbootdemo02Application.class,args);
	}

	@RequestMapping("/")
	String index(){
		System.out.println(bookBean.getAuthor());
		return "welcome to spring boot + book.author="+ bookBean.getAuthor()+"name="+bookBean.getName()+"price="+bookBean.getPrice()+"person="+personBean.getName()+" "+personBean.getNickname()+" "+personBean.getAge();
	}
}
