package com.sayokama.springmapper.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;

public class ModelMapperFactoryBean implements FactoryBean<ModelMapper> {

    private final List<BaseTypeMap> typeMappers;
    private final ModelMapperConfiguration modelMapperConfiguration;

    public ModelMapperFactoryBean(List<BaseTypeMap> typeMappers, ModelMapperConfiguration modelMapperConfiguration) {
        this.typeMappers = typeMappers;
        this.modelMapperConfiguration = modelMapperConfiguration;
    }

    @Override
    public ModelMapper getObject() {
        final ModelMapper mapper = new ModelMapper();

        if (modelMapperConfiguration != null)
            modelMapperConfiguration.preModelMapperConfig(mapper);

        typeMappers.forEach(typeMap -> typeMap.process(mapper));

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
