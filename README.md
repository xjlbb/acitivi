# acitivi
Activiti学习

###过程
> 部署activiti  
> 流程定义  
> 流程定义部署  
> 启动一个流程实例  
> 用户查询待办任务（Task）  
> 用户办理任务  
> 流程结束

- pom.xml文件中引入的相关jar包，包括：  
  1.activiti-engine-7.0.0.beta1.jar  
  2.activiti 依赖的 jar 包：mybatis、alf4j、log4j 等  
  3.activiti 依赖的 spring 包  
  4.数据库驱动  
  5.第三方数据连接池 dbcp  
  6.单元测试 Junit-4.12.jar

###绘制插件
idea在2019年之后就没有再更新维护Activiti的设计工具，在高版本的IDEA中没法使用actiBPM插件来绘制  
- 解决方案：1、降低idea版本 2、通过Eclipse安装来实现流程的设计