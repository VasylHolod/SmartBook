package com.smartbook.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class CaseStudyDto {

    private String translate;
    private String ffWord;
    private String ffTransBritish;
    private String ffTransAmerican;
    private String ffSoundBritish;
    private String ffSoundAmerican;

    private String sfWord;
    private String sfTransBritish;
    private String sfTransAmerican;
    private String sfSoundBritish;
    private String sfSoundAmerican;

    private String tfWord;
    private String tfTransBritish;
    private String tfTransAmerican;
    private String tfSoundBritish;
    private String tfSoundAmerican;

    private String ingWord;
    private String ingTransBritish;
    private String ingTransAmerican;
    private String ingSoundBritish;
    private String ingSoundAmerican;

    private Integer ffCaseNumber;

    private Integer ffVerbGroupMarkId;
}
