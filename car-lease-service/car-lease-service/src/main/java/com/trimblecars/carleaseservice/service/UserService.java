package com.trimblecars.carleaseservice.service;

import com.trimblecars.carleaseservice.dto.UserDTO;
import com.trimblecars.carleaseservice.exception.ResourseNotFoundException;
import com.trimblecars.carleaseservice.model.User;
import com.trimblecars.carleaseservice.repository.UserRepository;
import com.trimblecars.carleaseservice.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperUtil mapperUtil;

    public void registerUser(UserDTO userDTO)
    {
        User user = mapperUtil.toUser(userDTO);
        userRepository.save(user);
    }

    public void authenticate (String username , String password){
        log.info("Authenticating user : {}",username);
        userRepository.findAll().stream().filter(u-> u.getUsername().equals(username)).findFirst().orElseThrow(()-> {
            log.warn("Authentication failed for user:{}", username);
            return new ResourseNotFoundException("Invalid Credentials");
        });
        log.info("User authenticated successfully:{}",username);
    }


    public List<UserDTO> getAllUsers(){
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(mapperUtil::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        log.info("Fetching user by id:{}",id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("User not found with id:"+ id));
        return mapperUtil.toUserDto(user);

    }

    public void deleteUser(Long id){
        log.info("Deleting user by ID :{}", id);
        if(!userRepository.existsById(id)){
            log.warn("User not found with id:{}", id);
            throw new ResourseNotFoundException("User not found with Id:"+id);
        }
        userRepository.deleteById(id);
        log.info("User deleted with id:{}",id);
    }
}
