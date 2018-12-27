package com.sayokama.springmapper;

import com.sayokama.springmapper.configuration.BaseTypeMap;
import com.sayokama.springmapper.dto.FullUserDto;
import com.sayokama.springmapper.model.User;
import com.sayokama.springmapper.model.UserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomTypeMapWithConverterTest.Application.class)
public class CustomTypeMapWithConverterTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void verifyFullUserDtoMapping() {
        User user = UserUtil.generateFullUserActive();

        FullUserDto mapped = modelMapper.map(user, FullUserDto.class);

        assertEquals(user.getGuid(), mapped.getGuid());
        assertTrue(mapped.isActive());
        assertEquals(user.getProfile().getFirstName(), mapped.getFirstName());
        assertEquals(user.getProfile().getLastName(), mapped.getLastName());
        assertEquals(user.getProfile().getDob().toString(), mapped.getDob());
    }

    @EnableAutoConfiguration
    static class Application {

        @Bean
        public BaseTypeMap user2FullUserDto() {
            return new BaseTypeMap() {
                @Override
                public void accept(ModelMapper mapper) {
                    TypeMap<User, FullUserDto> typeMap = mapper.createTypeMap(User.class, FullUserDto.class);

                    Converter<User.State, Boolean> state2Boolean = ctx -> Objects.equals(User.State.ACTIVE, ctx.getSource());
                    Converter<LocalDateTime, String> localDate2String = ctx -> Optional.ofNullable(ctx.getSource()).map(LocalDateTime::toString).orElse("unknown");

                    typeMap.addMapping(User::getGuid, FullUserDto::setGuid)
                        .addMappings(map -> map.using(state2Boolean).map(User::getState, FullUserDto::setActive))
                        .addMapping(src -> src.getProfile().getLastName(), FullUserDto::setLastName)
                        .addMapping(src -> src.getProfile().getFirstName(), FullUserDto::setFirstName)
                        .addMappings(map -> map.using(localDate2String).map(src -> src.getProfile().getDob(), FullUserDto::setDob));
                }

                @Override
                public boolean validate(ModelMapper mapper) {
                    return Objects.isNull(mapper.getTypeMap(User.class, FullUserDto.class));
                }
            };
        }
    }
}
