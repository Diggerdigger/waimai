package com.horacewu.waimai.repository;

import com.horacewu.waimai.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void test(){
        ProductCategory one = repository.findOne(1);
        System.out.println(one.getCategoryName());
        one.setCategoryName("ddddddddd");
        repository.save(one);
    }

    @Test
    //@Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("哈哈", 4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> typelist = Arrays.asList(0, 2);
        List<ProductCategory> list = repository.findByCategoryTypeIn(typelist);
        Assert.assertNotEquals(0,list.size());
    }
}