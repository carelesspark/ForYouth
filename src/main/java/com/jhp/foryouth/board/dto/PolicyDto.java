package com.jhp.foryouth.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyDto {
    @JsonProperty("plcyNo")
    private String policyId;

    @JsonProperty("plcyNm")
    private String title;

    @JsonProperty("plcyExplnCn")
    private String content;

    @JsonProperty("aplyUrlAddr")
    private String url;
}
