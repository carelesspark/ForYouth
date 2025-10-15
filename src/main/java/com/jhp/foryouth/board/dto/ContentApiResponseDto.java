package com.jhp.foryouth.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContentApiResponseDto {
    @JsonProperty("youthPolicyList")
    private List<ContentDto> contentList;
}

