package com.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

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

}
