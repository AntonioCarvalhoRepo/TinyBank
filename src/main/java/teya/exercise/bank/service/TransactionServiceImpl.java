package teya.exercise.bank.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import teya.exercise.bank.dto.TransactionDTO;
import teya.exercise.bank.entity.*;
import teya.exercise.bank.mapper.TransactionMapper;
import teya.exercise.bank.type.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional
    public void createTransaction(TransactionDTO transaction) {
        // Validate if accounts exist
        if(!accountRepository.existsById(transaction.getFromAccountID())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "From Account does not Exist");
        }

        //Validate if user is still enabled
        if(userRepository.findById(accountRepository.findById(transaction.getFromAccountID()).get().getUserId()).get().isDeleted()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User has been de-activated");
        }

        if(transaction.getToAccountID() != null && userRepository.findById(accountRepository.findById(transaction.getToAccountID()).get().getUserId()).get().isDeleted()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User has been de-activated");
        }


        if(transaction.getToAccountID() != null && !accountRepository.existsById(transaction.getToAccountID())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "To Account does not Exist");
        }

        if(transaction.getType().equals(TransactionType.TRANSFER) && transaction.getToAccountID() == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Can only transfer between 2 accounts");
        }

        //Deposit
        if(transaction.getType().equals(TransactionType.DEPOSIT)){
            Optional<Account> account = accountRepository.findById(transaction.getFromAccountID());
            accountRepository.updateBalance(account.get().getBalance() + transaction.getAmount(),transaction.getFromAccountID());
        }

        //WithDrawn
        if(transaction.getType().equals(TransactionType.WITHDRAW)){
            Optional<Account> account = accountRepository.findById(transaction.getFromAccountID());
            if(account.get().getBalance() >= transaction.getAmount()){
                accountRepository.updateBalance(account.get().getBalance() - transaction.getAmount(),transaction.getFromAccountID());
            }else{
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Insufficient amount to be withdrawn ");
            }
        }

        //Transfer
        if(transaction.getType().equals(TransactionType.TRANSFER)){
            Optional<Account> originAccount = accountRepository.findById(transaction.getFromAccountID());
            Optional<Account> destinationAccount = accountRepository.findById(transaction.getToAccountID());
            if(originAccount.get().getBalance() >= transaction.getAmount()){
                accountRepository.updateBalance(originAccount.get().getBalance() - transaction.getAmount(),transaction.getFromAccountID());
                accountRepository.updateBalance(destinationAccount.get().getBalance() + transaction.getAmount(),transaction.getToAccountID());
            }else{
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Insufficient amount to be transfer between both accounts ");
            }
        }


        transactionRepository.save(transactionMapper.mapAccountRequestDTOtoEntity(transaction));

    }

    @Override
    public List<TransactionDTO> getTransactionByAccountId(UUID accountId) {
        if(userRepository.findById(accountRepository.findById(accountId).get().getUserId()).get().isDeleted()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User has been de-activated");
        }

        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        for (Transaction transaction : transactionRepository.getTransactionByAccountId(accountId)){
            transactionDTOS.add(transactionMapper.mapEntityToResponseDTO(transaction));
        }
        return transactionDTOS;
    }
}
