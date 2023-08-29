package mindera.mindswap.personalproject.service;


import mindera.mindswap.personalproject.converter.UserConverter;
import mindera.mindswap.personalproject.dto.user.UserCreateDto;
import mindera.mindswap.personalproject.dto.user.UserDto;
import mindera.mindswap.personalproject.dto.user.UserUpdateDto;
import mindera.mindswap.personalproject.model.insulin.Insulin;
import mindera.mindswap.personalproject.model.user.User;
import mindera.mindswap.personalproject.repository.DiabeticDetailsRepository;
import mindera.mindswap.personalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    UserConverter userConverter;

    DiabeticDetailsRepository diabeticDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, DiabeticDetailsRepository diabeticDetailsRepository) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.diabeticDetailsRepository = diabeticDetailsRepository;
    }



    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userConverter.toDto(user)).toList();
    }


    public UserDto getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
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

    public ResponseEntity<String> delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }


    public UserDto update(Long userId, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(userUpdateDto.getInsulin() != null) {
            for(Insulin insulin : existingUser.getDiabeticDetails().getInsulinList()){
                if (insulin.getName().equals(userUpdateDto.getInsulin().getName())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already have this insulin");
                }
            }
            existingUser.getDiabeticDetails().getInsulinList().add(userUpdateDto.getInsulin());
        }
        existingUser.getDiabeticDetails().setInsulinPerCarbohydrate(userUpdateDto.getInsulinPerCarbohydrate());
        diabeticDetailsRepository.save(existingUser.getDiabeticDetails());
        userRepository.save(existingUser);
        return userConverter.toDto(existingUser);
    }
}
