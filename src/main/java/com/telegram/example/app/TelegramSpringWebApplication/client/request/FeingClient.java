package com.telegram.example.app.TelegramSpringWebApplication.client.request;

import com.telegram.example.app.TelegramSpringWebApplication.dto.MessageSend;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegram;
import com.telegram.example.app.TelegramSpringWebApplication.dto.ResultBotTelegramList;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient (url = "${telegram.api}${telegram.token}", name = "telegram")
public interface FeingClient {

    @GetMapping("/getUpdates")
    ResponseEntity<ResultBotTelegramList> buscarAtualizacao();

    @Headers("MEU_HEADER: {meu_header}")
    @GetMapping("/getUpdates")
    ResponseEntity<ResultBotTelegramList> buscarAtualizacao1();

    //retorno com Response Entity
    @PostMapping("/sendMessage")
    ResponseEntity<ResultBotTelegram> enviarMensagem(@RequestBody MessageSend msg);

    //retorno response Object
    @PostMapping("/sendMessage")
    ResultBotTelegram enviarMensagem1(@RequestBody MessageSend msg);

    //set header RequestHeader
    @PostMapping(value = "/sendMessage")
    ResultBotTelegram enviarMensagem3(@RequestBody MessageSend msg, @RequestHeader("MEU_HEADER") String header);

    //RequestMapping informando o tipo de metodo
    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    ResultBotTelegram enviaMensagem5(@RequestBody MessageSend msg);

    //Exemplo de path variable
    @GetMapping("/getUpdates/{meuTeste}")
    ResultBotTelegram examplePathVariable(@PathVariable("meuTeste") String meuTeste);

    //Exemplo de request param
    //www.minhaUrl.com.br/getUpdates?nome=nomeInformado
    @GetMapping("/getUpdates")
    ResultBotTelegram exampleRequestParam(@RequestParam("nome") String nome);

}
