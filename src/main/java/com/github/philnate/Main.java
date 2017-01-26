package com.github.philnate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.integration.leader.event.OnGrantedEvent;

@EnableAutoConfiguration
@Import( Config.class )
@SpringBootApplication
public class Main
{

    public static void main( String[] args )
    {
        SpringApplication.run( Main.class, args );
    }

    @EventListener
    private void onGranted( OnGrantedEvent grant )
    {
        throw new RuntimeException( "Some exception during event propagation" );
    }
}
