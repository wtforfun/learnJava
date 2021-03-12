package boot.spring.mapper;

public interface ActivitiMapper {

    /*
    * 恢复目标任务节点的任务ID为 原始的任务ID
    * */

    void restoreTaskId(String originTaskId,String currentTaskId);
}
