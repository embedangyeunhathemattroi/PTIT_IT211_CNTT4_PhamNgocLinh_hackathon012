package com.example.hackathon012.service.impl;

import com.example.hackathon012.dto.CarRequestDTO;
import com.example.hackathon012.dto.CarResponseDTO;
import com.example.hackathon012.entity.Car;
import com.example.hackathon012.exception.ResourceNotFoundException;
import com.example.hackathon012.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hackathon012.service.CarService;
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    @Transactional
    public CarResponseDTO create(CarRequestDTO carRequestDTO) {
        Car car = Car.builder().
                model(carRequestDTO.getModel()).
                brand(carRequestDTO.getBrand()).
                price(carRequestDTO.getPrice()).
                status(carRequestDTO.getStatus()).
                isDeleted(false).
                build();

        Car saved = carRepository.save(car);
        return mapToResponse(saved);
    }

    @Override
    public Page<CarResponseDTO> findAll(Pageable pageable, String keyword){
        return carRepository.findByKeyword(keyword, pageable).map(this :: mapToResponse);
    }



    @Override
    @Transactional
    public  CarResponseDTO updatePut(Long id,CarRequestDTO request){
        Car car =carRepository.findActiveById(id)
                .orElseThrow(()->new ResourceNotFoundException("car not found"));
        car.setModel(request.getModel());
        car.setBrand(request.getBrand());
        car.setPrice(request.getPrice());
        car.setStatus(request.getStatus());
        return mapToResponse(carRepository.save(car));

    }

    @Override
    @Transactional
    public  CarResponseDTO updatePatch(Long id,CarRequestDTO request){
        Car car =carRepository.findActiveById(id)
                .orElseThrow(()->new ResourceNotFoundException("ko tim thay id"+id));

       if(request.getModel() != null && !request.getModel().isBlank()){
           car.setModel(request.getModel());
       }
        if(request.getBrand() != null && !request.getBrand().isBlank()){
            car.setBrand(request.getBrand());
        }

        if(request.getPrice() != null ){
            car.setPrice(request.getPrice());
        }

        if(request.getStatus() != null){
            car.setStatus(request.getStatus());
        }
        return mapToResponse(carRepository.save(car));


    }
    @Override
    @Transactional
    public void delete(Long id){
        Car car =carRepository.findActiveById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ko tim thay id"+id));
        car.setIsDeleted(true);
        carRepository.save(car);
    }

    private CarResponseDTO mapToResponse(Car car) {
        CarResponseDTO response = new CarResponseDTO();
        response.setId(car.getId());
        response.setModel(car.getModel());
        response.setBrand(car.getBrand());
        response.setPrice(car.getPrice());
        response.setStatus(car.getStatus());
        return response;
    }
}
