package teya.exercise.bank.mapper;

import org.springframework.stereotype.Component;
import teya.exercise.bank.dto.AccountRequestDTO;
import teya.exercise.bank.dto.AccountResponseDTO;
import teya.exercise.bank.dto.TransactionDTO;
import teya.exercise.bank.entity.Account;
import teya.exercise.bank.entity.Transaction;

@Component
public class TransactionMapper {

    public Transaction mapAccountRequestDTOtoEntity (TransactionDTO transactionDTO){
        Transaction entity = new Transaction();
        entity.setFrom(transactionDTO.getFromAccountID());
        entity.setTo(transactionDTO.getToAccountID());
        entity.setType(transactionDTO.getType());
        entity.setAmount(transactionDTO.getAmount());
        return  entity;
    }

    public TransactionDTO mapEntityToResponseDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setToAccountID(transaction.getTo());
        dto.setFromAccountID(transaction.getFrom());
        dto.setType(transaction.getType());
        dto.setAmount(transaction.getAmount());
        return dto;
    }
}
