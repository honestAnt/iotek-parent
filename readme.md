1、pom重构
	1.引入profile概念。
    	1)部署模式。
  		2)开发模式。(默认启动)
  	
  	2.规范maven结构。
  		1)手动指定依赖和插件，规避老版本依赖和插件不兼容的问题。
  		2)手动指定编码和编译环境，统一使用UTF-8和JDK1.6.
  		3)指定公共属性便于升级，如struts2、spring的版本。
  	
  	3.引入数据库重构。[暂缓]
  		1.加入sql系统脚本和测试脚本到src/main/resources目录下。
  		2.通过ant任务插件生成数据库、数据表和初始数据。