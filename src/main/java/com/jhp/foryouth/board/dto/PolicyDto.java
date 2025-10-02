package com.jhp.foryouth.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyDto {
    @JsonProperty("bizId")
    private String policyId;

    @JsonProperty("polyBiz")
    private String title;

    @JsonProperty("polyItcnCn")
    private String content;

    @JsonProperty("rqutUrla")
    private String url;
}
