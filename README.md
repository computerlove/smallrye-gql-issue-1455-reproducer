# Reproducer for [issue 1455 i Smallrye Graphql](https://github.com/smallrye/smallrye-graphql/issues/1455)

Start server in root folder with `./mvnw quarkus:dev`, then start client from its folder.
The server will print NPEs in the console 

```
2022-06-28 09:56:24,626 INFO  [org.acm.LoggingEventservice] (vert.x-eventloop-thread-0) beforeExecute 66480635511
2022-06-28 09:56:28,396 INFO  [org.acm.LoggingEventservice] (executor-thread-0) afterExecute 66480635511 66480635511

2022-06-28 09:56:31,054 INFO  [org.acm.LoggingEventservice] (executor-thread-0) afterExecute 66480635511 null
2022-06-28 09:56:31,056 WARN  [org.acm.LoggingEventservice] (executor-thread-0) 66480635511 twice
2022-06-28 09:56:31,057 ERROR [io.qua.mut.run.MutinyInfrastructure] (executor-thread-0) Mutiny had to drop the following exception: java.lang.NullPointerException: 66480635511
        at org.acme.LoggingEventservice.afterExecute(LoggingEventservice.java:33)
        at io.smallrye.graphql.execution.event.EventEmitter.fireAfterExecute(EventEmitter.java:102)
        at io.smallrye.graphql.execution.ExecutionService.lambda$writeAsync$1(ExecutionService.java:183)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.helpers.UniCallbackSubscriber.onItem(UniCallbackSubscriber.java:77)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateFromCompletionStage$CompletionStageUniSubscription.forwardResult(UniCreateFromCompletionStage.java:63)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at graphql.execution.AbstractAsyncExecutionStrategy.lambda$handleResults$0(AbstractAsyncExecutionStrategy.java:38)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenCompleteStage(CompletableFuture.java:887)
        at java.base/java.util.concurrent.CompletableFuture.whenComplete(CompletableFuture.java:2325)
        at graphql.execution.AsyncExecutionStrategy.lambda$execute$1(AsyncExecutionStrategy.java:74)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at graphql.execution.Async.lambda$each$0(Async.java:43)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at io.smallrye.context.CompletableFutureWrapper.lambda$new$0(CompletableFutureWrapper.java:39)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.helpers.UniCallbackSubscriber.onItem(UniCallbackSubscriber.java:77)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.onItem(UniOnItemOrFailureFlatMap.java:56)
        at io.smallrye.mutiny.operators.uni.builders.DefaultUniEmitter.complete(DefaultUniEmitter.java:37)
        at io.quarkus.smallrye.graphql.runtime.spi.datafetcher.AbstractAsyncDataFetcher.lambda$invokeAndTransform$0(AbstractAsyncDataFetcher.java:54)
        at io.smallrye.mutiny.context.BaseContextPropagationInterceptor$ContextualizedTriConsumer.lambda$accept$0(BaseContextPropagationInterceptor.java:343)
        at io.smallrye.context.impl.wrappers.SlowContextualExecutor.execute(SlowContextualExecutor.java:19)
        at io.smallrye.mutiny.context.BaseContextPropagationInterceptor$ContextualizedTriConsumer.accept(BaseContextPropagationInterceptor.java:343)
        at io.smallrye.mutiny.groups.UniOnItemOrFailure.lambda$transformToUni$4(UniOnItemOrFailure.java:167)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateWithEmitter.subscribe(UniCreateWithEmitter.java:22)
        at io.smallrye.mutiny.operators.AbstractUni.subscribe(AbstractUni.java:36)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.performInnerSubscription(UniOnItemOrFailureFlatMap.java:99)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.onItem(UniOnItemOrFailureFlatMap.java:54)
        at io.smallrye.mutiny.operators.uni.UniOnTermination$UniOnTerminationProcessor.onItem(UniOnTermination.java:39)
        at io.smallrye.mutiny.operators.uni.UniDelayOnItem$UniDelayOnItemProcessor.lambda$onItem$0(UniDelayOnItem.java:53)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)
        at io.quarkus.vertx.core.runtime.VertxCoreRecorder$14.runWith(VertxCoreRecorder.java:559)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2449)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1478)
        at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
        at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:833)


2022-06-28 09:56:32,130 INFO  [org.acm.LoggingEventservice] (executor-thread-0) afterExecute 66480635511 null
2022-06-28 09:56:32,132 WARN  [org.acm.LoggingEventservice] (executor-thread-0) 66480635511 twice
2022-06-28 09:56:32,133 ERROR [io.qua.mut.run.MutinyInfrastructure] (executor-thread-0) Mutiny had to drop the following exception: java.lang.NullPointerException: 66480635511
        at org.acme.LoggingEventservice.afterExecute(LoggingEventservice.java:33)
        at io.smallrye.graphql.execution.event.EventEmitter.fireAfterExecute(EventEmitter.java:102)
        at io.smallrye.graphql.execution.ExecutionService.lambda$writeAsync$1(ExecutionService.java:183)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.helpers.UniCallbackSubscriber.onItem(UniCallbackSubscriber.java:77)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateFromCompletionStage$CompletionStageUniSubscription.forwardResult(UniCreateFromCompletionStage.java:63)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at graphql.execution.AbstractAsyncExecutionStrategy.lambda$handleResults$0(AbstractAsyncExecutionStrategy.java:38)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenCompleteStage(CompletableFuture.java:887)
        at java.base/java.util.concurrent.CompletableFuture.whenComplete(CompletableFuture.java:2325)
        at graphql.execution.AsyncExecutionStrategy.lambda$execute$1(AsyncExecutionStrategy.java:74)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at graphql.execution.Async.lambda$each$0(Async.java:43)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at io.smallrye.context.CompletableFutureWrapper.lambda$new$0(CompletableFutureWrapper.java:39)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.helpers.UniCallbackSubscriber.onItem(UniCallbackSubscriber.java:77)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.onItem(UniOnItemOrFailureFlatMap.java:56)
        at io.smallrye.mutiny.operators.uni.builders.DefaultUniEmitter.complete(DefaultUniEmitter.java:37)
        at io.quarkus.smallrye.graphql.runtime.spi.datafetcher.AbstractAsyncDataFetcher.lambda$invokeAndTransform$0(AbstractAsyncDataFetcher.java:54)
        at io.smallrye.mutiny.context.BaseContextPropagationInterceptor$ContextualizedTriConsumer.lambda$accept$0(BaseContextPropagationInterceptor.java:343)
        at io.smallrye.context.impl.wrappers.SlowContextualExecutor.execute(SlowContextualExecutor.java:19)
        at io.smallrye.mutiny.context.BaseContextPropagationInterceptor$ContextualizedTriConsumer.accept(BaseContextPropagationInterceptor.java:343)
        at io.smallrye.mutiny.groups.UniOnItemOrFailure.lambda$transformToUni$4(UniOnItemOrFailure.java:167)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateWithEmitter.subscribe(UniCreateWithEmitter.java:22)
        at io.smallrye.mutiny.operators.AbstractUni.subscribe(AbstractUni.java:36)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.performInnerSubscription(UniOnItemOrFailureFlatMap.java:99)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.onItem(UniOnItemOrFailureFlatMap.java:54)
        at io.smallrye.mutiny.operators.uni.UniOnTermination$UniOnTerminationProcessor.onItem(UniOnTermination.java:39)
        at io.smallrye.mutiny.operators.uni.UniDelayOnItem$UniDelayOnItemProcessor.lambda$onItem$0(UniDelayOnItem.java:53)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)
        at io.quarkus.vertx.core.runtime.VertxCoreRecorder$14.runWith(VertxCoreRecorder.java:559)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2449)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1478)
        at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
        at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:833)

2022-06-28 09:56:33,730 INFO  [org.acm.LoggingEventservice] (executor-thread-0) afterExecute 66480635511 null
2022-06-28 09:56:33,732 WARN  [org.acm.LoggingEventservice] (executor-thread-0) 66480635511 twice
2022-06-28 09:56:33,733 ERROR [io.qua.mut.run.MutinyInfrastructure] (executor-thread-0) Mutiny had to drop the following exception: java.lang.NullPointerException: 66480635511
        at org.acme.LoggingEventservice.afterExecute(LoggingEventservice.java:33)
        at io.smallrye.graphql.execution.event.EventEmitter.fireAfterExecute(EventEmitter.java:102)
        at io.smallrye.graphql.execution.ExecutionService.lambda$writeAsync$1(ExecutionService.java:183)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.helpers.UniCallbackSubscriber.onItem(UniCallbackSubscriber.java:77)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateFromCompletionStage$CompletionStageUniSubscription.forwardResult(UniCreateFromCompletionStage.java:63)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at graphql.execution.AbstractAsyncExecutionStrategy.lambda$handleResults$0(AbstractAsyncExecutionStrategy.java:38)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenCompleteStage(CompletableFuture.java:887)
        at java.base/java.util.concurrent.CompletableFuture.whenComplete(CompletableFuture.java:2325)
        at graphql.execution.AsyncExecutionStrategy.lambda$execute$1(AsyncExecutionStrategy.java:74)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at graphql.execution.Async.lambda$each$0(Async.java:43)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at io.smallrye.context.CompletableFutureWrapper.lambda$new$0(CompletableFutureWrapper.java:39)
        at java.base/java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:863)
        at java.base/java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:841)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:510)
        at java.base/java.util.concurrent.CompletableFuture.complete(CompletableFuture.java:2147)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.helpers.UniCallbackSubscriber.onItem(UniCallbackSubscriber.java:77)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.onItem(UniOnItemOrFailureFlatMap.java:56)
        at io.smallrye.mutiny.operators.uni.builders.DefaultUniEmitter.complete(DefaultUniEmitter.java:37)
        at io.quarkus.smallrye.graphql.runtime.spi.datafetcher.AbstractAsyncDataFetcher.lambda$invokeAndTransform$0(AbstractAsyncDataFetcher.java:54)
        at io.smallrye.mutiny.context.BaseContextPropagationInterceptor$ContextualizedTriConsumer.lambda$accept$0(BaseContextPropagationInterceptor.java:343)
        at io.smallrye.context.impl.wrappers.SlowContextualExecutor.execute(SlowContextualExecutor.java:19)
        at io.smallrye.mutiny.context.BaseContextPropagationInterceptor$ContextualizedTriConsumer.accept(BaseContextPropagationInterceptor.java:343)
        at io.smallrye.mutiny.groups.UniOnItemOrFailure.lambda$transformToUni$4(UniOnItemOrFailure.java:167)
        at io.smallrye.context.impl.wrappers.SlowContextualConsumer.accept(SlowContextualConsumer.java:21)
        at io.smallrye.mutiny.operators.uni.builders.UniCreateWithEmitter.subscribe(UniCreateWithEmitter.java:22)
        at io.smallrye.mutiny.operators.AbstractUni.subscribe(AbstractUni.java:36)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.performInnerSubscription(UniOnItemOrFailureFlatMap.java:99)
        at io.smallrye.mutiny.operators.uni.UniOnItemOrFailureFlatMap$UniOnItemOrFailureFlatMapProcessor.onItem(UniOnItemOrFailureFlatMap.java:54)
        at io.smallrye.mutiny.operators.uni.UniOnTermination$UniOnTerminationProcessor.onItem(UniOnTermination.java:39)
        at io.smallrye.mutiny.operators.uni.UniDelayOnItem$UniDelayOnItemProcessor.lambda$onItem$0(UniDelayOnItem.java:53)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)
        at io.quarkus.vertx.core.runtime.VertxCoreRecorder$14.runWith(VertxCoreRecorder.java:559)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2449)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1478)
        at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
        at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:833)
```

These situations occur more often when the client aborts the query. 
So the frequency drops when ```.await().atMost(getDuration())``` does not time out. 
