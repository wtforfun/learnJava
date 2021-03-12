import boot.spring.Application;
import boot.spring.service.impl.ProgressService;
import org.activiti.engine.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class Test2 {


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
     * 删除流程实例
     * */
    @Test
    public void test1(){
        String pid = "12528";
        runtimeService.deleteProcessInstance(pid,"删除原因");//删除流程
    }


}
