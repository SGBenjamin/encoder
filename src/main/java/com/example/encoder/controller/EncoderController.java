package com.example.encoder.controller;


import com.example.encoder.model.request.*;
import com.example.encoder.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class EncoderController {

    @Autowired
    EncodeService encodeService;

    @PostMapping (value = "/encode")
    public String encode(@RequestBody EncodeDecodeRequest request) throws Exception {
        return encodeService.encode(request);
    }

    @PostMapping (value = "/decode")
    public String decode(@RequestBody EncodeDecodeRequest request) throws Exception {
        return encodeService.decode(request);
    }


}
