package com.github.manjunathprabhakar.service;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseService {
    private Response response;

    public ResponseService(Response response) {
        this.response = response;
    }

    public Response getRaw() {
        return this.response;
    }

    public int statusCode() {
        return this.response.then().extract().statusCode();
    }

    public Map<String, String> responseHeaders() {
        Headers headers = this.response.getHeaders();
        Map<String, String> res = new LinkedHashMap<>();
        for (Header header : headers) {
            res.put(header.getName(), header.getValue());
        }
        return res;
    }

    public String responseBody() {
        return this.response.body().asString();
    }

    public Map<String, String> responseCookies() {
        return this.response.getCookies();
    }

    public Cookies responseDetailedCookies() {
        return this.response.getDetailedCookies();
    }

    public long responseTime() {
        return this.response.getTime();
    }

}