package com.myblog.myblog11;


import io.micrometer.observation.Observation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestClass1 {
    public static void main(String[] args) {
        //CONSUMER FUNCTIONAL:
        //consumer functional interface it takes input but produces no output
//        Consumer con = str-> System.out.println(str);
//        con.accept("HII");

//        // to pass the values to consumer we need to pass it via loop because it takes only one input
//        List <String> names = Arrays.asList("Picolo","Gohan","Vegita","Chichi","Reolu");
//        //way one
//        Consumer <String> con =li-> System.out.println(li);
//        names.forEach(con);
//        //way second
//        names.stream().forEach(li-> System.out.println(li));

        //Supplier:
        //supplier does not take input but gives output

        Supplier <Integer> sup = () -> new Random().nextInt(100);
        System.out.println(sup.get());
    }
    }