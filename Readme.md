Issue Description
=================

During the propagation of an OnGrantedEvent if any of the EventListeners is throwing an exception, this will cause that the just Granted Leader role is revoked and the LeaderInitiator is stopped, blocking this instance from ever becoming Leader again.

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.4.3.RELEASE)

2017-01-26 08:37:00.798  INFO 43101 --- [           main] com.github.philnate.Main                 : No active profile set, falling back to default profiles: default
2017-01-26 08:37:00.982  INFO 43101 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@243c4f91: startup date [Thu Jan 26 08:37:00 CET 2017]; root of context hierarchy
2017-01-26 08:37:01.732  INFO 43101 --- [           main] o.s.b.f.config.PropertiesFactoryBean     : Loading properties file from URL [jar:file:/Users/pknobel/.m2/repository/org/springframework/integration/spring-integration-core/4.3.6.RELEASE/spring-integration-core-4.3.6.RELEASE.jar!/META-INF/spring.integration.default.properties]
2017-01-26 08:37:01.734  INFO 43101 --- [           main] o.s.i.config.IntegrationRegistrar        : No bean named 'integrationHeaderChannelRegistry' has been explicitly defined. Therefore, a default DefaultHeaderChannelRegistry will be created.
2017-01-26 08:37:01.971  INFO 43101 --- [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'errorChannel' has been explicitly defined. Therefore, a default PublishSubscribeChannel will be created.
2017-01-26 08:37:01.976  INFO 43101 --- [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'taskScheduler' has been explicitly defined. Therefore, a default ThreadPoolTaskScheduler will be created.
2017-01-26 08:37:02.066  INFO 43101 --- [           main] o.s.b.f.config.PropertiesFactoryBean     : Loading properties file from URL [jar:file:/Users/pknobel/.m2/repository/org/springframework/integration/spring-integration-core/4.3.6.RELEASE/spring-integration-core-4.3.6.RELEASE.jar!/META-INF/spring.integration.default.properties]
2017-01-26 08:37:02.067  INFO 43101 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'integrationGlobalProperties' of type [class org.springframework.beans.factory.config.PropertiesFactoryBean] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2017-01-26 08:37:02.067  INFO 43101 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'integrationGlobalProperties' of type [class java.util.Properties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2017-01-26 08:37:02.391  INFO 43101 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-01-26 08:37:02.399  INFO 43101 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2017-01-26 08:37:02.400  INFO 43101 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.6
2017-01-26 08:37:02.487  INFO 43101 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-01-26 08:37:02.488  INFO 43101 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1515 ms
2017-01-26 08:37:02.593  INFO 43101 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-01-26 08:37:02.596  INFO 43101 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-01-26 08:37:02.596  INFO 43101 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-01-26 08:37:02.597  INFO 43101 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-01-26 08:37:02.597  INFO 43101 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-01-26 08:37:02.676  INFO 43101 --- [           main] com.hazelcast.config.XmlConfigLocator    : Loading 'hazelcast.xml' from classpath.
2017-01-26 08:37:02.737  WARN 43101 --- [           main] c.h.config.AbstractXmlConfigHelper       : Name of the hazelcast schema location incorrect using default
2017-01-26 08:37:02.850  INFO 43101 --- [           main] c.h.instance.DefaultAddressPicker        : [LOCAL] [dev] [3.7.3] Prefer IPv4 stack is true.
2017-01-26 08:37:02.864  INFO 43101 --- [           main] c.h.instance.DefaultAddressPicker        : [LOCAL] [dev] [3.7.3] Picked [10.200.54.44]:5701, using socket ServerSocket[addr=/0:0:0:0:0:0:0:0,localport=5701], bind any local is true
2017-01-26 08:37:02.873  INFO 43101 --- [           main] com.hazelcast.system                     : [10.200.54.44]:5701 [dev] [3.7.3] Hazelcast 3.7.3 (20161117 - 8166eea) starting at [10.200.54.44]:5701
2017-01-26 08:37:02.873  INFO 43101 --- [           main] com.hazelcast.system                     : [10.200.54.44]:5701 [dev] [3.7.3] Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
2017-01-26 08:37:02.873  INFO 43101 --- [           main] com.hazelcast.system                     : [10.200.54.44]:5701 [dev] [3.7.3] Configured Hazelcast Serialization version : 1
2017-01-26 08:37:03.020  INFO 43101 --- [           main] c.h.s.i.o.impl.BackpressureRegulator     : [10.200.54.44]:5701 [dev] [3.7.3] Backpressure is disabled
2017-01-26 08:37:03.393  INFO 43101 --- [           main] com.hazelcast.core.LifecycleService      : [10.200.54.44]:5701 [dev] [3.7.3] [10.200.54.44]:5701 is STARTING
2017-01-26 08:37:03.485  INFO 43101 --- [           main] c.h.s.i.o.impl.OperationExecutorImpl     : [10.200.54.44]:5701 [dev] [3.7.3] Starting 8 partition threads
2017-01-26 08:37:03.486  INFO 43101 --- [           main] c.h.s.i.o.impl.OperationExecutorImpl     : [10.200.54.44]:5701 [dev] [3.7.3] Starting 5 generic threads (1 dedicated for priority tasks)
2017-01-26 08:37:03.490  INFO 43101 --- [           main] c.h.n.t.n.NonBlockingIOThreadingModel    : [10.200.54.44]:5701 [dev] [3.7.3] TcpIpConnectionManager configured with Non Blocking IO-threading model: 3 input threads and 3 output threads
2017-01-26 08:37:03.501  WARN 43101 --- [           main] com.hazelcast.instance.Node              : [10.200.54.44]:5701 [dev] [3.7.3] No join method is enabled! Starting standalone.
2017-01-26 08:37:03.523  INFO 43101 --- [           main] com.hazelcast.core.LifecycleService      : [10.200.54.44]:5701 [dev] [3.7.3] [10.200.54.44]:5701 is STARTED
2017-01-26 08:37:03.744  INFO 43101 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@243c4f91: startup date [Thu Jan 26 08:37:00 CET 2017]; root of context hierarchy
2017-01-26 08:37:03.787  INFO 43101 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-01-26 08:37:03.787  INFO 43101 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-01-26 08:37:03.808  INFO 43101 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-26 08:37:03.808  INFO 43101 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-26 08:37:03.832  INFO 43101 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-01-26 08:37:03.927  INFO 43101 --- [           main] o.s.s.c.ThreadPoolTaskScheduler          : Initializing ExecutorService  'taskScheduler'
2017-01-26 08:37:04.062  INFO 43101 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-01-26 08:37:04.077  INFO 43101 --- [           main] c.h.i.p.impl.PartitionStateManager       : [10.200.54.44]:5701 [dev] [3.7.3] Initializing cluster partition table arrangement...
2017-01-26 08:37:04.112  INFO 43101 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2017-01-26 08:37:04.113  INFO 43101 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
2017-01-26 08:37:04.113  INFO 43101 --- [           main] o.s.i.channel.PublishSubscribeChannel    : Channel 'application.errorChannel' has 1 subscriber(s).
2017-01-26 08:37:04.113  INFO 43101 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : started _org.springframework.integration.errorLogger
2017-01-26 08:37:04.114  INFO 43101 --- [st-leadership-0] o.s.integration.leader.DefaultCandidate  : DefaultCandidate{role=leader, id=03eac049-b54b-4f92-ad8a-e1bd0da61220} leadership has been revoked: HazelcastContext{role=leader, id=03eac049-b54b-4f92-ad8a-e1bd0da61220, isLeader=false}
2017-01-26 08:37:04.149  INFO 43101 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-01-26 08:37:04.153  INFO 43101 --- [           main] com.github.philnate.Main                 : Started Main in 3.746 seconds (JVM running for 4.156)
```

Note that the `DefaultCandidate` isn't even reporting that it became master, instead it directly reports the revocation. The application isn't logging the error either, this is problematic as except of the revocation we don't even get any visibility that there is some issue.
