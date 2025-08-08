package com.example.HospitalManagment.config;

import com.example.HospitalManagment.config.tenant.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final TenantInterceptor tenantInterceptor;



    @Autowired
    public WebConfiguration(TenantInterceptor tenantInterceptor) {
        this.tenantInterceptor = tenantInterceptor;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(tenantInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/v2/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/ticket_support/**"
        );
//        registry.addIntercepto();
        //registry.addInterceptor(timeZoneInterceptor).addPathPatterns("/**").excludePathPatterns("/files/**");

    }

}