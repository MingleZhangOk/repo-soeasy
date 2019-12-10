package com.mingzhang.table.util;

import com.mingzhang.table.enums.FieldType;
import com.mingzhang.table.enums.FlagType;
import com.mingzhang.table.pojo.TableFieldPojo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.types.DataType;
import org.apache.flink.types.Row;

import java.util.List;

public class SchemaUtil {

    public static TableSchema getTableSchema(List<TableFieldPojo> tableFieldPoList) {

        TypeInformation[] typeInformations = new TypeInformation[tableFieldPoList.size()];
        String[] fieldNames = new String[tableFieldPoList.size()];

        for (int i = 0; i < tableFieldPoList.size(); i++) {

            if (FieldType.VARCHAR.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.STRING;
            } else if (FieldType.INTEGER.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.INT;
            } else if (FieldType.DATE.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_DATE;
            } else if (FieldType.DECIMAL.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.BIG_DEC;
            } else if ((FieldType.LONG.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase()))) {
                typeInformations[i] = Types.LONG;
            } else if (FieldType.TIME.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_TIME;
            } else if (FieldType.TIMESTAMP.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_TIMESTAMP;
            } else {
                typeInformations[i] = Types.STRING;
            }
            fieldNames[i] = tableFieldPoList.get(i).getFieldCode();
        }

        return new TableSchema(fieldNames, typeInformations);
    }

    public static TableSchema getTableSchema1(List<TableFieldPojo> tableFieldPoList) {

        TypeInformation[] typeInformations = new TypeInformation[tableFieldPoList.size() + 1];
        String[] fieldNames = new String[tableFieldPoList.size() + 1];

        for (int i = 0; i < tableFieldPoList.size(); i++) {

            if (FieldType.VARCHAR.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.STRING;
            } else if (FieldType.INTEGER.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.INT;
            } else if (FieldType.DATE.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_DATE;
            } else if (FieldType.DECIMAL.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.BIG_DEC;
            } else if ((FieldType.LONG.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase()))) {
                typeInformations[i] = Types.LONG;
            } else if (FieldType.TIME.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_TIME;
            } else if (FieldType.TIMESTAMP.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_TIMESTAMP;
            } else {
                typeInformations[i] = Types.STRING;
            }
            fieldNames[i] = tableFieldPoList.get(i).getFieldCode();
        }
        fieldNames[tableFieldPoList.size()] = "proctime";
        typeInformations[tableFieldPoList.size()] = Types.LOCAL_DATE_TIME;

        return new TableSchema(fieldNames, typeInformations);
    }

    public static String[] getTableFieldNames(List<TableFieldPojo> tableFieldPoList) {
        String[] fieldNames = new String[tableFieldPoList.size()];
        for (int i = 0; i < tableFieldPoList.size(); i++) {
            fieldNames[i] = tableFieldPoList.get(i).getFieldCode();
        }
        return fieldNames;
    }

    public static DataType[] getTableFieldTypes(List<TableFieldPojo> tableFieldPoList) {
        DataType[] dataTypes = new DataType[tableFieldPoList.size()];


        for (int i = 0; i < tableFieldPoList.size(); i++) {

            if (FieldType.VARCHAR.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                dataTypes[i] = DataTypes.STRING();
            } else if (FieldType.INTEGER.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                dataTypes[i] = DataTypes.INT();
            } else if (FieldType.DATE.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                dataTypes[i] = DataTypes.DATE();
            } else if (FieldType.DECIMAL.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                dataTypes[i] = DataTypes.DECIMAL(21, 2);
            } else if ((FieldType.LONG.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase()))) {
                dataTypes[i] = DataTypes.BIGINT();
            } else if (FieldType.TIME.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                dataTypes[i] = DataTypes.DATE();
            } else if (FieldType.TIMESTAMP.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                dataTypes[i] = DataTypes.DATE();
            } else {
                dataTypes[i] = DataTypes.STRING();
            }

        }

        return dataTypes;

    }


    public static TypeInformation<Row> getRowType(List<TableFieldPojo> tableFieldPoList) {

        TypeInformation[] typeInformations = new TypeInformation[tableFieldPoList.size()];
        String[] fieldNames = new String[tableFieldPoList.size()];

        for (int i = 0; i < tableFieldPoList.size(); i++) {

            if (FieldType.VARCHAR.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.STRING;
            } else if (FieldType.INTEGER.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.INT;
            } else if (FieldType.DATE.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_DATE;
            } else if (FieldType.DECIMAL.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.BIG_DEC;
            } else if ((FieldType.LONG.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase()))) {
                typeInformations[i] = Types.LONG;
            } else if (FieldType.TIME.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_TIME;
            } else if (FieldType.TIMESTAMP.getCode().equals(tableFieldPoList.get(i).getFieldType().toUpperCase())) {
                typeInformations[i] = Types.SQL_TIMESTAMP;
            } else {
                typeInformations[i] = Types.STRING;
            }
            fieldNames[i] = tableFieldPoList.get(i).getFieldCode();
        }
        return new RowTypeInfo(typeInformations, fieldNames);

    }

    public static Schema getSchema(List<TableFieldPojo> tableFieldPoList) {

        final Schema schema = new Schema();
        tableFieldPoList.forEach(data -> {
            if (FieldType.VARCHAR.getCode().equals(data.getFieldType().toUpperCase())) {
                schema.field(data.getFieldCode(), Types.STRING);
            } else if (FieldType.INTEGER.getCode().equals(data.getFieldType().toUpperCase())) {
                schema.field(data.getFieldCode(), Types.INT);
            } else if (FieldType.DATE.getCode().equals(data.getFieldType().toUpperCase())) {
                schema.field(data.getFieldCode(), Types.SQL_DATE);
            } else if (FieldType.DECIMAL.getCode().equals(data.getFieldType().toUpperCase())) {
                schema.field(data.getFieldCode(), Types.BIG_DEC);
            } else if ((FieldType.LONG.getCode().equals(data.getFieldType().toUpperCase()))) {
                schema.field(data.getFieldCode(), Types.LONG);
            } else if ((FieldType.TIME.getCode().equals(data.getFieldType().toUpperCase()))) {
                schema.field(data.getFieldCode(), Types.SQL_TIME);
            } else if ((FieldType.TIMESTAMP.getCode().equals(data.getFieldType().toUpperCase()))) {
                schema.field(data.getFieldCode(), Types.SQL_TIMESTAMP);
            } else {
                schema.field(data.getFieldCode(), Types.STRING);
            }
        });
        return schema;
    }

    public static String getTableFieldSKey(List<TableFieldPojo> tableFieldPoList) {
        StringBuffer sb = new StringBuffer();
        for (TableFieldPojo tableFieldPo : tableFieldPoList) {
            if (FlagType.YES.getCode().equals(tableFieldPo.getIsKey())) {
                sb.append("," + tableFieldPo.getFieldCode());
            }

        }

        if (sb.toString().length() > 0) {
            return sb.toString().substring(1);
        } else {
            return null;
        }


    }

    /**
     * 根据tablefield获取对应的列表信息
     *
     * @param tableFieldPoList
     * @return
     */
    public static String getTablefiledNames(List<TableFieldPojo> tableFieldPoList) {
        StringBuffer sb = new StringBuffer();
        boolean isStarter = true;
        for (TableFieldPojo tableFieldPo : tableFieldPoList) {
            if (isStarter) {
                sb.append(tableFieldPo.getFieldCode());
                isStarter = false;
            } else {
                sb.append("," + tableFieldPo.getFieldCode());
            }
        }

        return sb.toString() + ",proctime.proctime";

    }


}
