package org.channel.config.service.links.vo;

import java.util.Map;

public class Projects
{
	private String name;
	
	private String route;	// 网关的名称,对应网关配置文件里的routes下面的名称
	
	private String rel;
	
	private Map<String,Controllers> controllersMap; // 键为Controllers的name(类名称)

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRoute()
	{
		return route;
	}

	public void setRoute(String route)
	{
		this.route = route;
	}

	public String getRel()
	{
		return rel;
	}

	public void setRel(String rel)
	{
		this.rel = rel;
	}

	public Map<String, Controllers> getControllersMap()
	{
		return controllersMap;
	}

	public void setControllersMap(Map<String, Controllers> controllersMap)
	{
		this.controllersMap = controllersMap;
	}
	
	
}
