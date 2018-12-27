package com.sayokama.springmapper.configuration;

import org.modelmapper.ModelMapper;

public interface ModelMapperConfiguration {

    default void preModelMapperConfig(ModelMapper modelMapper) {

    }

    default void postModelMapperConfig(ModelMapper modelMapper) {

    }

}
