package com.telegram.example.app.TelegramSpringWebApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegram.example.app.TelegramSpringWebApplication.dto.MessageSend;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegramList;
import com.telegram.example.app.TelegramSpringWebApplication.service.Telegram;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/telegram")
public class TelegramController {

    private Telegram telegram;
    private ObjectMapper objectMapper;

    public TelegramController(Telegram telegram, ObjectMapper objectMapper) {
        this.telegram = telegram;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity criarSoldado(@RequestBody MessageSend messageRequest) {
        System.out.println("Entrou no post");
        telegram.enviarMensagem(messageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ResultBotTelegramList> buscarSoldados() {
        ResultBotTelegramList getUpdatesResultBotTelegram = telegram.buscarAtualizacao();
        return ResponseEntity.ok(getUpdatesResultBotTelegram);
    }
}
