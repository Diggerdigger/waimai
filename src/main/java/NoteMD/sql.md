去重：
create ......select......group by ......

用union all/union代替or

用 exists 代替 in 是一个好的选择：
select num from a where num in(select num from b)用下面的语句替换：select num from a where exists(select 1 from b where num=a.num)
select cardno from fb_inoculation where companyid in (select companyid from fs_company where dist_id=?)
select cardno from fb_inoculation a where exists(select 1 from fs_company where companyid=a.companyid and  dist_id=? )


应尽量避免在 where 子句中对字段进行表达式操作，这将导致引擎放弃使用索引而进行全表扫描
select id from t where num/2=100 应改为:select id from t where num=100*2

应尽量避免在where子句中对字段进行函数操作，这将导致引擎放弃使用索引而进行全表扫描
select id from t where substring(name,1,3)='abc'--name以abc开头的id  select id from t where name like 'abc%'
select id from t where datediff(day,createdate,'2005-11-30')=0--'2005-11-30'生成的id   select id from t where createdate>='2005-11-30' and createdate<'2005-12-1'

模糊查询最好前面不要用%，只在字段的后面用模糊查询
select id from t where name like '%abc%'

select id from t where num in(1,2,3)   select id from t where num between 1 and 3

应尽量避免在 where 子句中使用 !=或<> 操作符，否则将引擎放弃使用索引而进行全表扫描。

应尽量避免在 where 子句中对字段进行 null 值 判断，否则将导致引擎放弃使用索引而进行全表扫描，要设置默认值，不用null

永远别要用复杂的mysql语句来显示你的聪明。就我来说，看到一次关联了三，四个表的语句，只会让人觉得很不靠谱。 

一个表的索引数最好不要超过6个

任何地方都不要使用 select * from t ，用具体的字段列表代替“*”，不要返回用不到的任何字段。

当数据量大时，避免使用where 1=1的条件。通常为了方便拼装查询条件，我们会默认使用该条件，数据库引擎会放弃索引进行全表扫描。如下：
SELECT * FROM t WHERE 1=1 优化方式：用代码拼装sql时进行判断，没where加where，有where加and。

INNODB和BERKLEYDB（BDB）数据库引擎都是造就MYSQL灵活性的技术的直接产品，这项技术就是MYSQL++ API。在使用MYSQL的时候，你所面对的每
一个挑战几乎都源于ISAM和MYISAM数据库引擎不支持事务处理也不支持外来键。尽管要比ISAM和MYISAM引擎慢很多，但是INNODB和BDB包括了对事
务处理和外来键的支持，这两点都是前两个引擎所没有的。