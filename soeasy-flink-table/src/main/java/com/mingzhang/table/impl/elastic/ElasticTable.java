//package com.mingzhang.table.impl.elastic;
//
//import com.mingzhang.table.pojo.TableFieldPojo;
//import com.mingzhang.table.util.SchemaUtil;
//import org.apache.flink.api.common.typeinfo.TypeInformation;
//import org.apache.flink.api.common.typeinfo.Types;
//import org.apache.flink.api.java.DataSet;
//import org.apache.flink.api.java.ExecutionEnvironment;
//import org.apache.flink.api.java.operators.DataSource;
//import org.apache.flink.api.java.typeutils.RowTypeInfo;
//import org.apache.flink.shaded.hadoop2.org.apache.http.HttpHost;
//import org.apache.flink.table.api.TableSchema;
//import org.apache.flink.table.functions.AsyncTableFunction;
//import org.apache.flink.table.functions.TableFunction;
//import org.apache.flink.table.sources.BatchTableSource;
//import org.apache.flink.table.sources.LookupableTableSource;
//import org.apache.flink.table.types.DataType;
//import org.apache.flink.table.types.utils.TypeConversions;
//import org.apache.flink.types.Row;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.RangeQueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//import java.net.InetAddress;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class ElasticTable implements BatchTableSource<Row>, LookupableTableSource<Row> {
//
//    private static List<HttpHost> list;
//    private static String index;
//    private static String type = "_doc";
//    private static RestHighLevelClient rhlClient;
//    private static SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//    private static List<TableFieldPojo> tableFieldPojo;
//    private static TypeInformation[] types = new TypeInformation[2];
//    private static String[] names = new String[2];
////    private TransportClient transportClient;
//
//
//    public ElasticTable(List<HttpHost> list, String index, List<TableFieldPojo> tableFieldPojo) {
//        ElasticTable.tableFieldPojo = tableFieldPojo;
//        ElasticTable.list = list;
//        ElasticTable.index = index;
//        rhlClient = getHighClient(list);
////        try {
////            transportClient = getBasicLowClientInfo(list);
////        } catch (Exception e) {
////
////        }
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(10001);
//        types[0] = Types.STRING;
//        types[1] = Types.LONG;
//        names[0] = "f0";
//        names[1] = "f1";
//        RangeQueryBuilder queryBuilder =
//                QueryBuilders.rangeQuery("SysTime.keyword")
//                        .gte("201911151141").lte("201911151611").format("yyyyMMddHHmm||yyyyMMddHHmm");
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(queryBuilder);
//        searchSourceBuilder.query(boolQueryBuilder);
//    }
//
//    private RestHighLevelClient getHighClient(List<HttpHost> list) {
////        RestClientBuilder builder = RestClient.builder(list.toArray(new HttpHost[list.size()]));
//        RestClientBuilder builder = null;
//        RestHighLevelClient rhlClient = new RestHighLevelClient(builder);
////        if (!rhlClient.ping()) {
////            throw new Exception("确保ES地址是否正确");
////        }
//        return rhlClient;
//    }
//
//    private static TransportClient getBasicLowClientInfo(List<HttpHost> list) throws Exception {
//
//        Settings settings = Settings.builder()
//                .put("cluster.name", "my-application")
////                .put("client.transport.sniff", "")
//                .build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddresses(new TransportAddress(InetAddress.getByName("192.168.56.102"),
//                9200));
//
//        return client;
//    }
//
//    public TypeInformation getReturnType() {
//        return SchemaUtil.getRowType(tableFieldPojo);
//    }
//
//    @Override
//    public TableSchema getTableSchema() {
////    SchemaUtil.getTableSchema(tableFieldPojo);
//        return TableSchema.builder()
//                .fields(names, TypeConversions.fromLegacyInfoToDataType(types))
//                .build();
//    }
//
//    @Override
//    public DataSet<Row> getDataSet(ExecutionEnvironment executionEnvironment) {
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        //精准匹配字段值
//        //TermQueryBuilder queryBuilder = QueryBuilders.termQuery("", "");
//        //区间匹配字段
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse response = null;
//
//        try {
////            response = transportClient.search(searchRequest).actionGet();
//            response = rhlClient.search(searchRequest);
//        } catch (Exception e) {
//
//        }
//        SearchHits hits = response.getHits();
//        SearchHit[] hits1 = hits.getHits();
//
//        List<Row> list = new ArrayList();
//
//        for (int i = 0; i < hits1.length; i++) {
//            Row row = new Row(tableFieldPojo.size());
//            Map<String, Object> sourceAsMap = hits1[i].getSourceAsMap();
//            for (int j = 0; j < tableFieldPojo.size(); j++) {
//                Object o = sourceAsMap.get(tableFieldPojo.get(j).getFieldCode());
//                if (tableFieldPojo.get(j).getFieldType().equalsIgnoreCase("VARCHAR")) {
//                    o = (String) o;
//                } else {
//                    o = Long.valueOf(o.toString());
//                }
//                row.setField(j, o);
//            }
//            list.add(row);
//        }
//
////        if (null != rhlClient) {
////            try {
////                rhlClient.close();
////            } catch (Exception e) {
////
////            }
////        }
//        DataSource<Row> rowDataSource = executionEnvironment.fromCollection(list);
//        return rowDataSource;
//    }
//
//    @Override
//    public DataType getProducedDataType() {
////        LogicalType[] logicalTypes = {new VarCharType(20), new BigIntType(true)};
////        DataType[] dataTypes = TypeConversions.fromLogicalToDataType(logicalTypes);
//        return TypeConversions.fromLegacyInfoToDataType(new RowTypeInfo(types, names));
//    }
//
//    @Override
//    public TableFunction<Row> getLookupFunction(String[] strings) {
//        return null;
//    }
//
//    @Override
//    public AsyncTableFunction<Row> getAsyncLookupFunction(String[] strings) {
//        return null;
//    }
//
//    @Override
//    public boolean isAsyncEnabled() {
//        return true;
//    }
//
////    @Override
////    public DataType getProducedDataType() {
////        List<RowType.RowField> fields = new ArrayList<>();
////
////        fields.add(new RowType.RowField("OrgId", new VarCharType(20)));
//////        fields.add(new RowType.RowField("OrgId", new AnyType<>(String.class, new StringSerializer())));
////        fields.add(new RowType.RowField("TrxTps", new BigIntType()));
////
////        HashMap<String, DataType> stringDataTypeHashMap = new HashMap<>();
////        stringDataTypeHashMap.put("OrgId",new VarCharType(20));
////
////
////        return new FieldsDataType(new RowType(true, fields), Row.class,);
////    }
//
//}
