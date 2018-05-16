package com.adee.spring4;

public class HelloWorld1 {
	
	public HelloWorld1(String name, int age, HelloWorld2 hw2) {
		this.name = name;
		this.age = age;
	}
	
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
