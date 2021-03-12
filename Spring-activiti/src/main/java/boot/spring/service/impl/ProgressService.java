package boot.spring.service.impl;

import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;
import java.util.Map;

public interface ProgressService {

    //void cleanResources(HistoricTaskInstance historicTaskInstance, List<ActivitiConfig> activitiConfigs, Map<String> taskLocalVariables);

    /**
     * 完成任务
     *
     * @param taskID          任务编号
     * @param globalVariables 全局流程变量
     * @param localVariables  任务节点的流程变量
     * @return 下个任务编号
     */
    String completeTaskByTaskID(String taskID, Map<String,Object> globalVariables, Map<String,Object> localVariables);

    /**
     * 完成任务
     *
     * @param taskId          任务编号
     * @param globalVariables 全局流程变量
     * @param localVariables  任务节点的流程变量
     * @return 下个任务编号
     */
    @Transactional
    String completeTaskByTaskIDAndProcessVariables(String taskId, Map<String,Object> globalVariables, Map<String,Object> localVariables);

    /**
     * 根据流程实例编号获取流程图 - 带经过轨迹
     *
     * @param processInstanceID 流程实例编号
     */
    InputStream getImgByProcessInstanceId(String processInstanceID);


}
