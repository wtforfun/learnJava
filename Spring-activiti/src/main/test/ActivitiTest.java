import boot.spring.Application;
import boot.spring.RejectTaskCMD;
import boot.spring.constant.ProcessConstant;
import boot.spring.service.impl.ProgressService;
import boot.spring.util.DateTimeUtil;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityManager;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class ActivitiTest {

    @Autowired
    ProcessEngine processEngine;

    @Autowired
    HistoryService historyService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProgressService progressService;

    @Autowired
    TaskService taskService;

    /**
     * 获取正要执行的任务Id
     * */
    @Test
    public void test1(){
        String taskId = "5002";
        taskService.complete(taskId);
        System.out.println("处理成功");
    }


    /*
    * 方式1
    * 流程撤回测试
    * */
    @Test
    public void test2(){
        String currentTaskId = "22536";
        String destinationId =  "22531";
        String msg = "不同意 驳回流程";

        String nextId = rejectTask(destinationId,currentTaskId,msg);
        System.out.println("回退成功");
    }


    /*
     * 方式2 turnBackNew
     * 流程撤回测试
     * */
    @Test
    public void test3(){
        String currentTaskId = "30";
        String destinationId =  "23";
        String msg = "不同意 驳回流程";

        try {
            turnBackNew(currentTaskId,msg,destinationId);
        }catch (Exception e){

        }
    }

    /**
     * 驳回任务方封装
     *
     * @param destinationTaskID 驳回的任务ID 目标任务ID
     * @param messageContent  驳回的理由
     * @param currentTaskID  当前正要执行的任务ID
     * @return 驳回结果 携带下个任务编号
     */
    public String rejectTask(String destinationTaskID, String currentTaskID, String messageContent) {
        // region 目标任务实例 historicDestinationTaskInstance 带流程变量，任务变量
        HistoricTaskInstance historicDestinationTaskInstance = historyService
                .createHistoricTaskInstanceQuery()
                .taskId(destinationTaskID)
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .singleResult();

        Date desTaskStartTime = historicDestinationTaskInstance.getStartTime();
        // endregion
        // region 正在执行的任务实例 historicCurrentTaskInstance 带流程变量，任务变量
        HistoricTaskInstance historicCurrentTaskInstance = historyService
                .createHistoricTaskInstanceQuery()
                .taskId(currentTaskID)
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .singleResult();
        // endregion
        // 流程定义ID
        String processDefinitionId = historicCurrentTaskInstance.getProcessDefinitionId();
        // 流程实例ID
        String processInstanceId = historicCurrentTaskInstance.getProcessInstanceId();
        // 流程定义实体
        ProcessDefinitionEntity processDefinition =
                (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        // region 根据任务创建时间正序排序获取历史任务实例集合 historicTaskInstanceList 含流程变量，任务变量
        List<HistoricTaskInstance> historicTaskInstanceList = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .orderByTaskCreateTime()
                .asc()
                .list();
        // endregion
        // region 历史活动节点实例集合 historicActivityInstanceList
        List<HistoricActivityInstance> historicActivityInstanceList =
                historyService
                        .createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .orderByHistoricActivityInstanceStartTime()
                        .asc()
                        .list();

        // endregion
        // 获取目标任务的节点信息
        ActivityImpl destinationActivity = processDefinition
                .findActivity(historicDestinationTaskInstance.getTaskDefinitionKey());
        // 定义一个历史任务集合，完成任务后任务删除此集合中的任务
        List<HistoricTaskInstance> deleteHistoricTaskInstanceList = new ArrayList<>();
        // 定义一个历史活动节点集合，完成任务后要添加的历史活动节点集合
        List<HistoricActivityInstanceEntity> insertHistoricTaskActivityInstanceList = new ArrayList<>();
        // 遍历所有节点，获取目标节点信息
        HistoricActivityInstance desActivityEntity = null;
        for(HistoricActivityInstance h : historicActivityInstanceList){
            if(historicDestinationTaskInstance.getTaskDefinitionKey().equals(h.getActivityId())){
                desActivityEntity = h;
            }
        }
        Integer destinationTaskInstanceId = Integer.valueOf(destinationTaskID);

        // 筛选出需要被删除的任务实例
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
            /*
            * 改为用时间判断需要被删除的任务实例
            * */
            if (DateTimeUtil.gteTime(historicTaskInstance.getStartTime(),desTaskStartTime)) {
                deleteHistoricTaskInstanceList.add(historicTaskInstance);
            }
        }


        // 筛选出需要被添加的活动节点
        for (int i = 0; i < historicActivityInstanceList.size() - 1; i++) {
            HistoricActivityInstance historicActivityInstance = historicActivityInstanceList.get(i);
            String taskId = historicActivityInstance.getTaskId();
            //任务节点
            if (taskId != null) {
                //如果节点的开始时间  小于等于 目标节点的开始时间 那么该节点需要被添加
                if (DateTimeUtil.lteTime(historicActivityInstance.getStartTime(),desActivityEntity.getStartTime())) {
                    insertHistoricTaskActivityInstanceList.add((HistoricActivityInstanceEntity) historicActivityInstance);
                }
            //非任务节点
            } else {
                //开始节点
                if (historicActivityInstance.getActivityType().equals(ProcessConstant.START_EVENT)) {
                    insertHistoricTaskActivityInstanceList.add((HistoricActivityInstanceEntity) historicActivityInstance);
                } else if(DateTimeUtil.lteTime(historicActivityInstance.getStartTime(),desActivityEntity.getStartTime())){
                    //排他网关节点
                    if (historicActivityInstance.getActivityType().equals(ProcessConstant.EXCLUSIVE_GATEWAY)){
                        insertHistoricTaskActivityInstanceList.add((HistoricActivityInstanceEntity) historicActivityInstance);
                    }
                }
            }
        }
        // 获取流程定义的节点信息
        List<ActivityImpl> processDefinitionActivities = processDefinition.getActivities();
        // 用于保存正在执行的任务节点信息
        ActivityImpl currentActivity = null;
        // 用于保存原来的任务节点的出口信息
        PvmTransition pvmTransition = null;
        // 保存原来的流程节点出口信息
        for (ActivityImpl activity : processDefinitionActivities) {
            if (historicCurrentTaskInstance.getTaskDefinitionKey().equals(activity.getId())) {
                currentActivity = activity;
                // 备份
                pvmTransition = activity.getOutgoingTransitions().get(0);
                // 清空当前任务节点的出口信息
                activity.getOutgoingTransitions().clear();
            }
        }
        // 执行流程转向
        processEngine.getManagementService().executeCommand(
                //new RejectTaskCMD(historicDestinationTaskInstance, historicCurrentTaskInstance, destinationActivity));
                new RejectTaskCMD(historicCurrentTaskInstance, historicDestinationTaskInstance, destinationActivity));
        // 获取正在执行的任务的流程变量
        Map<String, Object> taskLocalVariables = historicCurrentTaskInstance.getTaskLocalVariables();
        // 获取目标任务的流程变量，修改任务不自动跳过，要求审批
        Map<String, Object> processVariables = historicDestinationTaskInstance.getProcessVariables();
        // 获取流程发起人编号
        Integer employeeId = (Integer) processVariables.get(ProcessConstant.PROCESS_START_PERSON);
        processVariables.put(ProcessConstant.SKIP_EXPRESSION, false);
        taskLocalVariables.put(ProcessConstant.SKIP_EXPRESSION, false);
        // 设置驳回原因
        taskLocalVariables.put(ProcessConstant.REJECT_REASON, messageContent);
        // region 流程变量转换
        // 修改下个任务的任务办理人
        processVariables.put(ProcessConstant.DEAL_PERSON_ID, processVariables.get(ProcessConstant.CURRENT_PERSON_ID));
        // 修改下个任务的任务办理人姓名
        processVariables.put(ProcessConstant.DEAL_PERSON_NAME, processVariables.get(ProcessConstant.CURRENT_PERSON_NAME));
        // 修改下个任务的任务办理人
        taskLocalVariables.put(ProcessConstant.DEAL_PERSON_ID, processVariables.get(ProcessConstant.CURRENT_PERSON_ID));
        // 修改下个任务的任务办理人姓名
        taskLocalVariables.put(ProcessConstant.DEAL_PERSON_NAME, processVariables.get(ProcessConstant.CURRENT_PERSON_NAME));
        // endregion
        // 完成当前任务，任务走向目标任务
        String nextTaskId = progressService.completeTaskByTaskID(currentTaskID, processVariables, taskLocalVariables);

        //获得当前活动的任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
        //将需要添加的历史节点里面对应的任务ID更新为最新的任务ID
        for(HistoricActivityInstanceEntity historicActivityInstance : insertHistoricTaskActivityInstanceList){
            String actId = historicActivityInstance.getActivityId();
            if(task.getTaskDefinitionKey().equals(actId)){
                historicActivityInstance.setTaskId(task.getId());
            }
        }
        /*
        * 这里可以获取到最新的任务实例
        * */
        if (currentActivity != null) {
            // 清空临时转向信息
            currentActivity.getOutgoingTransitions().clear();
        }
        if (currentActivity != null) {
            // 恢复原来的走向
            currentActivity.getOutgoingTransitions().add(pvmTransition);
        }
        // 删除历史任务
        for (HistoricTaskInstance historicTaskInstance : deleteHistoricTaskInstanceList) {
            historyService.deleteHistoricTaskInstance(historicTaskInstance.getId());
        }
        // 删除活动节点
        processEngine.getManagementService().executeCommand(
                (Command<List<HistoricActivityInstanceEntity>>) commandContext -> {
                    HistoricActivityInstanceEntityManager historicActivityInstanceEntityManager =
                            commandContext.getHistoricActivityInstanceEntityManager();
                    // 删除所有的历史活动节点
                    historicActivityInstanceEntityManager
                            .deleteHistoricActivityInstancesByProcessInstanceId(processInstanceId);
                    // 提交到数据库
                    commandContext.getDbSqlSession().flush();
                    // 添加历史活动节点的
                    for (HistoricActivityInstanceEntity historicActivityInstance : insertHistoricTaskActivityInstanceList) {
                        historicActivityInstanceEntityManager.insertHistoricActivityInstance(historicActivityInstance);
                    }
                    // 提交到数据库
                    commandContext.getDbSqlSession().flush();
                    return null;
                }
        );
        // 返回下个任务的任务ID
        return nextTaskId;
    }

    /**
     *
     * @param taskId 任务id
     * @param msg 批注
     * @param endActivityId 结束节点的activitiyId
     * @throws Exception
     */
    public void turnBackNew(String taskId, String msg, String endActivityId) throws Exception {
        Map<String, Object> variables;
        // 取得当前任务
        HistoricTaskInstance currTask = historyService
                .createHistoricTaskInstanceQuery().taskId(taskId)
                .singleResult();
        // 取得流程实例
        ProcessInstance instance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(currTask.getProcessInstanceId())
                .singleResult();
        if (instance == null) {
            throw new RuntimeException("流程已结束");
        }
        variables = instance.getProcessVariables();
        // 取得流程定义
        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(currTask
                        .getProcessDefinitionId());
        if (definition == null) {
            throw new RuntimeException("流程定义未找到");
        }
        // 取得上一步活动
        ActivityImpl currActivity = ((ProcessDefinitionImpl) definition).findActivity(currTask.getTaskDefinitionKey());

        List<ActivityImpl> rtnList = new ArrayList<ActivityImpl>();
        List<ActivityImpl> tempList = new ArrayList<ActivityImpl>();

        //迭代循环流程树结构，查询当前节点可驳回的任务节点
        List<ActivityImpl> activities = iteratorBackActivity(taskId,currActivity, rtnList, tempList);

        if (activities == null || activities.size() <= 0)
            throw new RuntimeException("没有可以选择的驳回节点!");

        List<Task> list = taskService.createTaskQuery().processInstanceId(instance.getId()).list();
        for (Task task : list) {
            if (!task.getId().equals(taskId)) {
                task.setAssignee("排除标记");
                commitProcess(task.getId(), null, endActivityId);
            }
        }
        //流程退回
        turnTransition(taskId, activities.get(0).getId(), null);
    }

    /**
     * 提交流程
     * @param taskId     当前任务ID
     * @param variables  流程变量
     * @param activityId 流程转向执行任务节点ID，此参数为空，默认为提交操作
     *
     * @throws Exception
     */
    private void commitProcess(String taskId, Map<String, Object> variables, String activityId) throws Exception {
        if (variables == null) {
            variables = new HashMap<String, Object>();
        }
        // 跳转节点为空，默认提交操作
        if (activityId == null || activityId.equals("")) {
            taskService.complete(taskId, variables);
        } else {// 流程转向操作
            turnTransition(taskId, activityId, variables);
        }
    }

    /**
     * 清空指定活动节点流向
     *
     * @param activityImpl 活动节点
     * @return 节点流向集合
     */
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        // 获取当前节点所有流向，存储到临时变量，然后清空
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    /**
     * 还原指定活动节点流向
     *
     * @param activityImpl         活动节点
     * @param oriPvmTransitionList 原有节点流向集合
     */
    private void restoreTransition(ActivityImpl activityImpl,
                                   List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }

    /**
     * 流程转向操作
     *
     * @param taskId     当前任务ID
     * @param activityId 目标节点任务ID
     * @param variables  流程变量
     * @throws Exception
     */
    private void turnTransition(String taskId, String activityId,
                                Map<String, Object> variables) throws Exception {
        // 当前节点
        ActivityImpl currActivity = findActivitiImpl(taskId, null);
        // 清空当前流向
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
        // 设置新流向的目标节点
        newTransition.setDestination(pointActivity);

        // 执行转向任务
        taskService.complete(taskId, variables);
        // 删除目标节点新流入
        pointActivity.getIncomingTransitions().remove(newTransition);

        // 还原以前流向
        restoreTransition(currActivity, oriPvmTransitionList);
    }

    /**
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点
     *
     * @param taskId       当前任务ID
     * @param currActivity 当前活动节点
     * @param rtnList      存储回退节点集合
     * @param tempList     临时存储节点集合（存储一次迭代过程中的同级userTask节点）
     * @return 回退节点集合
     */
    private List<ActivityImpl> iteratorBackActivity(String taskId,
                                                    ActivityImpl currActivity, List<ActivityImpl> rtnList,
                                                    List<ActivityImpl> tempList) throws Exception {
        // 查询流程定义，生成流程树结构
        ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);

        // 当前节点的流入来源
        List<PvmTransition> incomingTransitions = currActivity.getIncomingTransitions();
        // 排他网关节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点
        List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();
        // 并行网关节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点
        List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();
        // 遍历当前节点所有流入路径
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            ActivityImpl activityImpl = transitionImpl.getSource();
            String type = (String) activityImpl.getProperty("type");
            /**
             * 并行节点配置要求：<br>
             * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束)
             */
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId.lastIndexOf("_") + 1);
                if ("START".equals(gatewayType.toUpperCase())) {// 并行起点，停止递归
                    return rtnList;
                } else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                    parallelGateways.add(activityImpl);
                }
            } else if ("startEvent".equals(type)) {// 开始节点，停止递归
                return rtnList;
            } else if ("userTask".equals(type)) {// 用户任务
                tempList.add(activityImpl);
            } else if ("exclusiveGateway".equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                currActivity = transitionImpl.getSource();
                exclusiveGateways.add(currActivity);
            }
        }

        /**
         * 迭代排他网关集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : exclusiveGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /**
         * 迭代并行网关集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : parallelGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /**
         * 根据同级userTask集合，过滤最近发生的节点
         */
        currActivity = filterNewestActivity(processInstance, tempList);
        if (currActivity != null) {
            // 查询当前节点的流向是否为并行终点，并获取并行起点ID
            String id = findParallelGatewayId(currActivity);
            if (id == null || id.equals("")) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点
                rtnList.add(currActivity);
            } else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点
                currActivity = findActivitiImpl(taskId, id);
            }

            // 清空本次迭代临时集合
            tempList.clear();
            // 执行下次迭代
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);
        }
        return rtnList;
    }

    /**
     * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID
     *
     * @param activityImpl 当前节点
     * @return
     */
    private String findParallelGatewayId(ActivityImpl activityImpl) {
        List<PvmTransition> incomingTransitions = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            activityImpl = transitionImpl.getDestination();
            String type = (String) activityImpl.getProperty("type");
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId
                        .lastIndexOf("_") + 1);
                if ("END".equals(gatewayType.toUpperCase())) {
                    return gatewayId.substring(0, gatewayId.lastIndexOf("_"))
                            + "_start";
                }
            }
        }
        return null;
    }

    /**
     * 根据流入任务集合，查询最近一次的流入任务节点
     *
     * @param processInstance 流程实例
     * @param tempList        流入任务集合
     * @return
     */
    private ActivityImpl filterNewestActivity(ProcessInstance processInstance,
                                              List<ActivityImpl> tempList) {
        while (tempList.size() > 0) {
            ActivityImpl activity_1 = tempList.get(0);
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(
                    processInstance, activity_1.getId());
            if (activityInstance_1 == null) {
                tempList.remove(activity_1);
                continue;
            }

            if (tempList.size() > 1) {
                ActivityImpl activity_2 = tempList.get(1);
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(
                        processInstance, activity_2.getId());
                if (activityInstance_2 == null) {
                    tempList.remove(activity_2);
                    continue;
                }

                if (activityInstance_1.getEndTime().before(
                        activityInstance_2.getEndTime())) {
                    tempList.remove(activity_1);
                } else {
                    tempList.remove(activity_2);
                }
            } else {
                break;
            }
        }
        if (tempList.size() > 0) {
            return tempList.get(0);
        }
        return null;
    }

    /**
     * 查询指定任务节点的最新记录
     *
     * @param processInstance 流程实例
     * @param activityId
     * @return
     */
    private HistoricActivityInstance findHistoricUserTask(
            ProcessInstance processInstance, String activityId) {
        HistoricActivityInstance rtnVal = null;
        // 查询当前流程实例审批结束的历史节点
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery().activityType("userTask")
                .processInstanceId(processInstance.getId()).activityId(
                        activityId).finished()
                .orderByHistoricActivityInstanceEndTime().desc().list();
        if (historicActivityInstances.size() > 0) {
            rtnVal = historicActivityInstances.get(0);
        }

        return rtnVal;
    }

    /**
     * 根据任务ID和节点ID获取活动节点 <br>
     *
     * @param taskId     任务ID
     * @param activityId 活动节点ID <br>
     *                   如果为null或""，则默认查询当前活动节点 <br>
     *                   如果为"end"，则查询结束节点 <br>
     * @return
     * @throws Exception
     */
    private ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

        // 获取当前活动节点ID
        if (activityId == null || "".equals(activityId)) {
            activityId = findTaskById(taskId).getTaskDefinitionKey();
        }

        // 根据流程定义，获取该流程实例的结束节点
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl
                        .getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }

        // 根据节点ID，获取对应的活动节点
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
                .findActivity(activityId);

        return activityImpl;
    }

    /**
     * 根据任务ID获取流程定义
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
            String taskId) throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(findTaskById(taskId)
                        .getProcessDefinitionId());

        if (processDefinition == null) {
            throw new Exception("流程定义未找到!");
        }

        return processDefinition;
    }



    /**
     * 根据任务ID获取对应的流程实例
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private ProcessInstance findProcessInstanceByTaskId(String taskId)
            throws Exception {
        // 找到流程实例
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery().processInstanceId(
                        findTaskById(taskId).getProcessInstanceId())
                .singleResult();
        if (processInstance == null) {
            throw new Exception("流程实例未找到!");
        }
        return processInstance;
    }

    /**
     * 根据任务ID获得任务实例
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private TaskEntity findTaskById(String taskId) throws Exception {
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(
                taskId).singleResult();
        if (task == null) {
            throw new Exception("任务实例未找到!");
        }
        return task;
    }

}
