package com.jhp.foryouth.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDto {
    @JsonProperty("pstSn")
    private String contentId;

    @JsonProperty("pstTtl")
    private String title;

    @JsonProperty("pstWholCn")
    private String content;

    @JsonProperty("pstUrlAddr")
    private String url;
}
