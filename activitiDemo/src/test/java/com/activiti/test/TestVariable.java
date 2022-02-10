package com.activiti.test;

import com.activiti.pojo.Evection;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestVariable {

    @Test
    public void test01(){

        //1.获取ProcessEngine对象
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        //2.获取RepositoryService进行部署操作
        RepositoryService service = engine.getRepositoryService();
        // 3.使用RepositoryService进行部署操作
        Deployment deploy = service.createDeployment()
                .addClasspathResource("bpmn/evection-varible.bpmn") // 添加bpmn资源
                .addClasspathResource("bpmn/evection-varible.png") // 添加png资源
                .name("出差申请流程-流程变量")
                .deploy();// 部署流程
        // 4.输出流程部署的信息
        System.out.println("流程部署的id:" + deploy.getId());
        System.out.println("流程部署的名称：" + deploy.getName());

    }

    //启动流程实例，设置流程变量
    @Test
    public void test02(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        //流程定义key
        String key = "evection-variable";
        //创建变量集合
        Map<String,Object> variables = new HashMap<>();
        //创建出差对象 POJO
        Evection evection = new Evection();
        //设置出差天数
        evection.setNum(2d);
        //定义流程变量到集合中
        variables.put("evection",evection);
        //设置assignee的取值
        variables.put("assignee0","张三");
        variables.put("assignee1","李四");
        variables.put("assignee2","王五");
        variables.put("assignee3","赵财务");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, variables);
        //输出信息
        System.out.println("获取流程实例名称" + processInstance.getName());
        System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());
    }
}
