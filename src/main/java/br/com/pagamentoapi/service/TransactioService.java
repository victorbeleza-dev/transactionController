package br.com.pagamentoapi.service;

import br.com.pagamentoapi.model.Transaction;
import br.com.pagamentoapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactioService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction){
        return this.transactionRepository.save(transaction);
    }

    public List<Transaction> listAll() {
        return this.transactionRepository.findAll();
    }
}
