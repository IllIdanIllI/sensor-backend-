package com.sensor.dto.details;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationUserDetails implements Serializable {

    private String login;

    private String password;

    private String role;
}
