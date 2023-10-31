package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/province")
public class ProvinceController {
    private final ProvinceService provinceService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> createProvince(@RequestBody ProvinceDto provinceDto) {
        provinceService.create(provinceDto);
        return ResponseEntity.status(201)
                .body(
                        ResponseObject.builder()
                                .message("Created")
                                .data(true)
                                .build()
                );

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> getAllProvince() {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getAllProvince())
                                .build()
                );

    }
    @RequestMapping(value = "/getAllAvailable", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getAllAvailableProvince() {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getAllAvailableProvince())
                                .build()
                );

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getProvince(@PathVariable Long id) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getProvince(id))
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

    @RequestMapping(value = "/setIsDisable/{idDisable}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseObject> deleteProvince(@RequestBody ProvinceDto provinceDto, @PathVariable boolean idDisable) {
        provinceService.setDisableProvince(provinceDto, idDisable);
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(true)
                                .build()
                );
    }
}
