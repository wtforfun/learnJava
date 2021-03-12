package org.channel.config.service.links.impl;

import java.util.Map;

import org.channel.config.base.SysQueryService;
import org.channel.config.service.links.AbsLinksService;
import org.channel.config.service.links.LinksService;
import org.channel.config.service.links.vo.LinksResource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 
 * 链接业务实现
 * <功能详细描述>
 *
 * @author  朱鹏
 * @version  [版本号, 2019年4月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LinksServiceFacade extends AbsLinksService implements LinksService
{
	private String projectName;
	
	private String controllerName;
	
	private String methodName;
	
	private String[] linkParams;
	
	private Map<String, Object> allParams;
	
	public LinksServiceFacade addProjectName(String projectName)
	{
		this.projectName = projectName;
		return this;
	}
	
	public LinksServiceFacade addControllerName(String controllerName)
	{
		this.controllerName = controllerName;
		return this;
	}

	public LinksServiceFacade addMethodName(String methodName)
	{
		this.methodName = methodName;
		return this;
	}

	public LinksServiceFacade addLinkParams(String[] linkParams)
	{
		this.linkParams = linkParams;
		return this;
	}

	public LinksServiceFacade addAllParams(Map<String, Object> allParams)
	{
		this.allParams = allParams;
		return this;
	}

	@Override
	public SysQueryService<LinksResource> queryLinks() throws Exception
	{
		SysQueryService<LinksResource> sysQueryService = context.getBean(QueryLinks.class)
				.addProjectName(projectName)
				.addControllerName(controllerName)
				.addMethodName(methodName)
				.addLinkParams(linkParams)
				.addAllParams(allParams);
		
		sysQueryService.execute();
		return sysQueryService;
	}

}
