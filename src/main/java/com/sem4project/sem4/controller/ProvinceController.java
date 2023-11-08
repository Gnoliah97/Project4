package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.service.ProvinceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Province", description = "Province API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/province")
@CrossOrigin
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
    public ResponseEntity<ResponseObject> getAllProvince(@RequestParam(required = false, defaultValue = "false") Boolean isDisable,
                                                         @RequestParam(defaultValue = "1") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize,
                                                         @RequestParam(required = false) String sort) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getAllProvince(isDisable, pageNumber, pageSize, sort))
                                .build()
                );

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getProvince(@PathVariable UUID id) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getProvinceById(id))
                                .build()
                );

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ResponseObject> updateProvince(@PathVariable UUID id, @RequestBody ProvinceDto provinceDto) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.updateProvince(id, provinceDto))
                                .build()
                );
    }
}
