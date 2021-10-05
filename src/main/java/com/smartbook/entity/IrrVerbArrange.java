package com.smartbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "irr_verb_arrange")
public class IrrVerbArrange implements Comparable<IrrVerbArrange> {

    @Column(name = "all_form_id")
    private long allFormId;
    @Id
    @Column(name = "word_id")
    private long wordId;
    @Column(name = "case_number")
    private Integer caseNumber;
    @Column(name = "tenses")
    private String tenses;
    @Column(name = "word")
    private String word;
    @Column(name = "gb_spell")
    private String gbSpell;
    @Column(name = "gb_audio")
    private byte[] gbAudio;
    @Column(name = "us_spell")
    private String usSpell;
    @Column(name = "us_audio")
    private byte[] usAudio;
    @Column(name = "translate")
    private String translate;
    @Column(name = "group_id")
    private Integer groupId;

    @Override
    public int compareTo(IrrVerbArrange o) {
        return this.word.compareTo(o.word);
    }
}
