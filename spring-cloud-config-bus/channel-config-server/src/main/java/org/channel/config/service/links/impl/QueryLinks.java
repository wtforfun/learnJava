package org.channel.config.service.links.impl;

import java.util.Map;

import org.channel.config.base.SysQueryService;
import org.channel.config.service.links.AbsLinksService;
import org.channel.config.service.links.vo.Links;
import org.channel.config.service.links.vo.LinksResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QueryLinks extends AbsLinksService implements SysQueryService<LinksResource>
{
	private static final String ALL_PARAMS_KEY_CONTROLLER_NAME = "controllerName"; // allParams的参数键
	private static final String ALL_PARAMS_KEY_METHOD_NAME = "methodName"; // allParams的参数键
	
	@Autowired
	private Links links;
	
	private String projectName;
	
	private String controllerName;
	
	private String methodName;
	
	private String[] linkParams;
	
	private Map<String, Object> allParams;
	
	private String type;
	
	private String url;
	
	public QueryLinks addProjectName(String projectName)
	{
		this.projectName = projectName;
		return this;
	}
	
	public QueryLinks addControllerName(String controllerName)
	{
		this.controllerName = controllerName;
		return this;
	}

	public QueryLinks addMethodName(String methodName)
	{
		this.methodName = methodName;
		return this;
	}

	public QueryLinks addLinkParams(String[] linkParams)
	{
		this.linkParams = linkParams;
		return this;
	}

	public QueryLinks addAllParams(Map<String, Object> allParams)
	{
		this.allParams = allParams;
		return this;
	}
	
	@Override
	public void execute() throws Exception
	{
		type = links.getType(projectName,controllerName, methodName);
		if(linkParams != null)
		{
			url = links.assembleUrl(projectName,controllerName, methodName, linkParams);
			return;
		}
			
		allParams.remove(ALL_PARAMS_KEY_CONTROLLER_NAME);
		allParams.remove(ALL_PARAMS_KEY_METHOD_NAME);
		url = links.assembleUrl(projectName,controllerName, methodName, allParams);
	}

	@Override
	public LinksResource resultObj() throws Exception
	{
		LinksResource resource = new LinksResource();
		resource.setProjectName(projectName);
		resource.setControllerName(controllerName);
		resource.setMethodName(methodName);
		resource.setUrl(url);
		resource.setType(type);
		resource.setRoute(links.getProjectsMap().get(projectName).getRoute());
		
		return resource;
	}

}
