package com.mingzhang.table.source.udfsocket;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.typeutils.RowTypeInfo;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname ParseTypeUtil
 * @date 2020-06-02 14:09
 */
public class ParseTypeUtil {

    public static RowTypeInfo getDataType(String[] fields, TypeInformation[] typeInformations) {
        return new RowTypeInfo(typeInformations, fields);
    }

    public static Object getCastDataType(TypeInformation typeInformations, String fieldContent) {
        Object castObject = null;
        if (typeInformations == Types.STRING) {
            castObject = String.valueOf(fieldContent);
        }

        if (typeInformations == Types.INT) {
            castObject = Integer.valueOf(fieldContent);
        }

        if (typeInformations == Types.BIG_DEC) {
            castObject = Double.valueOf(fieldContent);
        }

        if (castObject == null) {
            castObject = String.valueOf(fieldContent);
        }
        return castObject;
    }


}
