package org.channel.common.vo;

import org.channel.common.constant.StateEnum;

/**
 * 
 * 状态实体，作用范围是全局
 * <功能详细描述>
 * 
 * @author  chenzhi
 * @version  [版本号, 2018年12月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class StateInstance
{
    String id;
    
    StateEnum state;
    
    Boolean  stateType = false;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public StateEnum getState()
    {
        return state;
    }
    
    public void setState(StateEnum state)
    {
        this.state = state;
    }

	public Boolean getStateType() {
		return this.stateType;
	}

	public void setStateType(Boolean stateType) {
		this.stateType = stateType;
	}
    
}
