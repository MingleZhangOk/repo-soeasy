package com.mingzhang.connectors.common.elasticsearch;
import org.apache.flink.streaming.connectors.elasticsearch.ActionRequestFailureHandler;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.elasticsearch.action.ActionRequest;


/**
 * @author dongbinglin
 */
public class IgnoreFailureHandler implements ActionRequestFailureHandler {

    private static final long serialVersionUID = 1L;

    @Override
    public void onFailure(ActionRequest action, Throwable failure, int restStatusCode, RequestIndexer indexer) throws Throwable {
        // simply fail the sink
    }

}
