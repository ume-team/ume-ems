【启动WEB服务器】

方法A (在Eclipse中启动)
  1 运行前提：
    * Eclipse版本4.5或以上
    * JDK版本1.7或以上
    * Apache Maven版本3.3.3或以上
  2.选择项目后右键点击，弹出菜单中选择 [Run As] > [Maven install]进行编译，编译成功后执行如下操作；
    选择项目后鼠标右键点击，弹出菜单中选择 [Run As] > [Maven build]，在弹出窗口的[Name:]栏中输入便于记忆的名称，
    然后在[Goals:] 栏中输入：spring-boot:run
    点击 [Run] 按钮运行。

方法B (命令行执行jar文件)
  1 运行前提：
    * JDK版本1.7或以上(可在命令行输入命令 java -version 进行确认)

  2.打开命令行窗口，进入项目跟目录下的target目录，运行
    java -jar {可执行jar文件名}
  
【Web服务测试】
打开浏览器窗口(浏览器推荐使用Firefox或Chrome，安装Json-handle插件以编译查看JSON格式的数据)，
输入 http://localhost:8081/ume-ems 打开默认页面(查看当前Web服务的状态)。

或者直接输入如下的Web服务的URL，进行数据访问，默认开启的URL如下：
# 默认启动的Web服务的列表及参数描述
http://localhost:8081/ume-ems/capi/sys/EMS99901/

# Web服务的样例
## Sequence
http://localhost:8081/ume-ems/capi/sys/EMS00000/["MSG"]

## login
http://localhost:8081/ume-ems/capi/sys/EMS00001/["admin","88888888"]

## logout
http://localhost:8081/ume-ems/capi/sys/EMS00002/["admin"]

## Table DESC
http://localhost:8081/ume-ems/capi/sys/EMS30001/["DEFAULT","umedbm01"]
http://localhost:8081/ume-ems/capi/sys/EMS30001/["DEFAULT","umedbm01","ROLE"]
http://localhost:8081/ume-ems/capi/sys/EMS30002/["DEFAULT","umedbm01","UME_USER"]

## Entity DESC
http://localhost:8081/ume-ems/capi/sys/EMS10001/["UME_ROLE_ACL"]
http://localhost:8081/ume-ems/capi/sys/EMS10002/["UME_ROLE_ACL"]
http://localhost:8081/ume-ems/capi/sys/EMS10003/["UME_ROLE_ACL","ACC_LEVEL"]
http://localhost:8081/ume-ems/capi/sys/EMS10010/["UME_ROLE_ACL"]

## Resource Management
#http://localhost:8081/ume-ems/capi/sys/EMS40001/["DCISZS","CIS", "TESTG01", "USR", 5]
#http://localhost:8081/ume-ems/capi/sys/EMS40002/["TESTG01", "USR"]
#http://localhost:8081/ume-ems/capi/sys/EMS40003/["TESTG01"]
http://localhost:8081/ume-ems/capi/sys/EMS40004/["DS99","123^56^136^145","3306","umedbm01","ume","umePW123!!","mysql"]

## SQL console query
http://localhost:8081/ume-ems/capi/sys/EMS90031/["DEFAULT","UME_USER","*",{"user_status":">0"}]
http://localhost:8081/ume-ems/capi/sys/EMS90032/["DEFAULT","UME_USER","*",{"user_status":">0"}]



