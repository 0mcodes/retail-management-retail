package com.om.retail_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponseDto {

    private Long id;

    private String name;

    private String address;

    private String city;

    private String phoneNumber;

    private String email;

    private Boolean active;
}
