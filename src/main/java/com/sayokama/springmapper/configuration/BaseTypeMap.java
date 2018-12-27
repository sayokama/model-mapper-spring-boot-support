package com.sayokama.springmapper.configuration;

import org.modelmapper.ModelMapper;

public interface BaseTypeMap {

    default void process(ModelMapper mapper) {
        if (validate(mapper))
            accept(mapper);
    }

    void accept(ModelMapper mapper);

    boolean validate(ModelMapper mapper);

}
