package com.example;

import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.ClientWebSocket;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnError;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientWebSocket("/chat")
public abstract class MyWSClient implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(MyWSClient.class);
    private WebSocketSession session;

    @OnOpen
    void onOpen(
            WebSocketSession session
    ) {
        this.session = session;
        LOG.info("On Open state");
    }

    @OnMessage
    void onMessage() {
        LOG.info("On Message state");
    }

    @OnClose
    void onClose() {
        LOG.info("without any variable");
    }

    @OnClose
    void onClose(String anyVariable) {
        LOG.info("Log which will never be executed.");
    }

    @OnError
    void onError() {
        LOG.info("in error");
    }

}