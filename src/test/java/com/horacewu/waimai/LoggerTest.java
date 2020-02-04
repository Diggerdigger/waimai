package com.horacewu.waimai;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void logtest(){
        /*logger.debug("debug..........");
        logger.info("info..........");
        logger.error("error..........");*/
        log.debug("debug..........");
        log.info("info..........");
        log.error("error..........");

    }

    @Test
    public void test2(){
        int a=2;
        String s="aaaaaa";
        change (a);
        change(s);
        System.out.println(a);
        System.out.println(s);
    }

    private void change(String s) {
        s="ppppppp";
    }

    private void change(int a) {
        a=5;
    }

    @Test
    public void testExtend() throws Exception{
        Fu f=new Zi();

    }
}




