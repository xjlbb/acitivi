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

>- pom.xml文件中引入的相关jar包，包括：  
  1.activiti-engine-7.0.0.beta1.jar  
  2.activiti 依赖的 jar 包：mybatis、alf4j、log4j 等  
  3.activiti 依赖的 spring 包  
  4.数据库驱动  
  5.第三方数据连接池 dbcp  
  6.单元测试 Junit-4.12.jar
  <br>
####表的命名规则和作用
- ACT_RE ：'RE'表示 repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等
  等）。
- ACT_RU：'RU'表示 runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的
  数据。 Activiti 只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时
  表可以一直很小速度很快。
- ACT_HI：'HI'表示 history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
- ACT_GE ： GE 表示 general。 通用数据， 用于不同场景下  


> ###绘制插件
> idea在2019年之后就没有再更新维护Activiti的设计工具，在高版本的IDEA中没法使用actiBPM插件来绘制  
> - 解决方案：1、降低idea版本 2、通过Eclipse安装来实现流程的设计  
  
> ###idea相关问题
> New 中无 XML Configuration File 选项，idea社区版不支持  
  
> ###如何推代码到测试环境上打tag步骤
> - 先把测试好的分支合到develop（目前自己干的项目），然后在idea的git点击new tag（在develop分支上），把目前自己的分支版本号填上，去drone看是否推成功，最后在rancher上测试环境更新（步骤：1.点击服务的 Editor按钮 2.将版本号修改成tag的 3.)    


> 移除本地tag: git tag -d 3.7.0(tag版本号)  
> 移除远程tag: git push origin :refs/tags/3.7.0
