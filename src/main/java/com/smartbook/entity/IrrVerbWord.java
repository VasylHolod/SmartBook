package com.smartbook.entity;

import com.smartbook.entity.enums.Tenses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "irr_verb_word")
@Entity
public class IrrVerbWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String word;
    private Tenses tenses;
    @OneToOne
    private IrrVerbAllForm irrVerbAllForm;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<IrrVerbPhonetic> irrVerbPhonetics = new ArrayList<>();

    public IrrVerbWord(String word, Tenses tenses, IrrVerbAllForm irrVerbAllForm) {
        this.word = word;
        this.tenses = tenses;
        this.irrVerbAllForm = irrVerbAllForm;
    }

}
