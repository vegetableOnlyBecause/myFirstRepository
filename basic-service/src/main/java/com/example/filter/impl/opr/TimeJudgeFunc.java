package com.example.filter.impl.opr;

import com.example.common.utils.OprUtils;
import com.example.filter.enums.TimeOprEnums;
import com.ql.util.express.Operator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @title:
 * @author: vegetableOnlyBecause
 * @date 2023/7/12 17:03
 * @description:
 */
@Service
public class TimeJudgeFunc extends Operator {
    private static final long serialVersionUID = -1187796060851750607L;

    public Object executeInner(Object[] list) {
        Date time1 = (Date) list[0];
        String judgeType = (String)list[1];
        String time2Str = (String)list[2];
        // 获取Date类型
        Date time2 = OprUtils.str2Date(time2Str);
        if (null == time1 || null == time2 || StringUtils.isBlank(judgeType)) {
            return false;
        }
        // 获取枚举类型
        TimeOprEnums oprType = OprUtils.getEnumFromString(TimeOprEnums.class, judgeType);
        return getResultByOprType(oprType, time1, time2);
    }

    private boolean getResultByOprType(TimeOprEnums oprType,
                                       Date time1, Date time2) {
        if (null == oprType) {
            return false;
        }
        switch (oprType) {
            case before:
                return time1.before(time2);
            case beforeAndEquals:
                return time1.before(time2) || time1.equals(time2);
            case after:
                return time1.after(time2);
            case afterAndEquals:
                return time1.after(time2) || time1.equals(time2);
            case equals:
                return time1.equals(time2);
            default:
                return false;
        }
    }
}
