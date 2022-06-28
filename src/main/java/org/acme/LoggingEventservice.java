package org.acme;

import io.smallrye.graphql.api.Context;
import io.smallrye.graphql.spi.EventingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;

public class LoggingEventservice implements EventingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingEventservice.class);
    private static final Map<String, String> requestContexts = Collections.synchronizedMap(new IdentityHashMap<>());

    @Override
    public String getConfigKey() {
        return "LoggingEventservice.enabled";
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
