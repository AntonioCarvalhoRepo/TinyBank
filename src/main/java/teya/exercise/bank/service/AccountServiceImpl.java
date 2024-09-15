package teya.exercise.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import teya.exercise.bank.dto.AccountRequestDTO;
import teya.exercise.bank.dto.AccountResponseDTO;
import teya.exercise.bank.entity.Account;
import teya.exercise.bank.entity.AccountRepository;
import teya.exercise.bank.entity.User;
import teya.exercise.bank.entity.UserRepository;
import teya.exercise.bank.mapper.AccountMapper;
import teya.exercise.bank.type.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public UUID create(AccountRequestDTO accountRequestDTO) {
        if(userRepository.findById(accountRequestDTO.getUserId()).get().isDeleted){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User has been de-activated");
        }

        return accountRepository.save(accountMapper.mapAccountRequestDTOtoEntity(accountRequestDTO)).getId();
    }

    @Override
    public List<AccountResponseDTO> getAccountBalances(UUID userId) {
        if(userRepository.findById(userId).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User does not exist");
        }

        if(userRepository.findById(userId).get().isDeleted()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User has been de-activated");
        }

        List<AccountResponseDTO> accountResponseDTOS = new ArrayList<>();

        for (Account account : accountRepository.findAccountsByUser(userId)){
            accountResponseDTOS.add(accountMapper.mapEntityToResponseDTO(account));
        }
        return accountResponseDTOS;
    }


}
