package com.xiaozhuge.springdataes.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration()
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.elasticsearch.uris}")
    private String hostAndPort;

    @Override
    @Bean(name = "esRestClient")
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo(hostAndPort)
            .build();
        RestHighLevelClient rest = RestClients.create(clientConfiguration).rest();

        System.out.println(rest);

        return rest;
    }

}