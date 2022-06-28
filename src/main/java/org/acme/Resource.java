package org.acme;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import java.time.Duration;
import java.util.Random;

@GraphQLApi
public class Resource {

    @Query
    public Uni<String> operation1() {
        return Uni.createFrom().item("operation1").onItem().delayIt().by(getDuration());
    }

    @Query
    public Uni<String> operation2() {
        return Uni.createFrom().item("operation2").onItem().delayIt().by(getDuration());
    }

    @Query
    public Uni<String> operation3() {
        return Uni.createFrom().item("operation3").onItem().delayIt().by(getDuration());
    }

    @Query
    public Uni<String> operation4() {
        return Uni.createFrom().item("operation4").onItem().delayIt().by(getDuration());
    }

    @Query
    public Uni<String> operation5() {
        return Uni.createFrom().item("operation5").onItem().delayIt().by(getDuration());
    }

    private Duration getDuration() {
        return Duration.ofMillis(new Random().nextLong(10_000));
    }
}
