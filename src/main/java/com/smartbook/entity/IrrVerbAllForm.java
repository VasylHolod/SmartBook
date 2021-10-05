package com.smartbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "irr_verb_all_form")
@Entity
public class IrrVerbAllForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String presentSimple;
    private String thirdPerson;
    private String pastSimple;
    private String pastParticiple;
    private String ingForm;
    private Integer caseNumber;
    private String translate;
    @ManyToOne
    private VerbGroupMark verbGroupMark;

}
