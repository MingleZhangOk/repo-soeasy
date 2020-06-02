package com.mingzhang.table.source.udfsocket;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.types.Row;

import java.io.Serializable;
import java.net.Socket;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname SouceParaSocket
 * @date 2020-06-01 16:05
 */
public class SouceParaSocket extends RichParallelSourceFunction<Row> implements Serializable {

    private static final String[] fields = new String[]{"id", "name", "age"};
    private static final TypeInformation[] typeInformations = new TypeInformation[]{Types.STRING, Types.STRING, Types.INT};

    private static MySocketThread SOKECT_THREAD;
    private static String hosts = "172.16.60.51";
    private static int port = 6868;
    private static final long serialVersionUID = 1L;
    private volatile boolean isRunning = true;


    static {
        try {
            SOKECT_THREAD = new MySocketThread(new Socket(hosts, port));
        } catch (Exception e) {
            System.out.println("hosts:" + hosts + ",port:" + port + ",连接异常！！");
        }
    }


    @Override
    public void run(SourceContext<Row> sourceContext) throws Exception {
        String messages;
//        while (isRunning) {
        if ((messages = SOKECT_THREAD.reader.readLine()) != null) {
//                sourceContext.collectWithTimestamp(Row.of(messages), System.currentTimeMillis());
            System.out.println(messages);

//                String[] fields = {"id", "name", "age"};
//                TypeInformation[] typeInformations = {Types.STRING, Types.STRING, Types.INT};
//                TypeInformation<Row> rowType = SchemaParse.getRowType(fields, typeInformations);
            String[] fields = messages.split(",");
            Row row = new Row(fields.length);
            for (int i = 0; i < fields.length; i++) {
                row.setField(i, ParseTypeUtil.getCastDataType(typeInformations[i], fields[i]));
            }
            sourceContext.collectWithTimestamp(row, System.currentTimeMillis());
            sourceContext.emitWatermark(new Watermark(System.currentTimeMillis()));
        }
        Thread.sleep(10000);
//        }
    }


    @Override
    public void cancel() {
        isRunning = false;
        if (SOKECT_THREAD != null) {
            SOKECT_THREAD.close();
        }
    }
}