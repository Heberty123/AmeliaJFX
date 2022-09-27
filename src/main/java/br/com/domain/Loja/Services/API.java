package br.com.domain.Loja.Services;

import br.com.domain.Loja.Models.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class API {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T getAPI(URI uri, Class<T> c) throws IOException, InterruptedException {


        HttpClient httpClient = HttpClient.newHttpClient();


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 400){
            Endereco endereco = (Endereco) objectMapper.readValue(response.body(), c);
            return (T) endereco;
        }
        else {
            return null;
        }
    }
}
