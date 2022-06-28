package org.acme.client;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import io.smallrye.mutiny.Uni;

@GraphQLClientApi(configKey = "service")
public interface Service {
    Uni<String> operation1();
    Uni<String> operation2();
    Uni<String> operation3();
    Uni<String> operation4();
    Uni<String> operation5();
}
