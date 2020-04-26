package com.mingzhang.table.sink.hdfs;

import org.apache.flink.api.common.serialization.BulkWriter;
import org.apache.flink.core.fs.FSDataOutputStream;
import org.apache.flink.types.Row;
import org.apache.flink.util.Preconditions;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-04-24 11:19
 */
public class DayBulkWriter implements BulkWriter<Row> {

    Charset charset = StandardCharsets.UTF_8;
    FSDataOutputStream stream = null;

    public DayBulkWriter DayBulkWriter(FSDataOutputStream inputStream) {
        this.stream = Preconditions.checkNotNull(inputStream);
        return this;
    }

    @Override
    public void addElement(Row row) throws IOException {
        this.stream.write(row.toString().getBytes(charset));
    }

    @Override
    public void flush() throws IOException {
        this.stream.flush();
    }

    @Override
    public void finish() throws IOException {
        this.flush();
    }

    @FunctionalInterface
    public interface Factory extends Serializable {
        BulkWriter<Row> create(FSDataOutputStream var1) throws IOException;
    }



}
