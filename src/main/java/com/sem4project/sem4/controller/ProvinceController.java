package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.service.ProvinceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Province", description = "Province API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/province")
public class ProvinceController {
    private final ProvinceService provinceService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> createProvince(@RequestBody ProvinceDto provinceDto) {
        provinceService.createProvince(provinceDto);
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Created")
                                .data(true)
                                .build()
                );

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getAllProvince(@RequestParam(required = false) Boolean isDisable) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getAllProvince(isDisable))
                                .build()
                );

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getProvince(@PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getProvinceById(id))
                                .build()
                );

    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseObject> updateProvince(@RequestBody ProvinceDto provinceDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.updateProvince(provinceDto))
                                .build()
                );
    }
}
