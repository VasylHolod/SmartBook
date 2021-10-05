package com.smartbook.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EditVerbDto {

    private String word;
    private String transBritish;
    private String transAmerican;
    private String translate;

}
