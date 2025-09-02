package blog.backend.main.user.service.impl;

import blog.backend.main.user.dto.UserDTO;
import blog.backend.main.user.model.User;
import blog.backend.main.user.repository.UserRepository;
import blog.backend.main.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static blog.backend.main.user.utils.UserUtil.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        log.info("Inside @Class UserServiceImpl @method create  Param userDto : {}", userDTO);
        try {
            Objects.requireNonNull(userDTO, "UserDto must be null");
            if(StringUtils.isNotBlank(userDTO.getUuid()))
                return update(userDTO);

            User user = dtoToUser(userDTO);
            user = this.userRepository.saveAndFlush(user);
            return userToDTO(user);
        }catch (Exception ex) {
            log.error("Error Inside @Class UserServiceImpl @Method create errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }

    private UserDTO update(UserDTO userDTO) {
        log.info("Inside @Class UserServiceImpl @method update  Param userDto : {}", userDTO);
        
        try {

            UserDTO dbUserDTO = find(userDTO.getUuid());
            dbUserDTO.setUsername(userDTO.getUsername());
            dbUserDTO.setEmail(userDTO.getEmail());

            User user = dtoToUser(dbUserDTO);
            user = this.userRepository.saveAndFlush(user);
            return userToDTO(user);
        } catch (Exception ex) {
            log.error("Error Inside @Class UserServiceImpl @Method update errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
        
    }

    @Override
    public UserDTO find(String uuid) {
        log.info("Inside @Class UserServiceImpl @method find  Param UUID : {}", uuid);
        try {
            if(StringUtils.isBlank(uuid))
            {
                throw new IllegalArgumentException("User uuid can not be null or empty");
            }
            User user = userRepository.findById(uuid).orElse(null);
            log.info("Inside @Class UserServiceImpl @method find Param USER : {}", user);
            if(user == null)
            {
                throw new IllegalArgumentException("USER_NOT_FOUND");
            }
            return userToDTO(user);
        }catch (Exception ex) {
            log.error("Error Inside @Class UserServiceImpl @Method find errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }

    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("Inside @Claas UserServiceImpl @method findByUsername Param USERNAME : {}", username);
        try {
            if(StringUtils.isBlank(username))
            {
                throw new IllegalArgumentException("Username can not be null or empty");
            }
            User user = userRepository.findByUsername(username).orElse(null);
            log.info("Inside @Class UserServiceImpl @method findByUsername Param USER : {}", user);
            if(user == null)
            {
                throw new IllegalArgumentException("USER_NOT_FOUND");
            }
            return userToDTO(user);
        }catch (Exception ex) {
            log.error("Error Inside @Class UserServiceImpl @Method findByUsername errorMsg: {}, exceptionStackTrace: {}", ex.getMessage(), ex.getStackTrace());
            throw new IllegalCallerException("Something went wrong");
        }
    }
}
