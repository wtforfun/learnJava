package org.springbootdemos.springbootdemo4;

import org.springbootdemos.springbootdemo4.beans.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Springbootdemo4Application {

	public static void main(String[] args) {
		SpringApplication.run(Springbootdemo4Application.class, args);
	}

	@RequestMapping("/")
	public String index(Model model) {
		System.out.println("test....");
		Person single = new Person("aa", 11);
		List<Person> people = new ArrayList<>();
		Person p1 = new Person("zhangsan", 11);
		Person p2 = new Person("lisi", 22);
		Person p3 = new Person("wangwu", 33);
		people.add(p1);
		people.add(p2);
		people.add(p3);
		model.addAttribute("singlePerson", single);
		model.addAttribute("people", people);
		return "index";
	}
}
