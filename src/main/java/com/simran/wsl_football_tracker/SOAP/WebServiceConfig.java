package com.simran.wsl_football_tracker.SOAP;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.core.io.ClassPathResource;

@EnableWs 
@Configuration 

public class WebServiceConfig {
    @Bean 
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
        ApplicationContext applicationContext) {

            MessageDispatcherServlet servlet = new MessageDispatcherServlet();
            servlet.setApplicationContext(applicationContext);
            servlet.setTransformWsdlLocations(true);
            return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "matches")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema matchesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("MatchesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://wslfootballtracker.com");
        wsdl11Definition.setSchema(matchesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema matchesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/match.xsd"));
    }
}
