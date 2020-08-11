package com.derick.application.controllers;

import com.derick.dto.county.CountyDto;
import com.derick.dto.county.CountyResponse;
import com.derick.service.ICountyService;
import com.derick.utils.LogFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class CountyController {

    @Autowired
    ICountyService countyService;

    @Autowired
    LogFile logFile;

    @GetMapping(value = "/api/county")
    public ResponseEntity<CountyResponse> getCounties()
    {
        CountyResponse response=new CountyResponse();
        response.setResponse("failed");
        try {
            response=countyService.getCounties();
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/api/county/add")
    public ResponseEntity<CountyResponse> saveCounty(@RequestBody CountyDto countyDto,
                                                     HttpServletRequest request, HttpServletResponse response)
    {
        CountyResponse countyResponse=new CountyResponse();
        countyResponse.setResponse("failed");
        try {
            countyResponse=countyService.saveCounty(countyDto);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(countyResponse);
    }

    @PutMapping(value = "/api/county/add")
    public ResponseEntity<CountyResponse> updateCounty(@RequestBody CountyDto countyDto,
                                                     HttpServletRequest request, HttpServletResponse response)
    {
        CountyResponse countyResponse=new CountyResponse();
        countyResponse.setResponse("failed");
        try {
            countyResponse=countyService.updateCounty(countyDto);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(countyResponse);
    }

    @GetMapping(value = "/api/county/{id}")
    public ResponseEntity<CountyResponse> getCounty(@PathVariable int id,
                                                     HttpServletRequest request, HttpServletResponse response)
    {
        CountyResponse countyResponse=new CountyResponse();
        countyResponse.setResponse("failed");
        try {
            countyResponse=countyService.getCounty(id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(countyResponse);
    }

    @GetMapping(value = "/api/county/status/{status}")
    public ResponseEntity<CountyResponse> getCounty(@PathVariable boolean status,
                                                     HttpServletRequest request, HttpServletResponse response)
    {
        CountyResponse countyResponse=new CountyResponse();
        countyResponse.setResponse("failed");
        try {
            countyResponse=countyService.getCounty(status);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(countyResponse);
    }

    @GetMapping(value = "/api/county/name/{name}")
    public ResponseEntity<CountyResponse> getCounty(@PathVariable String name,
                                                     HttpServletRequest request, HttpServletResponse response)
    {
        CountyResponse countyResponse=new CountyResponse();
        countyResponse.setResponse("failed");
        try {
            countyResponse=countyService.getCounty(name);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(countyResponse);
    }

    @DeleteMapping(value = "/api/county/delete/{id}")
    public ResponseEntity<CountyResponse> deleteCounty(@PathVariable int id,
                                                     HttpServletRequest request, HttpServletResponse response)
    {
        CountyResponse countyResponse=new CountyResponse();
        countyResponse.setResponse("failed");
        try {
            countyResponse=countyService.deleteCounty(id);
        }catch (Exception e){
            e.printStackTrace();
            logFile.error(e);
        }
        return ResponseEntity.ok(countyResponse);
    }

}
