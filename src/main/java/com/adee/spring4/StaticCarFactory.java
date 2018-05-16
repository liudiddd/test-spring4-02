package com.adee.spring4;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态工厂方法
 * 在不创建工厂实例的情况下，创建实例
 * @author Administrator
 *
 */
public class StaticCarFactory {
	private static Map<String, Car> map = new HashMap<String, Car>();
	static {
		map.put("audi", new Car("audi", 300000));
		map.put("ford", new Car("ford", 400000));
	}
	
	public static Car getCar(String name) {
		return map.get(name);
	}
}
