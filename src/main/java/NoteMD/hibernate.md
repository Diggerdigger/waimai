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

Hibernate 允许对关联对象、属性进行延迟加载，但是必须保证延迟加载的操作限于同一个 Hibernate Session 范围之内进行。如果 Service 
层返回一个启用了延迟加载功能的领域对象给 Web 层，当 Web 层访问到那些需要延迟加载的数据时，由于加载领域对象的 Hibernate Session 
已经关闭，这些导致延迟加载数据的访问异常，把一个Hibernate Session和一次完整的请求过程对应的线程相绑定。目的是为了实现
"Open Session in View"的模式。例如： 它允许在事务提交之后延迟加载显示所需要的对象。

Mybatis不完全是ORM框架，因为mybatis需要写sql，mybatis灵活，但无法做到数据库无关性，如果要实现支持多数据库，就要定义多套sql映射，工作量大
Hibernate对象/关系映射能力强，数据库无关性好，但精通门槛高，需要设计O/R映射，在性能和对象模型之间权衡

由于Hibernate的二级缓存是作用在SessionFactory范围内的，因而它比一级缓存的范围更广，可以被所有的Session对象所共享。
在默认情况下，Hibernate会使用EHCache作为二级缓存组件。但是，可以通过设置 hibernate.cache.provider_class属性，
指定其他的缓存策略，该缓存策略必须实现 org.hibernate.cache.CacheProvider接口。

Mybatis
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.some.path.mapper"/>
</bean>