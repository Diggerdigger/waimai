持久化类编写注意：
1. 提供无参构造方法，因为hibernate底层需要用反射生成类的实例
2. 属性需要私有，并提供get，set方法，封装用到
3. 属性尽量用包装类
4. 有位移标识OID与表的主键对应
5. 持久化类尽量不要用final修饰，延迟加载机制会产生代理对象，产生代理对象用的是字节码增强技术，就是产生
了当前类的一个子类来实现的，用final修饰不能产生子类

hibernate主键生成策略：increment，identity，sequence，native，uuid，assigined

hibernate持久化对象3种状态：transient瞬时态，persistent持久态，detached脱管态

hibernate一级缓存（session）与快照

hibernate数据库执行api
Query（HQL），Creteria(QBC),DetachedCreteria,SQLQuery

extends HibernateDaoSupport
HibernateTemplate

inverse="true" cascade="all-delete-orphan"
fetch="select" lazy="false"

延迟加载分类级别延迟加载和关联级别延迟加载，类级别的延迟加载一般不做修改，<class>属性采用默认值lazy=“true”
不想采用类界别延迟加载，<class>属性采用默认值lazy=“false”,或者持久化类使用final修饰，使其不能产生子类，导致不能
生成动态代理（目的是方法增强）

<set>标签的lazy属性：true,false,extra（查询聚合函数时只查函数的结果，不讲所有数据查出来放在对象属性中）
<many-to-one>标签的lazy属性：proxy(取决于一的一方的lazy属性),false,no-proxy(基本不用)

<set>标签的fetch（抓取策略）属性：select(默认，普通select查询),join（迫切左外连）,subselect（子查询语句）
<many-to-one>标签的fetch属性：select,join
当fetch为join时，lazy失效，因为已经返回两个表的数据并将返回结果放封装在一个对象中

batch-size批量抓取

full join是left join和right join的集合

inner join fetch迫切内连接，hql中使用，将返回结果放封装在一个对象中
left join fetch迫切左外连接