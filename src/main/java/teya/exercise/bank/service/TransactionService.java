package teya.exercise.bank.service;

import org.springframework.stereotype.Service;
import teya.exercise.bank.dto.AccountRequestDTO;
import teya.exercise.bank.dto.AccountResponseDTO;
import teya.exercise.bank.dto.TransactionDTO;

import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService {
    void createTransaction(TransactionDTO transaction);

    List<TransactionDTO> getTransactionByAccountId(UUID accountId);
}
