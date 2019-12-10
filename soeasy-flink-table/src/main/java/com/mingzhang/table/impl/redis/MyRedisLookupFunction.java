package com.mingzhang.table.impl.redis;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.table.functions.FunctionContext;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;
import redis.clients.jedis.Jedis;

public class MyRedisLookupFunction extends TableFunction<Row> {
    Jedis jedis;

    private final String[] fieldNames;
    private final TypeInformation[] fieldTypes;

    public MyRedisLookupFunction(String[] fieldNames, TypeInformation[] fieldTypes) {
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
    }

    @Override
    public void open(FunctionContext context) {
        jedis = new Jedis("192.168.56.102", 6379);
        jedis.auth("123456");
    }

    public void eval(Object... paramas) {
        String key = paramas[0].toString();
        String value = jedis.get(key);
        collect(Row.of(value));
    }

    public static final class Builder {
        private String[] fieldNames;
        private TypeInformation[] fieldTypes;

        private Builder() {
        }

        public static MyRedisLookupFunction.Builder getBuilder() {
            return new MyRedisLookupFunction.Builder();
        }

        public MyRedisLookupFunction.Builder withFieldNames(String[] fieldNames) {
            this.fieldNames = fieldNames;
            return this;
        }

        public MyRedisLookupFunction.Builder withFieldTypes(TypeInformation[] fieldTypes) {
            this.fieldTypes = fieldTypes;
            return this;
        }

        public MyRedisLookupFunction build() {
            return new MyRedisLookupFunction(fieldNames, fieldTypes);
        }
    }
}
