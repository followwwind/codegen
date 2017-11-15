package com.wind;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");

        System.out.println(list.toArray(new String[list.size()])[0]);
    }
}
