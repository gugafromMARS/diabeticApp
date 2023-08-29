package mindera.mindswap.personalproject.service;


import mindera.mindswap.personalproject.converter.UserConverter;
import mindera.mindswap.personalproject.dto.user.UserCreateDto;
import mindera.mindswap.personalproject.dto.user.UserDto;
import mindera.mindswap.personalproject.model.user.User;
import mindera.mindswap.personalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userConverter.toDto(user)).toList();
    }


    public UserDto getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User dont exist"));
        return userConverter.toDto(user);
    }

    public UserDto create(UserCreateDto userCreateDto) {
        User user = userRepository.findByEmail(userCreateDto.getEmail());
        if(user != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!");
        }
        User newUser = userConverter.fromCreateDtoToUser(userCreateDto);
        userRepository.save(newUser);
        return userConverter.toDto(newUser);
    }
}
