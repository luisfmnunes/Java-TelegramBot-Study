package com.telegram.example.app.TelegramSpringWebApplication.client.request;

import com.telegram.example.app.TelegramSpringWebApplication.dto.MessageSend;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegram;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegramList;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RestTemplateClient {
    @Value("${telegram.api}")
    private String BASE_PATH;
    @Value("${telegram.token}")
    private String TOKEN;

    private RestTemplate restTemplate;

    public RestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarMensagem(MessageSend msg) {
        URI uri = restTemplate.postForLocation(BASE_PATH+TOKEN+"/sendMessage",msg);

        ResultBotTelegram retorno = restTemplate.postForObject(BASE_PATH+TOKEN+"/sendMessage",msg,ResultBotTelegram.class);

        ResponseEntity<ResultBotTelegram> retorno2 = restTemplate.postForEntity(BASE_PATH+TOKEN+"/sendMessage",msg,ResultBotTelegram.class);

        HttpHeaders headers = headers();
        HttpEntity<MessageSend> request = new HttpEntity<>(msg,headers);
        ResponseEntity<ResultBotTelegram> retorno3 = restTemplate.postForEntity(BASE_PATH+TOKEN+"/sendMessage",request, ResultBotTelegram.class);

        HttpHeaders headers1 = headers();
        HttpEntity<MessageSend> request1 = new HttpEntity<>(msg,headers);
        ResponseEntity<ResultBotTelegram> retorno4 = restTemplate.exchange(BASE_PATH+ TOKEN + "/sendMessage", HttpMethod.POST, request1, ResultBotTelegram.class);

    }

    public ResultBotTelegramList buscarAtualizacao() {
        ResultBotTelegramList forObject = restTemplate.getForObject(BASE_PATH + TOKEN + "/getUpdates", ResultBotTelegramList.class);
        return forObject;
    }

    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("MEU_HEADER", "MEU_VALOR");
        return headers;
    }
}
