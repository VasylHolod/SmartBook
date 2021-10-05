package com.smartbook.entity;

import com.smartbook.entity.enums.Dialect;
import com.smartbook.entity.enums.Tenses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "irr_verb_phonetic")
@Entity
public class IrrVerbPhonetic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String transcription;
    private String soundPathOxford;
    private byte[] fileAudioDb;
    private String soundPathStorage;
    private Integer oxfordStatus;
    private Dialect dialect;
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private IrrVerbWord irrVerbWord;

    public IrrVerbPhonetic(Dialect dialect, IrrVerbWord irrVerbWord) {
        this.dialect = dialect;
        this.irrVerbWord = irrVerbWord;
    }

    public IrrVerbPhonetic(Dialect dialect) {
        this.dialect = dialect;
    }
}
