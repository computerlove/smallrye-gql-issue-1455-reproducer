package org.acme;

import io.smallrye.graphql.api.Context;
import io.smallrye.graphql.spi.EventingService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggingEventservice implements EventingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingEventservice.class);
    private static final Map<String, String> requestContexts = new ConcurrentHashMap<>();
    private static final Map<String, String> fetchContexts = new ConcurrentHashMap<>();

    @Override
    public String getConfigKey() {
        return "LoggingEventservice.enabled";
    }

    @Override
    public void beforeDataFetch(Context context) {
        String fieldNameExecutionId = getFieldNameExecutionId(context);
        log.info("beforeDataFetch " + fieldNameExecutionId );
        fetchContexts.put(fieldNameExecutionId, fieldNameExecutionId);
    }

    @NotNull
    private static String getFieldNameExecutionId(Context context) {
        return context.getOperationName().get() + " " + context.getFieldName() + " " + context.getExecutionId();
    }

    @Override
    public void afterDataFetch(Context context) {
        String fieldNameExecutionId = getFieldNameExecutionId(context);
        String remove = fetchContexts.remove(fieldNameExecutionId);
        log.info("afterDataFetch " + fieldNameExecutionId + " " + remove);
        if(remove == null) {
            log.warn(fieldNameExecutionId + " twice" );
            throw new NullPointerException(fieldNameExecutionId);
        }
    }

    @Override
    public void beforeExecute(Context context) {
        log.info("beforeExecute " + context.getExecutionId());
        requestContexts.put(context.getExecutionId(), context.getExecutionId());
    }

    @Override
    public void afterExecute(Context context) {
        String remove = requestContexts.remove(context.getExecutionId());
        log.info("afterExecute " + context.getExecutionId() + " " + remove);
        if(remove == null) {
            log.warn(context.getExecutionId() + " twice" );
            throw new NullPointerException(context.getExecutionId());
        }
    }
}
