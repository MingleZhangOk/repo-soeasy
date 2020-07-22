package com.mingzhang.demo.datatype;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.types.DataType;

import static org.apache.flink.table.api.DataTypes.*;
/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname DataTypeDemo1
 * @date 2020-06-08 9:51
 */
public class DataTypeDemo1 {


    private void test1(){
        DataType t1 = INTERVAL(DAY(), SECOND(3));

        // tell the runtime to not produce or consume java.time.LocalDateTime instances
// but java.sql.Timestamp
        DataType t2 = DataTypes.TIMESTAMP(3).bridgedTo(java.sql.Timestamp.class);

// tell the runtime to not produce or consume boxed integer arrays
// but primitive int arrays
        DataType t3 = DataTypes.ARRAY(DataTypes.INT().notNull()).bridgedTo(int[].class);
    }
}
