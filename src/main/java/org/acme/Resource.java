package org.acme;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.util.Random;

@GraphQLApi
public class Resource {
    @Inject
    Logger log;

    @Query
    public Uni<String> operation1() {
        log.info("operation1");
        return Uni.createFrom().item("operation1").onItem().delayIt().by(getDuration())
                .invoke(() -> log.info("Done operation1"));
    }

    @Query
    public Uni<String> operation2() {
        log.info("operation2");
        return Uni.createFrom().item("operation2").onItem().delayIt().by(getDuration())
                .onItem().invoke(() -> log.info("Done operation2"));
    }

    @Query
    public Uni<String> operation3() {
        log.info("operation3");
        return Uni.createFrom().item("operation3").onItem().delayIt().by(getDuration())
                .invoke(() -> log.info("Done operation3"));
    }

    @Query
    public Uni<String> operation4() {
        log.info("operation4");
        return Uni.createFrom().item("operation4").onItem().delayIt().by(getDuration())
                .invoke(() -> log.info("Done operation4"));
    }

    @Query
    public Uni<String> operation5() {
        log.info("operation5");
        return Uni.createFrom().item("operation5").onItem().delayIt().by(getDuration())
                .invoke(() -> log.info("Done operation5"));
    }

    private Duration getDuration() {
        return Duration.ofMillis(new Random().nextLong(10_000));
    }
}
