package com.adee.spring4;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car>{
	
	private String brand;
	
	public Car getObject() throws Exception {
		return new Car(getBrand(), 500000);
	}
	
	public Class<?> getObjectType() {
		return Car.class;
	}
	
	public boolean isSingleton() {
		return true;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
