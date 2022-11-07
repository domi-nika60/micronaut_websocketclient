package com.example;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.rxjava3.http.client.websockets.Rx3WebSocketClient;
import io.micronaut.scheduling.annotation.Scheduled;
import io.reactivex.rxjava3.core.Single;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RunClient {
    private static final Logger LOG = LoggerFactory.getLogger(RunClient.class);
    @Inject
    @Client("http://nosuchdomaintest")
    Rx3WebSocketClient wsClient;

    @Scheduled(initialDelay = "1s")
    void connectToClient() {

        Single<MyWSClient> speechClient = wsClient.connect(MyWSClient.class, "/").lastOrError();
        speechClient.subscribe(
            success -> LOG.info("The connection is opened"),
            throwable -> {
                LOG.error("Problem with connection: {}", throwable.toString());
            }
        );
    }
}
