package com.example.encoder.service;


import com.example.encoder.model.request.*;
import org.springframework.stereotype.*;

@Service
public class EncodeService {

    private String cipher = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()*+,-./";

    public String encode(EncodeDecodeRequest request) {
        return "encoded";
    }

    public String decode (EncodeDecodeRequest request) {
        return "decoded";
    }

    public String showCipher() {
        return cipher;
    }
}
