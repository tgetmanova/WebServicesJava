package com.github.spb.tget.restapi.spring.test;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import java.io.IOException;

public class SyncProfileRequestControllerBaseTest {
    protected CloseableHttpClient httpClient;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Before
    public void setUp() throws Exception {
        this.httpClient = HttpClientBuilder.create().build();
    }

    @After
    public void tearDown() throws IOException {
        this.httpClient.close();
    }
}
