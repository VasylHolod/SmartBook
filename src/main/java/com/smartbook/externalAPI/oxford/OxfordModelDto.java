package com.smartbook.externalAPI.oxford;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OxfordModelDto {
    private String word;
    private String dialect;
    private String audioFile;
    private String phoneticSpelling;

    public OxfordModelDto(OxfordModel oxfordModel) {
        this.word = oxfordModel.getWord();
        List<OxfordModel.Results> results = oxfordModel.getResults();
        for (OxfordModel.Results res : results) {
            this.dialect = res.getLanguage();
            List<OxfordModel.Results.LexicalEntries> lexEntries = res.getLexicalEntries();
            for (OxfordModel.Results.LexicalEntries lexEntry : lexEntries) {
                List<OxfordModel.Results.LexicalEntries.Entries> entries = lexEntry.getEntries();
                for (OxfordModel.Results.LexicalEntries.Entries entry : entries) {
                    List<OxfordModel.Results.LexicalEntries.Entries.Pronunciations> pronunciations = entry.getPronunciations();
                    for (OxfordModel.Results.LexicalEntries.Entries.Pronunciations pronunciation : pronunciations) {
                        this.audioFile = pronunciation.getAudioFile();
                        this.phoneticSpelling = pronunciation.getPhoneticSpelling();
                    }
                }
            }
        }
    }
}
