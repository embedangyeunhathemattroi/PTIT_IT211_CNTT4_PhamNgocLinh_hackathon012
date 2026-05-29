package com.example.hackathon012.controller;

import com.example.hackathon012.dto.CarRequestDTO;
import com.example.hackathon012.dto.CarResponseDTO;
import com.example.hackathon012.service.CarService;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponseDTO> create(@RequestBody @Valid CarRequestDTO request){
        return new ResponseEntity<>(carService.create(request), HttpStatus.CREATED);
    }






    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updatePut( @Valid @PathVariable long id, @RequestBody CarRequestDTO request){
        return ResponseEntity.ok(carService.updatePatch(id,request));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updatePatch(@PathVariable Long id,
                                                             @RequestBody CarRequestDTO request) {
        return ResponseEntity.ok(carService.updatePatch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.ok("Xóa thông tin thành công.");
    }

    @GetMapping
    public ResponseEntity<Page<CarResponseDTO>> getAll(
            //ph hoc
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)

    {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(carService.findAll(pageable,keyword));
    }


}

