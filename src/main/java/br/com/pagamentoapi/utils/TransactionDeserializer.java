package br.com.pagamentoapi.utils;

import br.com.pagamentoapi.model.CardApplication;
import br.com.pagamentoapi.model.PaymentStatus;
import br.com.pagamentoapi.model.Transaction;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionDeserializer extends StdDeserializer<Transaction> {

    public TransactionDeserializer(Class<?> vc) {
        super(vc);
    }

    public TransactionDeserializer() {
        this(null);
    }

    @Override
    public Transaction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Integer id = (node.get("id")) != null ? (Integer) (node.get("id")).numberValue() : null;
        String date = node.get("date").asText();
        String time = node.get("time").asText();
        BigDecimal value = (BigDecimal) node.get("value").decimalValue();
        String cardApplication = node.get("cardApplication").asText();
        String status = node.get("status").asText();

        return new Transaction(id, dateConverter(date), timeConverter(date, time), value, CardApplication.valueOf(cardApplication), PaymentStatus.valueOf(status));
    }

    private LocalDate dateConverter(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    private LocalDateTime timeConverter(String date, String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(date + " " + time, dateTimeFormatter);
    }
}
