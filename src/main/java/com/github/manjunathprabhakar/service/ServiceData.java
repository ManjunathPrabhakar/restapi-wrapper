package com.github.manjunathprabhakar.service;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;

import java.util.Map;

public class ServiceData {
    private String host;
    private String basepath;
    private Object body;
    private Map<String, String> header;
    private Cookies cookies;
    private ContentType contentType;
    private APIMethod apiMethod;
    private Map<String, String> queryparams;
    private Config config;

    ServiceData(String host, String basepath, Object body, Map<String, String> header, Cookies cookies, ContentType contentType, APIMethod apiMethod, Map<String, String> queryparams, Config config) {
        this.host = host;
        this.basepath = basepath;
        this.body = body;
        this.header = header;
        this.cookies = cookies;
        this.contentType = contentType;
        this.apiMethod = apiMethod;
        this.queryparams = queryparams;
        this.config = config;
    }

    public static ServiceDataBuilder builder() {
        return new ServiceDataBuilder();
    }

    public String getHost() {
        return this.host;
    }

    public String getBasepath() {
        return this.basepath;
    }

    public Object getBody() {
        return this.body;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public Cookies getCookies() {
        return this.cookies;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public APIMethod getApiMethod() {
        return this.apiMethod;
    }

    public Map<String, String> getQueryparams() {
        return this.queryparams;
    }

    public Config getConfig() {
        return this.config;
    }

    public enum APIMethod {
        GET, POST
    }

    public enum Config {
        HTTP, HTTPS
    }

    public static class ServiceDataBuilder {
        private String host;
        private String basepath;
        private Object body;
        private Map<String, String> header;
        private Cookies cookies;
        private ContentType contentType;
        private APIMethod apiMethod;
        private Map<String, String> queryparams;
        private Config config;

        ServiceDataBuilder() {
        }

        public ServiceDataBuilder host(String host) {
            this.host = host;
            return this;
        }

        public ServiceDataBuilder basepath(String basepath) {
            this.basepath = basepath;
            return this;
        }

        public ServiceDataBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public ServiceDataBuilder header(Map<String, String> header) {
            this.header = header;
            return this;
        }

        public ServiceDataBuilder cookies(Cookies cookies) {
            this.cookies = cookies;
            return this;
        }

        public ServiceDataBuilder contentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }

        public ServiceDataBuilder apiMethod(APIMethod apiMethod) {
            this.apiMethod = apiMethod;
            return this;
        }

        public ServiceDataBuilder queryparams(Map<String, String> queryparams) {
            this.queryparams = queryparams;
            return this;
        }

        public ServiceDataBuilder config(Config config) {
            this.config = config;
            return this;
        }

        public ServiceData build() {
            return new ServiceData(host, basepath, body, header, cookies, contentType, apiMethod, queryparams, config);
        }

        public String toString() {
            return "ServiceData.ServiceDataBuilder(host=" + this.host + ", basepath=" + this.basepath + ", body=" + this.body + ", header=" + this.header + ", cookies=" + this.cookies + ", contentType=" + this.contentType + ", apiMethod=" + this.apiMethod + ", queryparams=" + this.queryparams + ", config=" + this.config + ")";
        }
    }
}