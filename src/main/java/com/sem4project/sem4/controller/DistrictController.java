package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.DistrictDto;
import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.repository.DistrictRepository;
import com.sem4project.sem4.service.DistrictService;
import com.sem4project.sem4.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/district")

public class DistrictController {
    private final DistrictService districtService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> createDistrict(@RequestBody DistrictDto districtDto) {
        districtService.create(districtDto);
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Created")
                                .data(true)
                                .build()
                );
    }
}
