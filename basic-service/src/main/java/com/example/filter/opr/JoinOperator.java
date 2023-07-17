package com.example.filter.opr;

import com.ql.util.express.Operator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 17:03
 * @description:
 */
@Service
public class JoinOperator extends Operator {
    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];
        if(opdata1 instanceof java.util.List){
            ((List)opdata1).add(opdata2);
            return opdata1;
        }else{
            List<Object> result = new ArrayList<>();
            result.add(opdata1);
            result.add(opdata2);
            if(list.length > 2){
                int index = -1;
                for(Object obj:list)
                    if(++index < 2) {
                        continue;
                    }
                    else
                        result.add(obj);
            }
            return result;
        }
    }
}
