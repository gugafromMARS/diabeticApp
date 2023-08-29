package mindera.mindswap.personalproject.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.mindswap.personalproject.dto.user.UserCreateDto;
import mindera.mindswap.personalproject.dto.user.UserDto;
import mindera.mindswap.personalproject.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {

    public UserDto toDto(User user){
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getInsulinPerCarbohydrate(),
                user.getDiabeticType(),
                user.getRegisterList());
    }

    public User fromCreateDtoToUser(UserCreateDto userCreateDto){
        return User.builder()
                .withName(userCreateDto.getName())
                .withEmail(userCreateDto.getEmail())
                .withAge(userCreateDto.getAge())
                .withHeight(userCreateDto.getHeight())
                .withWeight(userCreateDto.getWeight())
                .withInsulinPerCarbo(userCreateDto.getInsulinPerCarbohydrate())
                .withInsulin(userCreateDto.getInsulinList())
                .withDiabeticType(userCreateDto.getDiabeticType())
                .withRegisters(userCreateDto.getRegisterList())
                .build();

    }
}
