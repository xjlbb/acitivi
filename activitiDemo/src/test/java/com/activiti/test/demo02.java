package com.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class demo02 {

    /**
     * 启动流程实例，添加businessKey
     */
    @Test
    public void test01(){
        // 1.获取ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2.获取RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3.启动流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("evection", "1001");
        // 4.输出processInstance相关属性
        System.out.println(instance.getBusinessKey());
    }
}
