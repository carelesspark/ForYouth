package com.jhp.foryouth.find.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPwDTO {

    private String userName;

    private String userEmail;

    private String userId;
}
