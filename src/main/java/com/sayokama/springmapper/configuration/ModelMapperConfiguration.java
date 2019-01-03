package com.sayokama.springmapper.configuration;

import org.modelmapper.ModelMapper;

/**
 * ModelMapper configuration base interface.
 * It provides a methods for configure ModelMapper bean.
 * Put {@link org.springframework.stereotype.Component} on class for Spring be able to create it as bean.
 *
 * @see <a href="http://modelmapper.org/user-manual/configuration/">Model Mapper</a> for more detail about configurating.
 */
public interface ModelMapperConfiguration {

    /**
     * A default implementation of configurations ModelMapper before all mappers will be accepted.
     *
     * @param modelMapper mapper that can be configure.
     */
    default void preModelMapperConfig(ModelMapper modelMapper) {
    }

    /**
     * A default implementation of configurations ModelMapper after all mappers will be accepted.
     *
     * @param modelMapper mapper that can be configure.
     */
    default void postModelMapperConfig(ModelMapper modelMapper) {
    }

}
