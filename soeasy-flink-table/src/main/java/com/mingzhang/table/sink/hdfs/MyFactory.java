package com.mingzhang.table.sink.hdfs;

import org.apache.flink.api.common.serialization.BulkWriter;
import org.apache.flink.core.fs.FSDataOutputStream;
import org.apache.flink.types.Row;

import java.io.IOException;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-04-24 15:18
 */
public class MyFactory implements DayBulkWriter.Factory {
    @Override
    public BulkWriter<Row> create(FSDataOutputStream var1) throws IOException {
        return null;
    }
}
