package com.jhp.foryouth.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PolicyApiResponseDto {
    private List<PolicyDto> policyList;
}

