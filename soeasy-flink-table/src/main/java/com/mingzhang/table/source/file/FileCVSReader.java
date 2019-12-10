package com.mingzhang.table.source.file;

import com.mingzhang.table.common.CommonUtil;
import com.mingzhang.table.common.FlinkBatchInitDemo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.sources.CsvTableSource;
import org.apache.flink.types.Row;

public class FileCVSReader {

    private static final String fieldDeli = ",";
    private static final String lineDeli = "\n";
    private static final String registerName = "test_cvs";
    private static final String filePath = "E:/home/";
    private static final String fileName = "test.csv";


    public static void main(String[] args) throws Exception {
        registerCSVFileSourceTable("", "");

    }

    public static void registerCSVFileSourceTable(String filePath, String fileName) throws Exception {

        if (StringUtils.isEmpty(filePath)) {
            filePath = FileCVSReader.filePath;
        }
        if (StringUtils.isEmpty(fileName)) {
            fileName = FileCVSReader.fileName;
        }

        TypeInformation<Row> rowType = SchemaUtil.getRowType(FlinkBatchInitDemo.getFieldsList());
        Schema schema = SchemaUtil.getSchema(FlinkBatchInitDemo.getFieldsList());

        CsvTableSource build = CsvTableSource.builder()
                .fieldDelimiter(fieldDeli)
                .lineDelimiter(lineDeli)
                .ignoreParseErrors()
                .quoteCharacter('\"')
                .field("OrgId", Types.STRING)
                .field("TrxTps", Types.LONG)
                .path(CommonUtil.getFullFileName(filePath, fileName))
                .build();

//        TableDescriptor tableDescriptor = tableEnvironment.
//                connect(new FileSystem().path(CommonUtil.getFullFileName(filePath, fileName)))
//                .withFormat(new Csv().schema(rowType).deriveSchema()
//                        .fieldDelimiter(fieldDeli)
//                        .lineDelimiter(lineDeli)
//                        .allowComments()
//                        .ignoreParseErrors()
//                        .arrayElementDelimiter("|")
//                        .nullLiteral("N/A")
//                        .quoteCharacter('\"'))
//                .withSchema(schema);
//        Map map1 = schema.toProperties();
//        Map map2 = build.toProperties();
//        System.out.println(map1);
//        System.out.println(map2);
//
//        CsvBatchTableSourceFactory csvBatchTableSourceFactory = new CsvBatchTableSourceFactory();
//        TableSource tableSource = csvBatchTableSourceFactory.createTableSource(map2);

        FlinkBatchInitDemo.tableEnvironment.registerTableSource(registerName, build);

        FlinkBatchInitDemo.printData("select * from " + registerName);
    }
}
