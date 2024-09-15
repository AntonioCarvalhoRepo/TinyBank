package teya.exercise.bank.mapper;

import org.springframework.stereotype.Component;
import teya.exercise.bank.dto.UserRequestDTO;
import teya.exercise.bank.entity.User;

@Component
public class UserMapper {

    public User mapUserRequestDTOtoEntity (UserRequestDTO requestDTO){
        User entity = new User();
        entity.setName(requestDTO.getName());
        entity.setAge(requestDTO.getAge());
        entity.setAddress(requestDTO.getAddress());
        return  entity;
    }
}
