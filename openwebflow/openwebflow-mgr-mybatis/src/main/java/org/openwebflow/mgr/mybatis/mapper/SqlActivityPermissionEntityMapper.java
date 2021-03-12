package org.openwebflow.mgr.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.openwebflow.mgr.mybatis.entity.SqlActivityPermissionEntity;

public interface SqlActivityPermissionEntityMapper
{
	@Delete("DELETE from OWF_ACTIVITY_PERMISSION")
	public void deleteAll();

	@Select("SELECT * FROM OWF_ACTIVITY_PERMISSION where PROCESS_DEFINITION_ID=#{processDefinitionId} and ACTIVITY_KEY=#{taskDefinitionKey}")
	@Results(value = { @Result(property = "processDefinitionId", column = "PROCESS_DEF_ID"),
			@Result(property = "activityKey", column = "ACTIVITY_KEY"),
			@Result(property = "assignee", column = "ASSIGNED_USER"),
			@Result(property = "grantedGroupString", column = "GRANTED_GROUPS"),
			@Result(property = "grantedUserString", column = "GRANTED_USERS") })
	public SqlActivityPermissionEntity load(@Param("processDefinitionId")
	String processDefinitionId, @Param("taskDefinitionKey")
	String taskDefinitionKey);

	@Insert("INSERT INTO OWF_ACTIVITY_PERMISSION (PROCESS_DEFINITION_ID,ACTIVITY_KEY,ASSIGNED_USER,GRANTED_GROUPS,GRANTED_USERS,OP_TIME) values (#{processDefinitionId},#{activityKey},#{assignee},#{grantedGroupString},#{grantedUserString},#{opTime})")
	public void save(SqlActivityPermissionEntity ap);
}
