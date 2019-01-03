package com.sayokama.springmapper;

import com.sayokama.springmapper.configuration.BaseTypeMap;
import com.sayokama.springmapper.configuration.ModelMapperConfiguration;
import com.sayokama.springmapper.configuration.ModelMapperFactoryBean;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Configuration
@ConditionalOnClass(ModelMapper.class)
@ConditionalOnMissingBean(ModelMapper.class)
public class StringBootModelMapperConfiguration {

    @Bean
    @ConditionalOnMissingBean({ModelMapperFactoryBean.class})
    public ModelMapperFactoryBean modelMapperFactoryBean(@Autowired(required = false) List<BaseTypeMap> typeMappers, @Autowired(required = false) ModelMapperConfiguration modelMapperConfiguration) {
        if (Objects.isNull(typeMappers))
            typeMappers = Collections.emptyList();

        return new ModelMapperFactoryBean(typeMappers, modelMapperConfiguration);
    }

}

