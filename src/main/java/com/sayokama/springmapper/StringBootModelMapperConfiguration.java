package com.sayokama.springmapper;

import com.sayokama.springmapper.configuration.ModelMapperConfiguration;
import com.sayokama.springmapper.configuration.ModelMapperFactoryBean;
import com.sayokama.springmapper.configuration.BaseTypeMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnClass(ModelMapper.class)
public class StringBootModelMapperConfiguration {

    @Bean
    @ConditionalOnMissingBean(ModelMapperFactoryBean.class)
    public ModelMapperFactoryBean modelMapperFactoryBean(List<BaseTypeMap> typeMappers, @Autowired(required = false) ModelMapperConfiguration modelMapperConfiguration) {

        return new ModelMapperFactoryBean(typeMappers, modelMapperConfiguration);
    }

}

