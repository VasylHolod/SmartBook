package com.smartbook.externalAPI.oxford;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class OxfordModel {
    private String word;
    private ArrayList<Results> results;

    @Data
    public static class Results {
        private String language;
        private ArrayList<LexicalEntries> lexicalEntries;

        @Data
        public static class LexicalEntries {
            private List<Entries> entries;
            private LexicalCategory lexicalCategory;

            @Data
            public static class LexicalCategory {
                private String id;
            }

            @Data
            public static class Entries {
                private List<Pronunciations> pronunciations;

                @Data
                public static class Pronunciations {
                    private String audioFile;
                    private String phoneticSpelling;
                }
            }
        }
    }
}
