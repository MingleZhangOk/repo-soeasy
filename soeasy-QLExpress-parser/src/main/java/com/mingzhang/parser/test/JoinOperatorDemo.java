package com.mingzhang.parser.test;

import com.ql.util.express.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 17:32
 */
public class JoinOperatorDemo extends Operator {

    @Override
    public Object executeInner(Object[] list) throws Exception {
        if (list.length > 2) {
            List result = new ArrayList();
            for (int i = 0; i < list.length; i++) {
                result.add(list[i]);
            }
            return result;
        } else {
            Object opdata1 = list[0];
            Object opdata2 = list[1];
            if (opdata1 instanceof List) {
                ((List) opdata1).add(opdata2);
                return opdata1;
            } else {
                List result = new ArrayList();
                result.add(opdata1);
                result.add(opdata2);
                return result;
            }
        }
    }
}