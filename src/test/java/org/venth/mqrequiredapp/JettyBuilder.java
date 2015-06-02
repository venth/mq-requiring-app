package org.venth.mqrequiredapp;

import com.codahale.metrics.servlets.PingServlet;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Venth on 31/05/2015
 */
public class JettyBuilder {
    private int serverPort;

    public static JettyBuilder aServer() {
        return new JettyBuilder();
    }

    public JettyBuilder listeningOnHttpPort(int serverPort) {
        this.serverPort = serverPort;
        return this;
    }

    public Server build() throws URISyntaxException, IOException {
        Server jettyServer = new Server();

        ServerConnector http = new ServerConnector(jettyServer);
        http.setPort(serverPort);
        http.setIdleTimeout(30000);
        http.setHost("127.0.0.1");


        jettyServer.setConnectors(new Connector[] { http });

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        jettyServer.setHandler(context);

        context.setInitParameter("contextConfigLocation", "classpath*:**/applicationContext.xml");
        context.addServlet(PingServlet.class, "/ping");

        context.addServlet(new ServletHolder("default", DispatcherServlet.class), "/*");
        context.addEventListener(new ContextLoaderListener());
        context.setResourceBase(new ClassPathResource("/").getURI().toString());


        jettyServer.setStopAtShutdown(true);

        return jettyServer;
    }
}
