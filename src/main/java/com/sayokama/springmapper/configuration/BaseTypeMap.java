package com.sayokama.springmapper.configuration;

import org.modelmapper.ModelMapper;

/**
 * Base interface for custom mappings creating.
 * Put {@link org.springframework.stereotype.Component} on class for Spring be able to create it as bean.
 *
 * @see <a href="http://modelmapper.org/user-manual/property-mapping/">Model Mapper</a> for more detail about ModelMapper.
 */
public interface BaseTypeMap {

    /**
     * Default logic for validating and accepting custom mappings.
     * Subclasses may override this to perform custom mapping processing.
     *
     * @param mapper ModelMapper that will be created
     */
    default void process(ModelMapper mapper) {
        if (validate(mapper))
            accept(mapper);
    }

    /**
     * For accepting you custom mappings on created ModelMapper.
     *
     * @param mapper ModelMapper that will be created
     */
    void accept(ModelMapper mapper);

    /**
     * Validates before create any of typeMap or etc.
     *
     * @param mapper ModelMapper that will be created.
     * @return true if your mapper must be accepter for ModelMapper otherwise false.
     */
    boolean validate(ModelMapper mapper);

}
