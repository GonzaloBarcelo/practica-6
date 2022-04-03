package com.icai.practicas.controller;

import com.icai.practicas.controller.ProcessController.DataRequest;
import com.icai.practicas.controller.ProcessController.DataResponse;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void dataOk(){

        DataRequest data = new DataRequest("Gonzalo", "54022272X", "695540956");
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(data, new HttpHeaders());

        ResponseEntity<DataResponse> result = this.restTemplate.postForEntity("http://localhost:" + port + "/api/v1/process-step1", request, DataResponse.class);
        DataResponse expectedResponse = new DataResponse("OK");
            then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
            then(result.getBody().result()).isEqualTo("OK");
            then(result.getBody()).isEqualTo(expectedResponse);
    }
    @Test
    public void dataNotOK() {
        DataRequest data = new DataRequest("Gonzalo", "540222722", "6955456");
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(data, new HttpHeaders());

        ResponseEntity<DataResponse> result = this.restTemplate.postForEntity("http://localhost:" + port + "/api/v1/process-step1", request, DataResponse.class);
        DataResponse expectedResponse = new DataResponse("KO");
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody().result()).isEqualTo("KO");
        then(result.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void dataLegacyOk (){
        MultiValueMap<String, String> data = new LinkedMultiValueMap<String,String>();
            data.add("fullName","Gonzalo");
            data.add("dni","54022272X");
            data.add("telefono","695540956");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data,new HttpHeaders());
        ResponseEntity<String> result = this.restTemplate.postForEntity("http://localhost:" + port + "/api/v1/process-step1-legacy", request, String.class);
        String expectedResult = ResponseHTMLGenerator.message1;
            then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
            then(result.getBody()).isEqualTo(expectedResult);
    }


    @Test
    public void dataLegacyKo (){
        MultiValueMap<String, String> data = new LinkedMultiValueMap<String,String>();
            data.add("fullName","Gonzalo");
            data.add("dni","540222777");
            data.add("telefono","69554095J");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data,headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity("http://localhost:" + port + "/api/v1/process-step1-legacy", request, String.class);
        String expectedResult = ResponseHTMLGenerator.message2; //mensaje de error
            then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
            then(result.getBody()).isEqualTo(expectedResult);
    }
}