package com.example.administrator.newsubject.net;

import android.util.Log;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @描述: 日志拦截器
 * @项目名: ugou
 * @包名: com.ugou88.ugou.retrofit
 * @类名: LoggingInterceptor
 * @作者: zuojie
 * @创建时间: 16-6-17 上午10:50
 */
public final class LoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static final String MSG = "LoggingInterceptor";

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    private volatile Level level = Level.NONE;

    public LoggingInterceptor setLevel(Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    public LoggingInterceptor.Level getLevel() {
        return level;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Level level = this.level;

        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage =
                "--> " + request.method() + ' ' + request.url() + ' ' + protocol(protocol);
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }

        Log.i(MSG, requestStartMessage);

        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    Log.i(MSG, "Content-Type: " + requestBody.contentType());
                }
                if (requestBody.contentLength() != -1) {
                    Log.i(MSG, "Content-Length: " + requestBody.contentLength());
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    Log.i(MSG, name + ": " + headers.value(i));
                }
            }

            if (!logBody || !hasRequestBody) {
                Log.i(MSG, "--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                Log.i(MSG, "--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                Log.i(MSG, "");
                Log.i(MSG, buffer.readString(charset));

                Log.i(MSG, "--> END " + request.method()
                        + " (" + requestBody.contentLength() + "-byte body)");
            }
        }

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        Log.i(MSG, "<-- " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')');

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                Log.i(MSG, headers.name(i) + ": " + headers.value(i));
            }

            if (!logBody || !HttpEngine.hasBody(response)) {
                Log.i(MSG, "<-- END HTTP");
            } else if (bodyEncoded(response.headers())) {
                Log.i(MSG, "<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (contentLength != 0) {
                    Log.i(MSG, "");
                    Log.i(MSG, buffer.clone().readString(charset));
                }

                Log.i(MSG, "<-- END HTTP (" + buffer.size() + "-byte body)");
            }
        }

        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }

}
