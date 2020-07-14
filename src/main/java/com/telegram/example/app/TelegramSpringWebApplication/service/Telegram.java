package com.telegram.example.app.TelegramSpringWebApplication.service;

import com.telegram.example.app.TelegramSpringWebApplication.client.request.FeingClient;
import com.telegram.example.app.TelegramSpringWebApplication.client.request.JavaHttpClient;
import com.telegram.example.app.TelegramSpringWebApplication.client.request.RestTemplateClient;
import com.telegram.example.app.TelegramSpringWebApplication.dto.MessageSend;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegram;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegramList;
import org.springframework.stereotype.Service;

@Service
public class Telegram {
    //private RestTemplateClient restTemplateClient;
    //private FeingClient feingClient;
    private JavaHttpClient javaHttpClient;

    public Telegram(/*RestTemplateClient restTemplateClient, FeingClient feingClient,*/ JavaHttpClient javaHttpClient) {
        //this.restTemplateClient = restTemplateClient;
        //this.feingClient = feingClient;
        this.javaHttpClient = javaHttpClient;
    }

    public void enviarMensagem(MessageSend mensagem) {
        //HTTP Client
        ResultBotTelegram resultBotTelegramResponseEntity = javaHttpClient.enviarMenssagem(mensagem);
    }

    public ResultBotTelegramList buscarAtualizacao() {
        //HTTP Client
        ResultBotTelegramList resultBotTelegramList = javaHttpClient.buscarAtualizacao();

        return resultBotTelegramList;
    }
}
