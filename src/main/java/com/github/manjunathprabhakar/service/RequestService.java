package com.github.manjunathprabhakar.service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class RequestService {
    private final ServiceData data;
    private ValidatableResponse response;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private Cookies cookies;

    public RequestService(ServiceData data) {
        this.data = data;
        this.validateData();
    }

    private void validateData() {
        Objects.requireNonNull(this.data.getApiMethod());
        Objects.requireNonNull(this.data.getConfig());
        Objects.requireNonNull(this.data.getContentType());
        Objects.requireNonNull(this.data.getHost().isEmpty() ? null : this.data.getHost());
    }

    public ResponseService execute() {
        return execute(this.data);
    }

    //POST METHODS

    private ResponseService execute(ServiceData apiData) {
        ResponseService responseService = null;
        switch (apiData.getApiMethod()) {
            case POST:
                responseService = doPostcall(apiData.getConfig(), apiData.getHost(), apiData.getBasepath(),
                        apiData.getBody(), apiData.getHeader(), apiData.getContentType(),
                        apiData.getQueryparams(), apiData.getCookies());
                break;
            case GET:
                responseService = doGetCall(apiData.getConfig(), apiData.getHost(), apiData.getBasepath(),
                        apiData.getHeader(), apiData.getContentType(),
                        apiData.getQueryparams(), apiData.getCookies());
                break;
            default:
        }
        return responseService;
    }

    private ResponseService doPostcall(ServiceData.Config config,
                                       String host, String basepath, Object body,
                                       Map<String, String> header, ContentType contentType,
                                       Map<String, String> queryparams, Cookies cookies) {
        if (basepath == null || basepath.trim().length() == 0) {
            basepath = "";
        }
        if (body == null)
            body = "";
        RequestSpecBuilder builder = new RequestSpecBuilder();
        if (config == ServiceData.Config.HTTPS) {
            builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
        }
        builder.setBaseUri(host);

        builder.setBasePath(basepath);

        builder.setBody(body);
        builder.setUrlEncodingEnabled(false);
        builder.setContentType(contentType);
        RequestSpecification requestSpec = builder.build();
        Response result = null;

        RequestSpecification requestSpecification = given().spec(requestSpec);
        if (cookies != null) {
            requestSpecification = requestSpecification.cookies(cookies);
        }
        if (header != null) {
            requestSpecification = requestSpecification.headers(header);
        }
        if (queryparams != null) {
            requestSpecification = requestSpecification.queryParams(queryparams);
        }
        result = requestSpecification.post().then().extract().response();

        return new ResponseService(result);
    }


    //GET METHODS
    private ResponseService doGetCall(ServiceData.Config config,
                                      String host, String basepath,
                                      Map<String, String> header, ContentType contentType,
                                      Map<String, String> queryparams, Cookies cookies) {
        if (basepath == null || basepath.trim().length() == 0) {
            basepath = "";
        }
        RequestSpecBuilder builder = new RequestSpecBuilder();
        if (config == ServiceData.Config.HTTPS)
            builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
        builder.setBaseUri(host);
        builder.setBasePath(basepath);
        builder.setContentType(contentType);
        builder.setUrlEncodingEnabled(false);

        RequestSpecification requestSpec = builder.build();
        RequestSpecification requestSpecification = given().spec(requestSpec);
        if (cookies != null) {
            requestSpecification = requestSpecification.cookies(cookies);
        }
        if (header != null) {
            requestSpecification = requestSpecification.headers(header);
        }
        if (queryparams != null) {
            requestSpecification = requestSpecification.queryParams(queryparams);
        }
        Response result = requestSpecification.get().then().extract().response();
        return new ResponseService(result);
    }

}