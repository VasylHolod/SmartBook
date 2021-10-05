package com.smartbook.externalAPI.oxford;

import com.smartbook.contentImport.media.MediaFileDownloader;
import com.smartbook.entity.IrrVerbPhonetic;
import com.smartbook.entity.enums.Dialect;
import com.smartbook.repository.IrrVerbAllFormRepo;
import com.smartbook.repository.IrrVerbPhoneticRepo;
import com.smartbook.repository.IrrVerbWordRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:media.prop"),
        @PropertySource("classpath:oxford.prop")
})
public class OxfordService {
    private final OxfordDownloader oxfordDownloader;
    private final IrrVerbAllFormRepo irrVerbAllFormRepo;
    private final IrrVerbWordRepo irrVerbWordRepo;
    private final IrrVerbPhoneticRepo irrVerbPhoneticRepo;

    @Value("${media.intPath}")
    private String filePathInt;
    @Value("${media.extPath}")
    private String filePathExt;
    @Value("${oxford.baseUrl}")
    private String baseUrl;

    public ResponseEntity<OxfordModelDto> getNewWordOxfordDto(String word, Dialect dialect) {
        ResponseEntity<OxfordModel> oxfordResult = oxfordDownloader.gatherOxfordData(word, dialect);
        HttpStatus status = oxfordResult.getStatusCode();
        if (status.is2xxSuccessful()) {
            OxfordModel oxfordModel = oxfordResult.getBody();
            System.out.println(oxfordModel.toString());
            OxfordModelDto oxfordModelDto = new OxfordModelDto(oxfordModel);
            System.out.println(oxfordModelDto.toString());
            System.out.println("from service res = " + oxfordResult.getStatusCode());
            return new ResponseEntity<>(oxfordModelDto, status);
        }
        return new ResponseEntity<>(status);
    }

    public void getAudioFile(String url) throws IOException {

        MediaFileDownloader mediaFileDownloader = new MediaFileDownloader(filePathInt, filePathExt);

        mediaFileDownloader.makeBinaryFile(url);
//        mediaFileDownloaderUs.makeBinaryFile(url);
    }

    public void updateOnePhoneticFromOxfordDto() {

    }

    public void updateListPhoneticFromOxford() throws IOException {
        MediaFileDownloader mediaFileDownloader = new MediaFileDownloader(filePathInt, filePathExt);
        List<IrrVerbPhonetic> irrVerbPhonetics = irrVerbPhoneticRepo.findTop10ByOxfordStatus(null);
        for (IrrVerbPhonetic newPhoneVerb : irrVerbPhonetics) {
            String word = newPhoneVerb.getIrrVerbWord().getWord();
//            String newWord = "teaching";
            Dialect dialect = newPhoneVerb.getDialect();
            ResponseEntity<OxfordModel> oxfordResult = oxfordDownloader.gatherOxfordData(word, dialect);
            HttpStatus status = oxfordResult.getStatusCode();
            System.out.println("status = " + status);
            if (status.is2xxSuccessful()) {
                OxfordModel oxfordModel = oxfordResult.getBody();
                System.out.println(oxfordModel.toString());
                OxfordModelDto oxfordModelDto = new OxfordModelDto(oxfordModel);
                System.out.println(oxfordModelDto.toString());

                List<IrrVerbPhonetic> similarWords = irrVerbPhoneticRepo.findByIrrVerbWord_WordAndDialect(word, dialect);
                for (IrrVerbPhonetic singleWord : similarWords) {
                    singleWord.setSoundPathOxford(oxfordModelDto.getAudioFile());
                    singleWord.setTranscription("[" + oxfordModelDto.getPhoneticSpelling() + "]");
                    singleWord.setOxfordStatus(status.value());
                    //
                    String path = mediaFileDownloader.makeStoragePath(oxfordModelDto.getAudioFile());
                    byte[] file = mediaFileDownloader.makeBinaryFile(oxfordModelDto.getAudioFile());
                    singleWord.setFileAudioDb(file);
                    singleWord.setSoundPathStorage(path);
                    //
                    System.out.println(singleWord.toString());
                    irrVerbPhoneticRepo.save(singleWord);
                }
            } else {
                newPhoneVerb.setOxfordStatus(status.value());
                System.out.println("status not 200 = " + status.value());
                irrVerbPhoneticRepo.save(newPhoneVerb);
            }
        }
    }


}

