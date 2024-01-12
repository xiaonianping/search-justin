package com.justin.search.utils;

/**
 * @author justin_xiao
 * @create 2022-11-24
 **/
public class BeanSizeUtils {

    public static void main(String[] args) {
        final String property = System.getProperty("java.vm.name");
        System.out.println(property);
        System.setProperty("java.vm.name", "Java HotSpot(TM) ");

    }

}
