Connection接口:
— List 有序,可重复
ArrayList  优点: 底层数据结构是数组，查询快，增删慢。缺点: 线程不安全，效率高
Vector     优点: 底层数据结构是数组，查询快，增删慢。缺点: 线程安全，效率低
LinkedList 优点: 底层数据结构是链表，查询慢，增删快。缺点: 线程不安全，效率高

—Set 无序,唯一
HashSet       底层数据结构是哈希表。(无序,唯一)  如何来保证元素唯一性?  1.依赖两个方法：hashCode()和equals()
LinkedHashSet 底层数据结构是链表和哈希表。(FIFO(先进先出)插入有序,唯一) 1.由链表保证元素有序 2.由哈希表保证元素唯一
TreeSet       底层数据结构是红黑树。(唯一，有序，主要用于排序)  1. 如何保证元素排序的呢? 自然排序  比较器排序  
                                                            2.如何保证元素唯一性的呢? 根据比较的返回值是否是0来决定
Ordering: HashSet不保证有序，LinkHashSet保证FIFO即按插入顺序排序，TreeSet安装内部实现排序，也可以自定义排序规则
null:HashSet和LinkHashSet允许存在null数据，但是TreeSet中插入null数据时会报NullPointerException

基本类型和包装类型的区别
01、包装类型可以为 null，而基本类型不可以
02、包装类型可用于泛型，而基本类型不可以
03、基本类型比包装类型更高效
04、两个包装类型的值可以相同，但却不相等
05、自动装箱和自动拆箱
自动装箱是通过 Integer.valueOf() 完成的；自动拆箱是通过 Integer.intValue() 完成的。
当需要进行自动装箱时，如果数字在 -128 至 127 之间时，会直接使用缓存中的对象，而不是重新创建一个对象。

1.可变与不可变：String底层使用final修饰的字符数组来存储字符串，它属于不可变类，对String对象的任何改变操作都不会改变原对象，而是生
成一个新对象。StringBuilder和StringBuffer有一个共同的抽象父类AbstractStringBuiler，它们底层是一个不用final修饰的字符数组，因此它们是可变的。
2.是否线程安全:String由final修饰，因此必然是线程安全的。StringBuilder与StringBuffer的唯一区别就是StringBuffer的方法都加上了
syncharnized，因此StringBuffer是线程安全的。
3.执行效率：StringBuilder > StringBuffer > String