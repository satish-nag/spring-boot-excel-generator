package com.example.springbootexcelgenerator.controller;

import com.example.springbootexcelgenerator.service.IExcelGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping
public class ExcelDataController {
    @Autowired
    IExcelGeneratorService excelGeneratorService;

    public ExcelDataController() {
    }

    @GetMapping(
            value = {"dummyExcelGenerator"},
            produces = {"application/octet-stream"}
    )
    public ResponseEntity generateDummyExcelSheet() {
        ByteArrayOutputStream outputStream = this.excelGeneratorService.generateExcelData();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=my_file.xls")
                .header("Content-Length",String.valueOf(outputStream.size()))
                .body(outputStream.toByteArray());
    }

    @GetMapping({"getExcelFromURL"})
    public ResponseEntity generateExcelData() {
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity requestEntity = RequestEntity.get("http://localhost:8188/dummyExcelGenerator", new Object[0]).build();
        ResponseEntity<byte[]> exchange = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<byte[]>() {
        });
        return exchange;
    }
}
