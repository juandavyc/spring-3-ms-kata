package com.juandavyc.ranking.infrastructure.conflurence;

import com.juandavyc.ranking.domain.port.ReportPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class ConfluenceClient implements ReportPublisherPort {


    private final RestTemplate restTemplate;
    private final String confluenceToken;

    public ConfluenceClient(
            @Value("${app.confluence-token}") String confluenceToken
    ) {
        this.restTemplate = new RestTemplate();
        this.confluenceToken = confluenceToken;
    }

    @Override
    public void publish(String jsonReport) {
        try {

            String email = "juanda.yaracifuentes@gmail.com";
            String auth = email + ":" + confluenceToken;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Basic " + encodedAuth);

            HttpEntity<String> entity = getStringHttpEntity(jsonReport, headers);

            String confluenceDomain = "juandayaracifuentes.atlassian.net";
            String url = "https://" + confluenceDomain + "/wiki/rest/api/content";
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        } catch (Exception e) {
            System.err.println("Error subiendo reporte a Confluence: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static HttpEntity<String> getStringHttpEntity(String jsonReport, HttpHeaders headers) {
        String pageTitle = "Resultados";
        String spaceKey = "KATAS";
        String bodyJson = "{"
                + "\"type\":\"page\","
                + "\"title\":\"" + pageTitle + "\","
                + "\"space\":{\"key\":\"" + spaceKey + "\"},"
                + "\"body\":{\"storage\":{\"value\":\"<pre>" + jsonReport.replace("\"", "\\\"") + "</pre>\","
                + "\"representation\":\"storage\"}}"
                + "}";

        return new HttpEntity<>(bodyJson, headers);
    }

}
