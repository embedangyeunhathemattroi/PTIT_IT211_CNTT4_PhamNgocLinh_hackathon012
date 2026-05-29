package com.example.hackathon012.service;

import com.example.hackathon012.dto.CarRequestDTO;
import com.example.hackathon012.dto.CarResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface CarService {
    void delete(Long id);
    CarResponseDTO create(CarRequestDTO carRequestDTO);
    CarResponseDTO updatePut(Long id,CarRequestDTO carRequestDTO);
    CarResponseDTO updatePatch(Long id,CarRequestDTO carRequestDTO);
    Page<CarResponseDTO> findAll(Pageable pageable,String keyword);
}
