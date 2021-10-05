package com.smartbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verb_group_mark")
@Entity
public class VerbGroupMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String grNameUk;
    private String grNameEn;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<IrrVerbAllForm> irrVerbAllForms = new ArrayList<>();
}
