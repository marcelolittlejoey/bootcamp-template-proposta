package br.com.zup.bootcamp.proposta.configuration

import org.h2.server.web.WebServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class WebConfiguration {
    @Bean
    open fun h2servletRegistration(): ServletRegistrationBean<*>? {
        val registrationBean: ServletRegistrationBean<*> = ServletRegistrationBean(WebServlet())
        registrationBean.addUrlMappings("/console/*")
        return registrationBean
    }
}