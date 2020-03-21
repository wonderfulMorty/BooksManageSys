package com.dev.books.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @Description:swagger配置
 * @Author: LKD
 * @Date: 2018/08/07 16:37
 */
@Configuration
@EnableSwagger
@EnableWebMvc
public class SwaggerConfig {

  @Autowired
  private SpringSwaggerConfig springSwaggerConfig;


  public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
  {
    this.springSwaggerConfig = springSwaggerConfig;
  }

  @Bean
  public SwaggerSpringMvcPlugin customImplementation()
  {
    return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
            .apiInfo(apiInfo())
            .includePatterns(".*?");
  }

  private ApiInfo apiInfo()
  {
    ApiInfo apiInfo = new ApiInfo(
            "Beamtech接口文档",
            "功能接口一览",
            "",
            "damonli0724@Gmail.com",
            "",
            "");
    return apiInfo;
  }
}


