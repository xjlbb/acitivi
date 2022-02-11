package com.activiti.test;

import com.activiti.pojo.Evection;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;

public class TestExclusive {

    @Test
    public void test01(){
        //1.获取ProcessEngine对象
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        //2.获取RepositoryService进行部署操作
        RepositoryService service = engine.getRepositoryService();
        // 3.使用RepositoryService进行部署操作
        Deployment deploy = service.createDeployment()
                .addClasspathResource("bpmn/evection-exclusive.bpmn") // 添加bpmn资源
                .addClasspathResource("bpmn/evection-exclusive.png") // 添加png资源
                .name("出差申请单-排他网关")
                .deploy();// 部署流程
        // 4.输出流程部署的信息
        System.out.println("流程部署的id:" + deploy.getId());
        System.out.println("流程部署的名称：" + deploy.getName());

    }

    @Test
    public void test02(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("evection-exclusive");
        System.out.println("获取流程实例名称：" + processInstance.getName());
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例的ID：" + processInstance.getId());
        System.out.println("当前活动的ID：" + processInstance.getActivityId());
    }

    @Test
    public void test06(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();
        String userId = "lisi";
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("evection-exclusive")
                .taskAssignee(userId)
                .singleResult();
        Evection evection = new Evection();
        evection.setNum(2d);
        HashMap<String, Object> map = new HashMap<>();
        map.put("evection",evection);

        // 完成任务
        if (task != null){
            taskService.complete(task.getId(),map);
        }
    }
}
