package org.acme.client;

import io.quarkus.runtime.Startup;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Startup
@ApplicationScoped
public class EagerAppBean {

    private final Service service;
    private final DynamicGraphQLClient dynamicClient;

    @Inject
    public EagerAppBean(Service service, @GraphQLClient("service") DynamicGraphQLClient dynamicClient) {
        this.service = service;
        this.dynamicClient = dynamicClient;
        doStuff();
    }

    private void doStuff() {
        ExecutorService executor = Executors.newFixedThreadPool(50);
        executor.submit(() -> {
            while(true) {
                try {
                    service.operation1().await().atMost(Duration.ofSeconds(new Random().nextLong(10_000)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executor.submit(() -> {
            while(true) {
                try {
                    service.operation2().await().atMost(getDuration());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executor.submit(() -> {
            while(true) {
                try {
                    service.operation3().await().atMost(getDuration());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executor.submit(() -> {
            while(true) {
                try {
                    service.operation4().await().atMost(getDuration());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executor.submit(() -> {
            while(true) {
                try {
                    service.operation5().await().atMost(getDuration());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executor.submit(() -> {
            while(true) {
                try {
                    dynamicClient.executeAsync("""
                            query q12345 {
                              operation1
                              operation2
                              operation3
                              operation4
                              operation5
                            }
                            """).await().atMost(getDuration());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executor.submit(() -> {
            while(true) {
                try {
                    dynamicClient.executeAsync("""
                            query q135 {
                              operation1
                              operation3
                              operation5
                            }
                            """).await().atMost(getDuration());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private Duration getDuration() {
        return Duration.ofMillis(new Random().nextLong(10_000));
    }
}
