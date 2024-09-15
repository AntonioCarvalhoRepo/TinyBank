package teya.exercise.bank.mapper;

import org.springframework.stereotype.Component;
import teya.exercise.bank.dto.AccountRequestDTO;
import teya.exercise.bank.dto.AccountResponseDTO;
import teya.exercise.bank.entity.Account;

@Component
public class AccountMapper {

    public Account mapAccountRequestDTOtoEntity (AccountRequestDTO accountRequestDTO){
        Account entity = new Account();
        entity.setUserId(accountRequestDTO.getUserId());
        entity.setBalance(accountRequestDTO.getBalance());
        return  entity;
    }

    public AccountResponseDTO mapEntityToResponseDTO(Account account){
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        responseDTO.setAccountId(account.getId());
        responseDTO.setBalance(account.getBalance());
        return responseDTO;
    }
}
