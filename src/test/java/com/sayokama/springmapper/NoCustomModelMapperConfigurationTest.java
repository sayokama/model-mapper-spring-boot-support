package com.sayokama.springmapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = NoCustomModelMapperConfigurationTest.Application.class)
public class NoCustomModelMapperConfigurationTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void modelMapperShouldBeCreatedWithoutAnyConfigurer() {
        assertNotNull("Model mapper must be created without any custom configuration. ", mapper);
    }

    @EnableAutoConfiguration
    static class Application {

    }

}

