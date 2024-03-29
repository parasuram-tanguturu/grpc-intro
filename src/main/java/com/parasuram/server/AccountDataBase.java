package com.parasuram.server;


import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
        This is a DB
        1=>10
        2=>20
        ....
        10=>100
 */
public class AccountDataBase {
    private static final Map<Integer,Integer> MAP= IntStream.rangeClosed(1,10)
            .boxed()
            .collect(
                    Collectors.toMap(Function.identity(),v->v*10)
            );

    public static int getBalance(int acccountId){
        return MAP.get(acccountId);
    }
    public static Integer addBalance(int accountId,int amount){
        return MAP.computeIfPresent(accountId,(k,v)->v+amount);
    }
    public static Integer deductBalance(int accountId,int amount){
        return MAP.computeIfPresent(accountId,(k,v)->v-amount);
    }
}
