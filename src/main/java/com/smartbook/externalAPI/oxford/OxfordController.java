package com.smartbook.externalAPI.oxford;

import com.smartbook.entity.enums.Dialect;
import com.smartbook.externalAPI.oxford.OxfordModelDto;
import com.smartbook.externalAPI.oxford.OxfordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/oxford")
@RequiredArgsConstructor
@Tag(name = "Oxford Controller", description = "designed to retrieve verbs from the Oxford Dictionary API")
public class OxfordController {
    private final OxfordService oxfordService;


    @GetMapping("/data1")
    public ResponseEntity<?> oxDataNewWordView(@RequestParam String word, @RequestParam Dialect dialect) {
        ResponseEntity<OxfordModelDto> oxfordResult = oxfordService.getNewWordOxfordDto(word, dialect);
        HttpStatus status = oxfordResult.getStatusCode();
        return new ResponseEntity<>(oxfordResult.getBody(), status);
    }

    @GetMapping("/data2")
    public ResponseEntity<?> oxDataNewWordSave(@RequestParam String word, @RequestParam Dialect dialect) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/data3")
    public ResponseEntity<?> oxDataNewWordAndFileView(@RequestParam String word, @RequestParam Dialect dialect) throws IOException {
        ResponseEntity<OxfordModelDto> oxfordResult = oxfordService.getNewWordOxfordDto(word, dialect);
        HttpStatus status = oxfordResult.getStatusCode();
        String url = oxfordResult.getBody().getAudioFile();
        oxfordService.getAudioFile(url);
        return new ResponseEntity<>(oxfordResult.getBody(), status);

    }

    @GetMapping("/data10")
    public ResponseEntity<?> oxDataNewR10() throws IOException {
        oxfordService.updateListPhoneticFromOxford();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

