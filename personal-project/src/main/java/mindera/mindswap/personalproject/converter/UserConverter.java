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
                user.getDiabeticDetails());
    }

    public User fromCreateDtoToUser(UserCreateDto userCreateDto){
        return User.builder()
                .withName(userCreateDto.getName())
                .withEmail(userCreateDto.getEmail())
                .withAge(userCreateDto.getAge())
                .withHeight(userCreateDto.getHeight())
                .withWeight(userCreateDto.getWeight())
                .withDiabeticDetails(userCreateDto.getDiabeticDetails())
                .withRegisters(userCreateDto.getRegisterList())
                .build();

    }
}
