前端控制器
<servlet>
        <servlet-name>mvc-dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:/SpringMVC.xml</param-value>
        </init-param>
        <load-on-startup>2</load-on-startup>
</servlet>

请求->DispatcherServlet->HandlerMapping(处理器映射器)->DispatcherServlet->
HandlerAdapter(处理器适配器)->Controler/Handler(处理器)->HandlerAdapter->DispatcherServlet->
ViewResolver->DispatcherServlet->jsp\freemarker
    
    <!-- 启用SpringMVC的注解功能,它会自动注册HandlerMapping、HandlerAdapter、ExceptionResolver的相关实例 -->
    <!--处理请求时返回json字符串的中文乱码问题-->
    <mvc:annotation-driven>
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
    视图解析器（jsp,freemarker或者velocity需要配置其他解析器）
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF?/......." />
        <property name="suffix" value=".jsp" />
    </bean>
        
springmvc与struts2的不同
1.springmvc的入口是一个servlet即前端控制器，而struts2入口时一个filter过滤器
2.springmvc时基于方法开发，请求参数传递到方法的形参，建议设计为单例，sruts2时基于类开发，传递参数基于类的属性，只能设计为多例
3.struts2采用栈存储请求和响应的数据，通过OGNL存取数据，springMVC通过参数解析器讲request解析，给方法形参赋值，将数据和视图封装成
ModelAndView对象，最后又将ModelAndView里的模型数据通过request域传到页面

springMVC异常实现HandlerExceptionResolver
public class GlobalExceptionReslover implements HandlerExceptionResolver

<!-- 配置文件上传解析器 -->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="maxUploadSize" value="30971520"/>
    </bean>
    
springMVC拦截器要实现HandlerInterceptor,并重写以下方法：
preHandler:按拦截器定义顺序调用
postHandler:按拦截器定义逆序调用，在拦截器链内所有拦截器返成功调用
afterCompletion:按拦截器定义逆序调用，只有preHandle返回true才调用

@RequestMapping("/item/{itemId}")
	@ResponseBody
	private TbItem getItemById(@PathVariable Long itemId) {}