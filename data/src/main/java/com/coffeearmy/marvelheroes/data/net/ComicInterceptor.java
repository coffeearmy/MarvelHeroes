package com.coffeearmy.marvelheroes.data.net;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */

public class ComicInterceptor implements Interceptor {

    private static final String TAG = ComicInterceptor.class.getSimpleName();

    private static final String TS_PARAM = "ts";
    private static final String APIKEY_PARAM = "apikey";
    private static final String HASH_PARAM = "hash";
    private static final String DIGEST_ALGORITHM = "MD5";
    private static final String ENCODING = "UTF-8";
    private final String privateKey;
    private final String publicKey;

    @Inject
    public ComicInterceptor(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        HttpUrl.Builder urlBuilder = makeUrl(url);

        url = urlBuilder.build();

        request = request.newBuilder().url(url).build();

        return chain.proceed(request);
    }

    private HttpUrl.Builder makeUrl(HttpUrl url) {
        long timeStamp = System.currentTimeMillis();
        String hash = digest(String.valueOf(timeStamp), privateKey, publicKey);

        HttpUrl.Builder urlBuilder = url.newBuilder();
        urlBuilder.addEncodedQueryParameter(TS_PARAM, String.valueOf(timeStamp));
        urlBuilder.addEncodedQueryParameter(APIKEY_PARAM, publicKey);
        urlBuilder.addEncodedQueryParameter(HASH_PARAM, hash);
        return urlBuilder;
    }


    private String digest(String... params) {
        String message = "";
        for (String param : params) {
            message += param;
        }

        try {

            MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM);
            digest.update(message.getBytes(Charset.forName(ENCODING)));
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e.getMessage());
        }

        return "";
    }
}
