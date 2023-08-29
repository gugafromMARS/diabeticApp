package mindera.mindswap.personalproject.controller;


import mindera.mindswap.personalproject.dto.user.UserCreateDto;
import mindera.mindswap.personalproject.dto.user.UserDto;
import mindera.mindswap.personalproject.dto.user.UserUpdateDto;
import mindera.mindswap.personalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable ("userId") Long userId){
        return userService.getById(userId);
    }

    @PostMapping
    public UserDto create(@RequestBody UserCreateDto userCreateDto){
        return userService.create(userCreateDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        return userService.delete(userId);
    }


    @PutMapping("/{userId}")
    public UserDto update(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto){
        return userService.update(userId, userUpdateDto);
    }
}
