package com.myblog.myblog11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass {
    public static void main(String[] args) {
//        Note : code : 1
//        Predicate<Integer> val = y-> y%2==0;
//        boolean bb = val.test(20);
//        System.out.println(bb);

 //        Note : code : 2
//       Predicate <String> pp = y->y.equals("mike");
//        boolean bb =  pp.test("Shubham");    // Shubham <> mike
//        System.out.println(bb);

        // Note:flow--> DataStructureName(list,map,....).stream().pipeLikeStructure
//      List<Integer> li =  Arrays.asList(1,5,66,4,77,88);
//        List<Integer> li1 =        li.stream().filter(n->n%2!=0).collect(Collectors.toList());
//        System.out.println("odd numbers are:"+li1);

//        List<String> li =  Arrays.asList("Shubham","Sneha","Valmik","Nirmala","Shubham","Shubham");
//        // starts with S
//        List<String> li2 = li.stream().filter(l->l.startsWith("S")).collect(Collectors.toList());
//        System.out.println(li2);
//        // same name as Shubham
//        List<String> li3 = li.stream().filter(l->l.equals("Shubham")).collect(Collectors.toList());
//        // count above
//        long count = li3.stream().count();
//        System.out.println(li3);
//        System.out.println(count);

//        List<String> li =  Arrays.asList("Shubham","Sneha","Valmik","Nirmala","Shubham","Shubham");
//        Function <Integer,Integer>func= i->i+5;
//        Integer apply = func.apply(66);
//        System.out.println(apply);

//        //FUNTIONAL INTERFACE-->
//       //code 1
//       // function funtional interface takes one input then generates an output
//        //here string input wwe are passing and Integer output we are getting in ths lamda
//        Function<String,Integer> result= str->str.length();
//        Integer shubham = result.apply("Shubham");
//        System.out.println(shubham);

// //code2
//        Function <Integer,Integer> fun = str->str%2;
//        System.out.println( fun.apply(77));


//        //code 3:
//        List<Integer> li = Arrays.asList(12,88,99,6,5,44,1,2);
//        List<Integer> collect = li.stream().map(intr -> intr+10).collect(Collectors.toList());
//        System.out.println(collect);
//
//        List<String> li1 = Arrays.asList("Shubham","Amit","Viky","Nirmala",1,5,11,4);
//        List<String> collect1 = li1.stream().map(i->i.toUpperCase()).sorted().collect(Collectors.toList());
//        System.out.println(collect1);

        List<Integer> li1 = Arrays.asList(2,8,5,2,1,8,5,8);
        List<Integer> collect1 = li1.stream().distinct().collect(Collectors.toList());
        System.out.println(collect1);

    }
    }