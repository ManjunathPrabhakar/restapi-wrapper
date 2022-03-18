package com.github.manjunathprabhakar.test.service;

import com.github.manjunathprabhakar.service.RequestService;
import com.github.manjunathprabhakar.service.ResponseService;
import com.github.manjunathprabhakar.service.ServiceData;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ServiceTest {

    @Test
    public void getCallWithoutQueryParamsAndHeadersAndCookies() {
        /**
         * https://www.boredapi.com/api/activity
         * https://dog.ceo/api/breeds/image/random
         */
        ServiceData build = ServiceData.builder()
                .host("https://www.boredapi.com")
                .basepath("/api/activity")
                .config(ServiceData.Config.HTTPS)
                .apiMethod(ServiceData.APIMethod.GET)
                .cookies(null)
                .header(new HashMap<>())
                .contentType(ContentType.JSON)
                .queryparams(new HashMap<>())
                .build();

        RequestService restWrapper = new RequestService(build);
        ResponseService execute = restWrapper.execute();

        System.out.println("execute.statusCode() = " + execute.statusCode());
        System.out.println("execute.responseBody() = " + execute.responseBody());
        System.out.println(execute.getRaw().getTime());
    }

    @Test
    public void getCallWithQueryParamsAndWithoutHeadersAndCookies() {
        /**
         * Query param : https://api.genderize.io?name=luc
         */
        Map<String, String> query = new HashMap<>();
        query.put("name", "luc");
        ServiceData build = ServiceData.builder()
                .host("https://api.genderize.io")
                .basepath("")
                .config(ServiceData.Config.HTTPS)
                .apiMethod(ServiceData.APIMethod.GET)
                .cookies(null)
                .header(new HashMap<>())
                .contentType(ContentType.JSON)
                .queryparams(query)
                .build();

        RequestService restWrapper = new RequestService(build);
        ResponseService execute = restWrapper.execute();

        System.out.println("execute.statusCode() = " + execute.statusCode());
        System.out.println("execute.responseBody() = " + execute.responseBody());
        System.out.println(execute.getRaw().getTime());
    }
}