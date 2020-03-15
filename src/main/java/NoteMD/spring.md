<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
ContextLoaderListener继承自ContextLoader，实现的是ServletContextListener接口。
ContextLoaderListener的作用就是启动Web容器时，读取在contextConfigLocation中定义的xml文件，
自动装配ApplicationContext的配置信息，并产生WebApplicationContext对象，然后将这个对象放置在
ServletContext的属性里，这样我们只要得到Servlet就可以得到WebApplicationContext对象，并利用
这个对象访问spring容器管理的bean。简单来说，就是上面这段配置为项目提供了spring支持，初始化了Ioc容器。

<context:component-scan base-package="com.*"></context:component-scan>注解扫描

spring Aop的底层采用两种代理机制：
JDK的动态代理，针对实现了接口的类产生代理
Cglib的动态代理：针对没有实现接口的类产生代理，底层用的是字节码增强的技术，生成当前类的子类对象
<aop:aspectj-autoproxy />有一个proxy-target-class属性，默认为false，表示使用jdk动态代理织入增强，
当配为<aop:aspectj-autoproxy  poxy-target-class="true"/>时，表示使用CGLib动态代理技术织入增强。
不过即使proxy-target-class设置为false，如果目标类没有声明接口，则spring将自动使用CGLib动态代理。

e. 织入（Weaving）：织入是将增强添加到目标类具体连接点上的过程，
AOP有三种织入方式：①编译期织入：需要特殊的Java编译期（例如AspectJ的ajc）；
②装载期织入：要求使用特殊的类加载器，在装载类的时候对类进行增强；
③运行时织入：在运行时为目标类生成代理实现增强。
Spring采用了动态代理的方式实现了运行时织入，而AspectJ采用了编译期织入和装载期织入的方式。


事务的传播

读取配置文件位置<context:property-placeholder location="classpath:config.properties" ignore-unresolvable="true"/>  

<!-- 事务管理器配置 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>
	
	<!-- 使用annotation定义事务 -->
	<tx:annotation-driven transaction-manager="transactionManager" />


从最基本的XmlBeanFactory看起，它是容器系列的最底层实现，这个容器的实现与我们在Spring应用中用到的那些
上下文相比，有一个非常明显的特点，它只提供了最基本的IoC容器的功能。从它的名字中可以看出，这个IoC容器可
以读取以XML形式定义的BeanDefinition。理解这一点有助于我们理解ApplicationContext与基本的BeanFactory
之间的区别和联系。我们可以认为直接的BeanFactory实现是IoC容器的基本形式，而各种ApplicationContext的实
现是IoC容器的高级表现形式。

BeanDefinition
BeanDefinitionParserDelegate

IoC容器的初始化包括BeanDefinition的Resouce定位、载入和注册这三个基本的过程。
我觉得重点是在载入和对BeanDefinition做解析的这个过程。
