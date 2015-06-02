package org.venth.mqrequiredapp;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;

import org.apache.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Venth on 30/05/2015
 */
public class MqBrokerIntegrationTest {

    @Test
    public void application_starts_when_mq_broker_is_down() throws Exception {
        //given web container is ready and steady on port 8080
        int httpPort = 8080;
        Server server = JettyBuilder.aServer()
                .listeningOnHttpPort(httpPort)
                .build();

        //when the application is bootstrapped
        startApplicationOn(server);

        //then the application is started
        NetHttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:" + httpPort + "/ping"));

        try {
            HttpResponse httpResponse = request.execute();

            assertEquals(HttpStatus.SC_OK, httpResponse.getStatusCode());
        } catch (HttpResponseException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }

    private void startApplicationOn(Server server) throws Exception {
        server.start();
        server.join();
    }
}
