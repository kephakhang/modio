package com.youngplussoft.modio.common;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import org.slf4j.LoggerFactory;

public class WebsocketClient {

    protected final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WebsocketClient.class);
    public static final String WS_SERVER_AUTHORIZATION = "ZoeNUVO-87023a2a-d05e-4281-8a4e-700a1c88976f" ;
    private Vertx vertx = null;
    private HttpClient wsClient = null;
    private final String VERTX_HOST = "youngplussoft.com";
    private final int port = 7777;



    public WebsocketClient() {
        this.vertx = Vertx.vertx();
        this.wsClient = this.vertx.createHttpClient();
    }


    public void sendMessage(final JsonObject obj) {
        this.wsClient.websocket(this.port, this.VERTX_HOST, "", websocket -> {
            //websocket.handler(data -> {
            LOGGER.debug("sendMessage to Vert.X : " + obj.toString()) ;
                websocket.write(obj.toBuffer());
                websocket.close();
            //});
        });
    }
}