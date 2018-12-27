package com.sayokama.springmapper;

import com.sayokama.springmapper.configuration.BaseTypeMap;
import com.sayokama.springmapper.dto.BaseUserDto;
import com.sayokama.springmapper.model.User;
import com.sayokama.springmapper.model.UserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PureCustomTypeMapTest.Application.class)
public class PureCustomTypeMapTest {


    @Autowired
    private ModelMapper mapper;

    @Test
    public void typeMapShouldBeLoaded() {
        assertFalse("Type map must be created.", mapper.getTypeMaps().isEmpty());
    }

    @Test
    public void verifyUser2BaseUserDtoMapping() {
        User user = UserUtil.generateFullUserActive();

        BaseUserDto dto = mapper.map(user, BaseUserDto.class);

        assertEquals(user.getGuid(), dto.getGuid());
        assertEquals(user.getProfile().getFirstName(), dto.getFirstName());
        assertEquals(user.getProfile().getLastName(), dto.getLastName());
    }

    @EnableAutoConfiguration
    static class Application {

        @Bean
        public BaseTypeMap baseTypeMap() {
            return new BaseTypeMap() {

                @Override
                public void accept(ModelMapper mapper) {
                    TypeMap<User, BaseUserDto> typeMap = mapper.createTypeMap(User.class, BaseUserDto.class);
                    typeMap.addMapping(src -> src.getProfile().getFirstName(), BaseUserDto::setFirstName);
                    typeMap.addMapping(src -> src.getProfile().getLastName(), BaseUserDto::setLastName);
                }

                @Override
                public boolean validate(ModelMapper mapper) {
                    return true;
                }

            };
        }

    }

}
