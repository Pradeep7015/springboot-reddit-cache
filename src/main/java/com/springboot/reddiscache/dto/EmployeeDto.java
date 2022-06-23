package com.springboot.reddiscache.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private String name;
    private String designation;
}
