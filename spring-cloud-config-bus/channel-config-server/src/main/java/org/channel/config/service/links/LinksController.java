package org.channel.config.service.links;

import java.util.Map;

import org.channel.common.exception.ChannelException;
import org.channel.config.ContextHolder;
import org.channel.config.base.SysQueryService;
import org.channel.config.service.links.impl.LinksServiceFacade;
import org.channel.config.service.links.vo.LinksResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 * 链接信息接口
 * <功能详细描述>
 *
 * @author  朱鹏
 * @version  [版本号, 2019年4月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RestController
@RequestMapping("/links")
public class LinksController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected ContextHolder context;

	/**
	 * 查询组装好的链接
	 * 该接口读取links.json文件里的json串，占位符的替换规则有两种：
	 * 1.按json串里的占位符顺序传参数，将参数按顺序放入linkParams的数组里
	 * 2.按json串里的占位符名称传参数，例：如果占位符为{ispId}，则传参数ispId=xxxxx，多个参数同理
	 * 注：linkParams参数的规则优先级高于allParams，只有在没传linkParams时，规则变为allParams的规则
	 * 
	 * @param projectName		项目名称
	 * @param controllerName	controller类名称
	 * @param methodName		接口方法名称
	 * @param linkParams		链接中需要替换的参数（规则是按顺序排列！可不传，不传则需要遵循allParams参数的规则）
	 * @param allParams			所有的参数（如果没有使用linkParams，则必须按照links.json文件里json串里的key名称传参数）
	 * @return
	 * @see 					LinksServiceFacade#queryLinks()
	 */
	@GetMapping(value = "/")
	public ResponseEntity<LinksResource> search(@RequestParam String projectName,@RequestParam String controllerName,
			@RequestParam String methodName,String[] linkParams,@RequestParam Map<String,Object> allParams) {
		logger.info("查询链接信息集合入参为：controllerName = {},  methodName = {},  linkParams = {} ,  allParams = {} ",
				controllerName, methodName, linkParams,allParams);
				
		try {
			LinksService linksService = context.getBean(LinksServiceFacade.class)
					.addProjectName(projectName)
					.addControllerName(controllerName)
					.addMethodName(methodName)
					.addLinkParams(linkParams)
					.addAllParams(allParams);;
			SysQueryService<LinksResource> queryService = linksService.queryLinks();
			LinksResource resource = queryService.resultObj();
			return new ResponseEntity<LinksResource>(resource, HttpStatus.OK);
		}
		catch (ChannelException ce)
		{
			logger.error("查询链接信息失败!!", ce);
			throw ce;
		}
		catch (Exception e) {
			logger.error("系统异常!!", e);
			throw new ChannelException("查询链接信息失败!!",e);
		}
	}


}
