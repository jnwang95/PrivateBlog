package com.wjn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static sun.misc.Unsafe.getUnsafe;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-04 09:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestOne {


    public Unsafe getUnsafe() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        return unsafe;
    }

    @Test
    public void test1() throws IllegalAccessException, NoSuchFieldException {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        long size = 5;
        long address = unsafe.allocateMemory(size); //申请内存
        System.out.println(address);

        unsafe.setMemory(address,size,(byte) -1);//设置内存
        System.out.println(unsafe.getByte(unsafe.getByte(address)));//根据地址获取数据

        for (int i = 0 ; i < size ; i ++) {
            unsafe.putByte(address + i,(byte) i);
        }

        for (int i = 0 ; i < size ; i ++) {
            System.out.println(unsafe.getByte(address + i));
        }
        unsafe.freeMemory(address);
    }
}
