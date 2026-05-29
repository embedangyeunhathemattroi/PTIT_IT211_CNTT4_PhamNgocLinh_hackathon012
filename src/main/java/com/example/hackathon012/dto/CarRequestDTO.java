package com.example.hackathon012.dto;

import com.example.hackathon012.entity.CarStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CarRequestDTO {
    //de @NotNull , @NotBlank,@DecimalMin
@NotBlank(message = "nhập model vào trong ô trống")
    private String model;
    @NotBlank(message = "nhập brand vào trong ô trống")
    private String brand;
    @DecimalMin(value ="1",message = "phải nhập giá lớn hơn 0")
    @NotNull(message = "nhập giá vào ô trống ")
    private Double price;
    private CarStatus status;
    private Boolean is_deleted = false;
}
