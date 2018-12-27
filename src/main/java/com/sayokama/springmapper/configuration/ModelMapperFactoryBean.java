package com.sayokama.springmapper.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelMapperFactoryBean implements FactoryBean<ModelMapper> {

    private final List<BaseTypeMap> typeMappers;

    private final ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    public ModelMapperFactoryBean(List<BaseTypeMap> typeMappers, @Autowired(required = false) ModelMapperConfiguration modelMapperConfiguration) {
        this.typeMappers = typeMappers;
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    @Override
    public ModelMapper getObject() {
        ModelMapper mapper = new ModelMapper();

        if (modelMapperConfiguration != null)
            modelMapperConfiguration.preModelMapperConfig(mapper);

        for (BaseTypeMap typeMap : typeMappers) {
            typeMap.process(mapper);
        }

        if (modelMapperConfiguration != null)
            modelMapperConfiguration.postModelMapperConfig(mapper);

        return mapper;
    }

    @Override
    public Class<ModelMapper> getObjectType() {
        return ModelMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
