package org.channel.config.base;

/**
 * 
 * 业务查询接口
 * <功能详细描述>
 *
 * @author  朱鹏
 * @version  [版本号, 2019年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SysQueryService<T> extends BaseService
{
    
    T resultObj() throws Exception;
    
}
