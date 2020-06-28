# StarterDemo
 wannukkit-springboot-starter 的一个使用demo，推荐使用 demo 搭建你自己的项目。
 

#### 注意
  1. （重要）日志文件配置，自行配置。所有的日志输出已经被修改， server.log 不会再保存任何信息。
  2. （非常重要）pom 文件，需要特别注意与项目相关的地方。顶部 - groupId、artifactId，底部 - mainClass 。
  3. （可选）启动类继承 NukkitApplicationLauncher 类。
  4. （不重要）日志的使用，可以参考主类 StarterDemo 类的用法。
  5. （重要）非常不推荐在 nukkit 启动完成前获取 nukkit 内部的对象
  