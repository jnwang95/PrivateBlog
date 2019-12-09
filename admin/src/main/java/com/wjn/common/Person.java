package com.wjn.common;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-04 09:28
 */
//TODO 待删除，操作内存的方法
@Data
public class Person {
    public static String NAME = "doge";
    public String age;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String str = "HelloWord";
        String reverse = StrUtil.reverse(str);
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        long size = 5;
        long address = unsafe.allocateMemory(size); //申请内存
        System.out.println(address);

        unsafe.setMemory(address,size,(byte) -1);//设置内存
        System.out.println(unsafe.getByte((address)));//根据地址获取数据

        for (int i = 0 ; i < size ; i ++) {
            unsafe.putByte(address + i,(byte) i);
        }

        for (int i = 0 ; i < size ; i ++) {
            System.out.println(unsafe.getByte(address + i));
        }
        unsafe.freeMemory(address);
    }
}
