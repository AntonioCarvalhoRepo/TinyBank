package teya.exercise.bank.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import teya.exercise.bank.dto.UserRequestDTO;
import teya.exercise.bank.entity.User;

import java.util.UUID;

@Service
public interface UserService {
    UUID create(UserRequestDTO user);

    void disable(UUID id);
}
