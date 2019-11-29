package com.wjn.monitor;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-11-28 17:33
 */
@WebListener
public class CountListener implements HttpSessionListener {
    private int count = 0;
    private int number = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        count ++;
        System.out.println("count:"+count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        number++;
        System.out.println("number:"+number);
    }
}
