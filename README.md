数据库要存放中文的话用数据库编码用utf8mb4
create database xxx default charset utf8mb4;

测试单元@Transactional表明测试的结果不会放到数据库

@DynamicUpdate  

@Data(相当于@Getter,@Setter,toString方法，要引入lombok依赖和插件)


MyBatis不是ORM，对象-关系映射"(Object/Relational Mapping)，框架，MyBatis只是将数据库中的内容映射为实体。没有将实体映射为数据库中的字段

JPA（Java Persistence API）


@JsonInclude(JsonInclude.Include.NON_NULL)//前端返回json时空的字段不返回
@JsonSerialize(using = Date2LongSerializer.class)

gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>() {}.getType());

public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,BindingResult bindingResult) 
前两门课程