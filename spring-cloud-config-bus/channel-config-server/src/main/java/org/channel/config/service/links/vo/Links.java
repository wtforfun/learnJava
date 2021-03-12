package org.channel.config.service.links.vo;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.channel.common.exception.ChannelException;

public class Links
{
	private String address;
	
	private String port;
	
	private Map<String,Projects> projectsMap; // 键为Projects的name(项目名称)
	
	/**
	 * 按参数顺序获取链接
	 * <功能详细描述>
	 * @param controllerName
	 * @param methodName
	 * @param linkParams
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String assembleUrl(String projectName,String controllerName,String methodName,String[] linkParams)
	{
		return processTemplate(template(projectName,controllerName, methodName), linkParams);
	}
	
	/**
	 * 按参数名称获取链接
	 * <功能详细描述>
	 * @param controllerName
	 * @param methodName
	 * @param allParams
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String assembleUrl(String projectName,String controllerName,String methodName,Map<String,Object> allParams)
	{
		return processTemplate(template(projectName,controllerName, methodName), allParams);
	}
	
	
	/**
	 * 获取http的类型
	 * <功能详细描述>
	 * @param controllerName
	 * @param methodName
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getType(String projectName,String controllerName,String methodName)
	{
		validateParam(projectName,controllerName, methodName);
		return projectsMap.get(projectName).getControllersMap().get(controllerName).getMethodsMap().get(methodName).getType();
	}
	
	
	
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPort()
	{
		return port;
	}

	public void setPort(String port)
	{
		this.port = port;
	}
	
	public Map<String, Projects> getProjectsMap()
	{
		return projectsMap;
	}

	public void setProjectsMap(Map<String, Projects> projectsMap)
	{
		this.projectsMap = projectsMap;
	}

	/**
	 * 按顺序替换字符串模板
	 * <功能详细描述>
	 * @param template
	 * @param params
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String processTemplate(String template, String[] params){
	    StringBuffer sb = new StringBuffer();
	    Matcher m = Pattern.compile("\\{\\w+\\}").matcher(template);
	    int i = 0;
	    while (m.find() && i < params.length) 
	    {
	    	String value = params[i];
	        m.appendReplacement(sb, value);
	        i++;
	    }
	    m.appendTail(sb);
	    return sb.toString();
	}
	
	/**
	 * 按名称替换字符串模板
	 * <功能详细描述>
	 * @param template
	 * @param params
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String processTemplate(String template, Map<String, Object> params){
	    StringBuffer sb = new StringBuffer();
	    Matcher m = Pattern.compile("\\{\\w+\\}").matcher(template);
	    while (m.find()) 
	    {
	        String param = m.group();
	        Object value = params.get(param.substring(1, param.length() - 1));
	        m.appendReplacement(sb, value==null ? param : value.toString());
	    }
	    m.appendTail(sb);
	    return sb.toString();
	}
	
	/**
	 * 组装模板
	 * <功能详细描述>
	 * @param controllerName
	 * @param methodName
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String template(String projectName,String controllerName,String methodName)
	{
		validateParam(projectName,controllerName, methodName);
		StringBuffer sb = new StringBuffer(address).append(":").append(port);
		Projects projects = projectsMap.get(projectName);
		sb.append(projects.getRel());
		sb.append(projects.getControllersMap().get(controllerName).getRel());
		sb.append(projects.getControllersMap().get(controllerName).getMethodsMap().get(methodName).getRel());
		return sb.toString();
	}
	
	/**
	 * 验证参数projectName
	 * <功能详细描述>
	 * @param projectName
	 * @see [类、类#方法、类#成员]
	 */
	private void validateParam(String projectName)
	{
		Projects projects = projectsMap.get(projectName);
		if(projects == null)
		{
			throw new ChannelException("参数错误！projectName is error!!!");
		}
	}
	
	/**
	 * 验证参数projectName,controllerName
	 * <功能详细描述>
	 * @param controllerName
	 * @see [类、类#方法、类#成员]
	 */
	private void validateParam(String projectName,String controllerName)
	{
		validateParam(projectName);
		Controllers controller = projectsMap.get(projectName).getControllersMap().get(controllerName);
		if(controller == null)
		{
			throw new ChannelException("参数错误！controllerName is error!!!");
		}
	}
	
	/**
	 * 验证参数projectName,controllerName和methodName
	 * <功能详细描述>
	 * @param controllerName
	 * @param methodName
	 * @see [类、类#方法、类#成员]
	 */
	private void validateParam(String projectName,String controllerName,String methodName)
	{
		validateParam(projectName,controllerName);
		Methods methods = projectsMap.get(projectName).getControllersMap().get(controllerName).getMethodsMap().get(methodName);
		if(methods == null)
		{
			throw new ChannelException("参数错误！methodName is error!!!");
		}
	}
}
