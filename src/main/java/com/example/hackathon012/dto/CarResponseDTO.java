package com.example.hackathon012.dto;

import com.example.hackathon012.entity.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDTO {
    private Long id;
    private String model;
    private String brand;
    private Double price;
    private CarStatus status;
    private Boolean is_deleted = false;


}
