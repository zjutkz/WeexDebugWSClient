package com.zjutkz.weex.ws;

import com.taobao.weex.devtools.debug.IWebSocketClient;

import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by kangzhe on 18/4/2.
 */

public class CustomerSocketClient implements IWebSocketClient {

    private OkHttpClient client = new OkHttpClient.Builder()
            .build();

    private WebSocket socket;

    private AtomicBoolean isOen = new AtomicBoolean(false);

    @Override
    public boolean isOpen() {
        return isOen.get();
    }

    @Override
    public void connect(String wsAddress, final WSListener listener) {
        Request request = new Request.Builder()
                .url(wsAddress)
                .build();
        socket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                isOen.set(true);
                if(listener != null) {
                    listener.onOpen();
                }
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                if(listener != null) {
                    listener.onMessage(text);
                }
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                isOen.set(false);
                if(listener != null) {
                    listener.onClose();
                }
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                isOen.set(false);
                if(listener != null) {
                    listener.onFailure(t);
                }
            }
        });
    }

    @Override
    public void close() {
    }

    @Override
    public void sendMessage(int requestId, String message) {
        socket.send(message);
    }
}
