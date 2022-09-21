package com.example;

import com.google.common.collect.*;
import com.google.common.eventbus.Subscribe;

import java.util.*;

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
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("1");
        System.out.println(set);
    }
}
