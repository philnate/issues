package com.github.philnate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.hazelcast.leader.LeaderInitiator;

import com.hazelcast.core.HazelcastInstance;

@Configuration
public class Config
{
    @Autowired
    HazelcastInstance hazelcastInstance;

    @Bean
    public LeaderInitiator leaderInitiator()
    {
        return new LeaderInitiator( hazelcastInstance );
    }
}
