package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TTT {

    public static long diff(Date startTime, Date endTime){
        final long nd = 1000 * 60 * 60 * 24;
        final long nh = 1000 * 60 * 60;
        final long nm = 1000 * 60;
        long diff = endTime.getTime() - startTime.getTime();
        long result = diff % nd;
        result = result % nh;
        result = result / nm;
        return result;
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("D");
        list.add("物");
        list.add("A");

        Collections.sort(list);
        System.out.println(list);
    }
}
