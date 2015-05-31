package org.venth.mqrequiredapp;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Venth on 30/05/2015
 */
public class MqBrokerIntegrationTest {

    @Test
    public void application_starts_when_mq_broker_is_down() throws IOException {
        //when the application is bootstrapped
        startApplication();

        //then the application is started
        NetHttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:8080"));

        try {
            HttpResponse httpResponse = request.execute();

            assertEquals(HttpStatus.SC_ACCEPTED, httpResponse.getStatusCode());
        } catch (HttpResponseException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }

    private void startApplication() {

    }
}
