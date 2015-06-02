package org.venth.mqrequiredapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Venth on 01/06/2015
 */
public class TestQueueListener {

    private final static Logger LOG = LoggerFactory.getLogger(TestQueueListener.class);

    public void handleTextMessage(String message) {
        LOG.debug("Handled text message: {}", message);
    }

    public void handleNumberMessage(Long message) {
        LOG.debug("Handled number message: {}", message);
    }
}
