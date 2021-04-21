package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class MyNet {
    static HttpRequestBuilder httpRequestBuilder;
    static {
        httpRequestBuilder = new HttpRequestBuilder();
        HttpRequestBuilder.baseUrl = "https://v2.jokeapi.dev/joke/";
    }

    interface Callback {
        void success(JsonValue response);
    }

    static void get(String url, Callback callback){
        Gdx.net.sendHttpRequest(httpRequestBuilder.newRequest().method(Net.HttpMethods.GET).url(url).build(), new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                callback.success(new JsonReader().parse(httpResponse.getResultAsString()));
            }

            @Override public void failed(Throwable t) {}
            @Override public void cancelled() {}
        });
    }
}
