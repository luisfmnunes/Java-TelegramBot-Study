package com.telegram.example.app.TelegramSpringWebApplication.service;

import com.telegram.example.app.TelegramSpringWebApplication.client.request.FeingClient;
import com.telegram.example.app.TelegramSpringWebApplication.client.request.JavaHttpClient;
import com.telegram.example.app.TelegramSpringWebApplication.client.request.RestTemplateClient;
import com.telegram.example.app.TelegramSpringWebApplication.dto.MessageSend;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegram;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegramList;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Telegram {
    private RestTemplateClient restTemplateClient;
    private FeingClient feingClient;
    private JavaHttpClient javaHttpClient;

    public Telegram(RestTemplateClient restTemplateClient, FeingClient feingClient, JavaHttpClient javaHttpClient) {
        this.restTemplateClient = restTemplateClient;
        this.feingClient = feingClient;
        this.javaHttpClient = javaHttpClient;
    }

    public void enviarMensagem(MessageSend mensagem) {
        //HTTP Client
        ResultBotTelegram resultBotTelegramResponseEntity = javaHttpClient.enviarMenssagem(mensagem);

        //restTemplate
        restTemplateClient.enviarMensagem(mensagem);
        
        //feingClient 
        ResponseEntity<ResultBotTelegram> resultBotTelegramResponseEntity1 = feingClient.enviarMensagem(mensagem);
        ResultBotTelegram resultBotTelegram = feingClient.enviarMensagem1(mensagem);
    }

    public ResultBotTelegramList buscarAtualizacao() {
        //HTTP Client
        ResultBotTelegramList resultBotTelegramList = javaHttpClient.buscarAtualizacao();

        //restTemplate
        ResultBotTelegramList resultBotTelegramList1 = restTemplateClient.buscarAtualizacao();

        //feignClient
        ResponseEntity<ResultBotTelegramList> resultBotTelegramListResponseEntity = feingClient.buscarAtualizacao();
        ResultBotTelegramList resultBotTelegramList2 = resultBotTelegramListResponseEntity.getBody();

        return resultBotTelegramList2;
    }
}
