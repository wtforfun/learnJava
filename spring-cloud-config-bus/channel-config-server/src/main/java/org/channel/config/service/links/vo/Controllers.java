package org.channel.config.service.links.vo;

import java.util.Map;

public class Controllers
{
	private String name;
	
	private String rel;
	
	private Map<String,Methods> methodsMap; // 键为Methods的name(方法名称)

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRel()
	{
		return rel;
	}

	public void setRel(String rel)
	{
		this.rel = rel;
	}

	public Map<String, Methods> getMethodsMap()
	{
		return methodsMap;
	}

	public void setMethodsMap(Map<String, Methods> methodsMap)
	{
		this.methodsMap = methodsMap;
	}
	
}
