package com.splunk.hecclient;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by kchen on 10/20/17.
 */
public final class HecWithAck extends Hec {
    public HecWithAck(HecClientConfig config, PollerCallback callback) {
        this(config, HecClient.createHttpClient(config), callback);
    }

    public HecWithAck(HecClientConfig config, CloseableHttpClient httpClient, PollerCallback callback) {
        this(config, httpClient, createPoller(config, httpClient, callback));
    }

    public HecWithAck(HecClientConfig config, CloseableHttpClient httpClient, Poller poller) {
        super(config, httpClient, poller);
    }

    public static HecAckPoller createPoller(HecClientConfig config, CloseableHttpClient httpClient, PollerCallback callback) {
        return new HecAckPoller(httpClient, callback)
                .setAckPollInterval(config.getAckPollInterval())
                .setEventBatchTimeout(config.getEventBatchTimeout());
    }
}