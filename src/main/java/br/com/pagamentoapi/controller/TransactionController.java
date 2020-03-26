package br.com.pagamentoapi.controller;

import br.com.pagamentoapi.model.Transaction;
import br.com.pagamentoapi.service.TransactioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactioService transactioService;

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@Valid @RequestBody Transaction transaction){
        Transaction saveTransaction = this.transactioService.saveTransaction(transaction);
        return ResponseEntity.ok(saveTransaction);
    }

    @GetMapping("/all")
    public List<Transaction> listAll(){
        return this.transactioService.listAll();
    }

    @PutMapping
    public ResponseEntity<Transaction> editTransaction(@Valid @RequestBody Transaction transaction){
        Transaction editTransaction = this.transactioService.saveTransaction(transaction);
        return ResponseEntity.ok(editTransaction);
    }
}
