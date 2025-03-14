package com.codeEditor.v1.dto;

import com.codeEditor.v1.entity.Languages;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data

public class CodeDto {

    private Languages language ;
    private String code  ;

    public CodeDto(Languages language, String code) {
        this.language = language;
        this.code = code;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
