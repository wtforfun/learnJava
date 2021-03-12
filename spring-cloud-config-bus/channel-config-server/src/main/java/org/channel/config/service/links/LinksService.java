package org.channel.config.service.links;

import org.channel.config.base.SysQueryService;
import org.channel.config.service.links.vo.LinksResource;

/**
 * 
 * 链接业务
 * <功能详细描述>
 *
 * @author  朱鹏
 * @version  [版本号, 2019年4月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface LinksService
{
	/**
	 * 查询链接信息
	 * <功能详细描述>
	 * @param controllerName
	 * @param methodName
	 * @param arrayParams
	 * @param mapParams
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public SysQueryService<LinksResource> queryLinks() throws Exception;
}
