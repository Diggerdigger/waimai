ACID，指数据库事务四个基本要素。包含：原子性（Atomicity）、一致性（Consistency）、隔离性（Isolation）、持久性（Durability）
Serializable	  可避免脏读(指一个事务读取了另外一个事务未提交的数据)、不可重复读、虚读(指在一个事务内读取到了别的事务插入的数据)情况的发生。（串行化）
Repeatable read	  可避免脏读、不可重复读情况的发生。（可重复读）
Read committed	  可避免脏读情况发生（读已提交）。
Read uncommitted  最低级别，以上情况均无法保证。(读未提交)

//https://www.cnblogs.com/jasontec/p/9699242.html
//https://blog.csdn.net/Butterfly_resting/article/details/89668661
redis基于内存,亦可持久化的日志型、Key-Value 数据库
分布式一般遵循 CAP 定理.Consistency,Availability,Partition tolerance分区容错
单个 redis 服务器上的请求是顺序执行的，因为 redis 服务器是单进程、单线程的。
Redis支持的数据类型
String   set key value,string类型是二进制安全的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。
Hash     hmset name  key1 value1 key2 value2 存放的是结构化的对象 例子：存储用户信息，以cookieId作为key，设置30分钟为缓存过期时间，能很好的模拟出类似session的效果。
List
Set      sadd  name  value  string类型的无序集合。集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
                            全局去重的功能。为什么不用JVM自带的Set进行去重？因为我们的系统一般都是集群部署，使用JVM自带的Set，
                            比较麻烦，难道为了一个做一个全局去重，再起一个公共服务，太麻烦了。
zset     zadd  name score value

redis的过期策略以及内存淘汰机制    定期删除（redis默认每个100ms随机检查，是否有过期的key,有过期key则删除）
                               +惰性删除策略（获取key的时候，redis检查这个key，如果设置了过期时间并过期了此时就会删除。）。
                               
check-and-set （CAS）

一、什么是分布式锁？
要介绍分布式锁，首先要提到与分布式锁相对应的是线程锁、进程锁。
线程锁：主要用来给方法、代码块加锁。当某个方法或代码使用锁，在同一时刻仅有一个线程执行该方法或该代码段。线程锁只在同一JVM中有效果，
       因为线程锁的实现在根本上是依靠线程之间共享内存实现的，比如synchronized是共享对象头，显示锁Lock是共享某个变量（state）。
进程锁：为了控制同一操作系统中多个进程访问某个共享资源，因为进程具有独立性，各个进程无法访问其他进程的资源，因此无法通过synchronized等线程锁实现进程锁。
分布式锁：当多个进程不在同一个系统中，用分布式锁控制多个进程对资源的访问。