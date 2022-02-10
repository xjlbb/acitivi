package com.activiti.test;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;


import java.util.List;

public class demo {


    /**
     * 生成Activiti的相关的表结构
     */
    @Test
    public void test01(){

        //使用classpath下的activiti.cfg.xml中的配置来创建ProcessEngine对象
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(engine);

    }


//    /**
//     * 没有按照默认的方式加载classpath下的 activiti.cfg.xml文件
//     * 自定义方式加载配置文件
//     */
//    @Test
//    public void test02(){
//        //首先创建ProcessEngineConfiguration对象
//        ProcessEngineConfiguration configuration =
//                ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration("activiti.cfg.xml");
//        //通过ProcessEngineConfiguration对象来创建 ProcessEngine对象
//        ProcessEngine processEngine = configuration.buildProcessEngine();
//    }


    /**
     * 实现文件的单个部署,如果路径找不到，maven install，target文件夹就会有bpmn文件夹
     */
    @Test
    public void test03(){
        //1.获取ProcessEngine对象
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        //2.获取RepositoryService进行部署操作
        RepositoryService service = engine.getRepositoryService();
        // 3.使用RepositoryService进行部署操作
        Deployment deploy = service.createDeployment()
                .addClasspathResource("bpmn/evection.bpmn") // 添加bpmn资源
                .addClasspathResource("bpmn/evection.png") // 添加png资源
                .name("出差申请流程-2")
                .deploy();// 部署流程
        // 4.输出流程部署的信息
        System.out.println("流程部署的id:" + deploy.getId());
        System.out.println("流程部署的名称：" + deploy.getName());

    }

    /**
     * 启动一个流程实例
     */
    @Test
    public void test04(){

        // 1.创建ProcessEngine对象
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 2.获取RuntimeService对象
        RuntimeService runtimeService = engine.getRuntimeService();
        // 3.根据流程定义的id启动流程
        String id= "evection";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(id);
        // 4.输出相关的流程实例信息
        System.out.println("流程定义的ID：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例的ID：" + processInstance.getId());
        System.out.println("当前活动的ID：" + processInstance.getActivityId());
    }

    /**
     * 任务查询
     */
    @Test
    public void test05(){

        String assignee = "lisi";
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        //任务查询 需要获取一个TaskService对象
        TaskService taskService = engine.getTaskService();
        //根据流程的key和任务负责人 查询任务
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("evection")
                .taskAssignee(assignee)
                .list();
        // 输出当前用户具有的任务
        for (Task task: list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id:" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }

    }

    /**
     * 流程任务的处理
     */
    @Test public void test06(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection")
                .taskAssignee("lisi")
                .singleResult();
        // 完成任务
        taskService.complete(task.getId());
    }

    /**
     * 查询流程的定义
     */
    @Test
    public void test07(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        // 获取一个 ProcessDefinitionQuery对象 用来查询操作
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.processDefinitionKey("evection")
                .orderByProcessDefinitionVersion()// 安装版本排序
                .desc()// 倒序
                .list();
        // 输出流程定义的信息
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义的ID：" + processDefinition.getId());
            System.out.println("流程定义的name：" + processDefinition.getName());
            System.out.println("流程定义的key:" + processDefinition.getKey());
            System.out.println("流程定义的version:" + processDefinition.getVersion());
            System.out.println("流程部署的id:" + processDefinition.getDeploymentId());
        }
    }

    /**
     * 删除流程
     */
    @Test
    public void test08(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        // 删除流程定义，如果该流程定义已经有了流程实例启动则删除时报错, 在ACT_RE_DEPLOYMENT中找ID
        repositoryService.deleteDeployment("22501");
        // 设置为TRUE 级联删除流程定义，及时流程有实例启动，也可以删除，设置为false 非级联删 除操作。
        // repositoryService.deleteDeployment("12501",true);
    }

    /**
     *  流程历史信息查看
     */
    @Test
    public void test09(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 查看历史信息我们需要通过 HistoryService来实现
        HistoryService historyService = engine.getHistoryService();
        // 获取 actinst 表的查询对象
        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
        instanceQuery.processDefinitionId("evection:1:4");
        instanceQuery.orderByHistoricActivityInstanceStartTime().desc();
        List<HistoricActivityInstance> list = instanceQuery.list();
        // 输出查询的结果
        for (HistoricActivityInstance hi : list) {
            System.out.println(hi.getActivityId());
            System.out.println(hi.getActivityName());
            System.out.println(hi.getActivityType());
            System.out.println(hi.getAssignee());
            System.out.println(hi.getProcessDefinitionId());
            System.out.println(hi.getProcessInstanceId());
            System.out.println("-----------------------");
        }
    }

}
