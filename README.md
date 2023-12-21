# Reproducer for issue in Smallrye Graphql where afterDataFetch is executed multiple times with same context.

Start server in root folder with `./mvnw quarkus:dev`, then start client from its folder.
The server will print NPEs in the console 

```
2023-12-21T11:02:02.496+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice beforeExecute 145725331828
2023-12-21T11:02:02.497+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice beforeDataFetch operation1 145725331828
2023-12-21T11:02:02.497+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.Resource operation1
2023-12-21T11:02:02.498+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice beforeDataFetch operation3 145725331828
2023-12-21T11:02:02.498+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.Resource operation3
2023-12-21T11:02:02.499+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice beforeDataFetch operation5 145725331828
2023-12-21T11:02:02.500+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.Resource operation5
2023-12-21T11:02:03.157+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.Resource Done operation3
2023-12-21T11:02:03.158+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice afterDataFetch operation5 145725331828 operation5 145725331828
2023-12-21T11:02:07.970+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.Resource Done operation1
2023-12-21T11:02:07.971+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice afterDataFetch operation5 145725331828 null
2023-12-21T11:02:07.971+0100 51bac727424a2d029bf046f8ac4fa9fd WARN  org.acme.LoggingEventservice operation5 145725331828 twice
2023-12-21T11:02:07.972+0100 51bac727424a2d029bf046f8ac4fa9fd ERROR io.smallrye.graphql SRGQL012000: Data Fetching Error: java.lang.NullPointerException: operation5 145725331828
2023-12-21T11:02:11.467+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.Resource Done operation5
2023-12-21T11:02:11.468+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice afterDataFetch operation5 145725331828 null
2023-12-21T11:02:11.469+0100 51bac727424a2d029bf046f8ac4fa9fd WARN  org.acme.LoggingEventservice operation5 145725331828 twice
2023-12-21T11:02:11.469+0100 51bac727424a2d029bf046f8ac4fa9fd ERROR io.smallrye.graphql SRGQL012000: Data Fetching Error: java.lang.NullPointerException: operation5 145725331828
2023-12-21T11:02:11.470+0100 51bac727424a2d029bf046f8ac4fa9fd INFO  org.acme.LoggingEventservice afterExecute 145725331828 145725331828

```
