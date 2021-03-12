package boot.spring.service.impl;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;

@Service
public class ProgressServiceImpl implements ProgressService{

    @Autowired
    TaskService taskService;

    @Override
    public String completeTaskByTaskID(String taskID, Map<String, Object> globalVariables, Map<String, Object> localVariables) {
        taskService.complete(taskID,globalVariables);
        return null;
    }

    @Override
    public String completeTaskByTaskIDAndProcessVariables(String taskId, Map<String, Object> globalVariables, Map<String, Object> localVariables) {
        return null;
    }

    @Override
    public InputStream getImgByProcessInstanceId(String processInstanceID) {
        return null;
    }
}
