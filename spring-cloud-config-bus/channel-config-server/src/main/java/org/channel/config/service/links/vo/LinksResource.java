package org.channel.config.service.links.vo;

/**
 * 
 * 返回实体
 * <功能详细描述>
 *
 * @author  朱鹏
 * @version  [版本号, 2019年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LinksResource
{
	private String projectName;		// 项目名称
	
	private String controllerName;	// controller类名称
	
	private String methodName;		// 方法名称
	
	private String url;				// 链接地址
	
	private String type;			// 接口（http请求）类型
	
	private String route;			// 网关的名称

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	public String getControllerName()
	{
		return controllerName;
	}

	public void setControllerName(String controllerName)
	{
		this.controllerName = controllerName;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getRoute()
	{
		return route;
	}

	public void setRoute(String route)
	{
		this.route = route;
	}
	
	
}
