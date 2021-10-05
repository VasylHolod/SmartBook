package com.smartbook.externalAPI.oxford;

import com.smartbook.entity.enums.Dialect;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Data
@RequiredArgsConstructor
@PropertySource("classpath:oxford.prop")
public class OxfordDownloader {
    private final RestTemplate restTemplate;
    @Value("${oxford.baseUrl}")
    private String baseUrl;
    private String word;
    private Dialect dialect;

    public String composeUrlString(String word, Dialect dialect) {
//        return baseUrl + dialect.getName() + "/" + word.toLowerCase() + "?fields=pronunciations&lexicalCategory=verb&strictMatch=false";
        return baseUrl + dialect.getName() + "/" + word.toLowerCase() + "?fields=pronunciations&strictMatch=false";
    }

    public ResponseEntity<OxfordModel> gatherOxfordData(String word, Dialect dialect) {
        String url = composeUrlString(word, dialect);
        try {
            ResponseEntity<OxfordModel> response = restTemplate.getForEntity(url, OxfordModel.class, 1);
            System.out.println("from downloader = " + response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
            }
            return new ResponseEntity<>(response.getBody(), HttpStatus.NOT_FOUND);
        } catch (AuthenticationServiceException a) {
            System.out.println(a.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


}
//        String url = "https://od-api.oxforddictionaries.com:443/api/v2/entries/en-gb/put?fields=pronunciations&lexicalCategory=verb&strictMatch=false";
