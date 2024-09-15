package teya.exercise.bank.service;

import org.springframework.stereotype.Service;
import teya.exercise.bank.dto.AccountRequestDTO;
import teya.exercise.bank.dto.AccountResponseDTO;
import teya.exercise.bank.entity.Account;
import teya.exercise.bank.type.TransactionType;

import java.util.List;
import java.util.UUID;

@Service
public interface AccountService {
    UUID create(AccountRequestDTO user);

    List<AccountResponseDTO> getAccountBalances(UUID userId);
}
