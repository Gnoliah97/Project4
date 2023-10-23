package com.sem4project.sem4.controller;

import com.sem4project.sem4.dto.dtomodel.ProvinceDto;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.exception.CRUDException;
import com.sem4project.sem4.repository.ProvinceRepository;
import com.sem4project.sem4.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/province")
public class ProvinceController {
    private final ProvinceService provinceService;

    @ExceptionHandler({CRUDException.class})
    public ResponseEntity<ResponseObject> handleException(Exception exception){
        return ResponseEntity.status(400)
                .body(
                        ResponseObject.builder()
                                .message(exception.getMessage())
                                .data(false)
                                .build()
                );
    }

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
    public ResponseEntity<ResponseObject> getAllProvince() {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getAllProvince())
                                .build()
                );

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getProvince(@PathVariable Long id){
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Success")
                                .data(provinceService.getProvince(id))
                                .build()
                );

    }

}
