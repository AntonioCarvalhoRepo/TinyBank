package teya.exercise.bank.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import teya.exercise.bank.dto.UserRequestDTO;
import teya.exercise.bank.entity.UserRepository;
import teya.exercise.bank.mapper.UserMapper;

import java.util.UUID;

@Component
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;


    @Override
    public UUID create(UserRequestDTO user) {
        return userRepository.save(userMapper.mapUserRequestDTOtoEntity(user)).getId();
    }

    @Override
    @Transactional
    public void disable(UUID id) {
        userRepository.disable(id);
    }
}
