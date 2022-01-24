package com.activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
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

}
