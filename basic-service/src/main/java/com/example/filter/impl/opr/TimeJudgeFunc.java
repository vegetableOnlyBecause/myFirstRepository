package com.example.filter.impl.opr;

import com.example.common.utils.OprUtils;
import com.example.filter.enums.TimeOprEnums;
import com.ql.util.express.Operator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @title: 时间判断自定义方法
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
        Date time2 = OprUtils.str2Date((String)list[2]);
        return null != time1 && null != time2 && StringUtils.isNotBlank(judgeType)
                && getResultByOprType(judgeType, time1, time2);
    }

    /**
     * 根据判断类型获取结果
     * @param judgeType 判断类型
     * @param time1 Date1
     * @param time2 Date2
     * @return 结果
     */
    private boolean getResultByOprType(String judgeType, Date time1, Date time2) {
        TimeOprEnums oprType = OprUtils.getEnumByName(TimeOprEnums.class, judgeType);
        return Optional.ofNullable(oprType)
                .map(type -> type.getBiFunction().apply(time1, time2)).orElse(false);
    }
}
