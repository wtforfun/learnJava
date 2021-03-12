package org.channel.config.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.channel.config.service.links.vo.Controllers;
import org.channel.config.service.links.vo.Links;
import org.channel.config.service.links.vo.Methods;
import org.channel.config.service.links.vo.Projects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 
 * 链接配置
 * <功能详细描述>
 *
 * @author  朱鹏
 * @version  [版本号, 2019年4月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration("LinksConfig")
public class LinksConfig
{
	private static final String JSON_KEY_ADDRESS = "address";
	private static final String JSON_KEY_PORT = "port";
	private static final String JSON_KEY_PROJECTS = "projects";
	private static final String JSON_KEY_CONTROLLERS = "controllers";
	private static final String JSON_KEY_METHODS = "methods";
	private static final String JSON_KEY_NAME = "name";
	private static final String JSON_KEY_ROUTE = "route";
	private static final String JSON_KEY_REL = "rel";
	
	@Value(value = "classpath:links.json")
	private Resource linksJsonResource;
	
	@Bean(name = "links")
	public Links links()
	{
		
		String linksJson = getData(linksJsonResource);
		JSONObject linksObject = JSONObject.parseObject(linksJson);
		
		Map<String, Projects> projectsMap = new HashMap<>();
		JSONArray projectsArray = linksObject.getJSONArray(JSON_KEY_PROJECTS);
		for(int i=0;i<projectsArray.size();i++)
		{
			Map<String, Controllers> controllersMap = new HashMap<>();
			JSONObject projectsObject = projectsArray.getJSONObject(i);
			JSONArray controllersArray = projectsObject.getJSONArray(JSON_KEY_CONTROLLERS);
			for(int j=0;j<controllersArray.size();j++)
			{
				Map<String, Methods> methodsMap = new HashMap<>();
				JSONObject controllersObject = controllersArray.getJSONObject(j);
				JSONArray methodsArray = controllersObject.getJSONArray(JSON_KEY_METHODS);
				for(int k=0;k<methodsArray.size();k++)
				{
					JSONObject methodObject = methodsArray.getJSONObject(k);
					Methods methods = methodObject.toJavaObject(Methods.class);
					methodsMap.put(methods.getName(), methods);
				}
				String name = controllersObject.getString(JSON_KEY_NAME);
				String rel = controllersObject.getString(JSON_KEY_REL);
				
				Controllers controllers = new Controllers();
				controllers.setName(name);
				controllers.setRel(rel);
				controllers.setMethodsMap(methodsMap);
				controllersMap.put(name, controllers);
			}
			String name = projectsObject.getString(JSON_KEY_NAME);
			String route = projectsObject.getString(JSON_KEY_ROUTE);
			String rel = projectsObject.getString(JSON_KEY_REL);
			
			Projects projects = new Projects();
			projects.setName(name);
			projects.setRoute(route);
			projects.setRel(rel);
			projects.setControllersMap(controllersMap);
			projectsMap.put(name, projects);
		}
		
		String address = linksObject.getString(JSON_KEY_ADDRESS);
		String port = linksObject.getString(JSON_KEY_PORT);
		
		Links links = new Links();
		links.setAddress(address);
		links.setPort(port);
		links.setProjectsMap(projectsMap);
		
		return links;
	}
	
	
	
	String getData(Resource data)
    {
        try
        {
            InputStream inputStream = data.getInputStream();
            String jsonData = this.jsonRead(inputStream);
            return jsonData;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    String jsonRead(InputStream inputStream)
    {
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try
        {
            scanner = new Scanner(inputStream, "utf-8");
            while (scanner.hasNextLine())
            {
                buffer.append(scanner.nextLine());
            }
        }
        catch (Exception e)
        {
            
        }
        finally
        {
            if (scanner != null)
            {
                scanner.close();
            }
        }
        return buffer.toString();
    }
    
}
