package com.corcow.hw.flagproject.manager;

import android.content.Context;

import com.corcow.hw.flagproject.json.Login;
import com.corcow.hw.flagproject.json.LoginResult;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by multimedia on 2016-04-28.
 */
public class NetworkManager {
    private static NetworkManager instance;
    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    AsyncHttpClient client;
    Gson gson;

    private NetworkManager() {
        client = new AsyncHttpClient();
        gson = new Gson();
    }

    public interface OnResultListener<T> {
        public void onSuccess(T result);
        public void onFail(int code);
    }

    // Server URL
    private static final String SERVER = "http://52.79.140.129:3000";
    private static final String REQ_TYPE="type";
    private static final String TYPE_APP="app";

    // Sign in 로그인
    public void signIn(Context context, String userID, String userPW, final OnResultListener<LoginResult> listener) {
        RequestParams params = new RequestParams();
        params.put(REQ_TYPE, TYPE_APP);
        params.put("userID", userID);
        params.put("userPW", userPW);

        client.post(context, SERVER + "/signin", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                Login resultResponse = gson.fromJson(responseString, Login.class);
                listener.onSuccess(resultResponse.result);
            }
        });
    }

    public void signOut(Context context, String userID, final OnResultListener<String> listener ) {
        RequestParams params = new RequestParams();
        params.put(REQ_TYPE, TYPE_APP);
        params.put("userID", userID);

        client.post(context, SERVER + "/signout", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    // Sign up 회원가입
    public void signUp(Context context, String userID, String userPW, String email, final OnResultListener<LoginResult> listener) {
        RequestParams params = new RequestParams();
        params.put(REQ_TYPE, TYPE_APP);
        params.put("userID", userID);
        params.put("userPW", userPW);
        params.put("email", email);

        client.post(context, SERVER + "/signup", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                Login resultResponse = gson.fromJson(responseString, Login.class);
                listener.onSuccess(resultResponse.result);
            }
        });
    }

    // ID 중복확인
    public void idCheck(Context context, String userID, final OnResultListener<String> listener) {
        RequestParams params = new RequestParams();
        params.put(REQ_TYPE, TYPE_APP);
        params.put("userID", userID);

        client.post(context, SERVER + "/idcheck", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    public void fileUpload(Context context, String fileName, String flagName, boolean filePrivate, String userID, final OnResultListener<String> listener) {
        RequestParams params = new RequestParams();
        params.put(REQ_TYPE, TYPE_APP);
        params.put("fileName", fileName);
        params.put("flagName", flagName);
        params.put("filePrivate", filePrivate);
        params.put("userID", userID);

        client.post(context, SERVER + "/fileUpload", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    public void fileDownload(Context context, String userID, String flagName, final OnResultListener<String> listener) {
        RequestParams params = new RequestParams();
        params.put(REQ_TYPE, TYPE_APP);
        params.put("userID", userID);
        params.put("flagName", flagName);

        client.post(context, SERVER + "/" + userID + "/" + flagName, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }

    public void fileDownloadTEST(Context context, final OnResultListener<String> listener) {

        client.get(context, SERVER + "/" + "test" + "/" + "testjpg", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }
















//    public void fileDelete(Context context, String )



    public void test365(Context context, String email, String password, String nickname, final OnResultListener<String> listener ) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);
        params.put("nick", nickname);

        client.post(context, "http://52.68.247.34:3000/users", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }
}