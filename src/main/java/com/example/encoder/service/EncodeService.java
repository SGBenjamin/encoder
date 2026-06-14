package com.example.encoder.service;


import com.example.encoder.model.request.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.security.*;

@Service
@Slf4j
public class EncodeService {

    private static final String cipher = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()*+,-./";

    public String encode(EncodeDecodeRequest request) throws IllegalArgumentException {
        log.info("Encode Start");
        if(request == null
                || isNullOrBlank(request.getOriginalMessage())) {
            throw new IllegalArgumentException("Missing/Invalid fields");
        }
        String offsetChar = String.valueOf(request.getOriginalMessage().charAt(0));
        String shiftedCipher = generateNewCipher(offsetChar);
        return encodeDecodeMessage(request.getOriginalMessage().substring(1), cipher, shiftedCipher, offsetChar);
    }

    public String decode (EncodeDecodeRequest request) throws IllegalArgumentException {
        log.info("decode Start");
        if(request == null || isNullOrBlank(request.getOriginalMessage())) {
            throw new IllegalArgumentException("Missing/Invalid fields");
        }
        String offsetChar = String.valueOf(request.getOriginalMessage().charAt(0));
        String shiftedCipher = generateNewCipher(offsetChar);
        log.info(shiftedCipher);
        return encodeDecodeMessage(request.getOriginalMessage().substring(1), shiftedCipher, cipher, null);
    }

    private String generateNewCipher(String cipherStartChar) {
        log.info("generateNewCipher start");
        int cipherStartIndex = cipher.indexOf(cipherStartChar);
        if (cipherStartIndex == -1) {
            throw new IllegalArgumentException("Invalid cipher offset: " + cipherStartChar);
        }
        return cipher.substring(cipherStartIndex) + cipher.substring(0, cipherStartIndex);
    }

    private String encodeDecodeMessage(String originalMessage, String from, String to, String cipherOffset) {
        log.info("encodeMessage start");
        StringBuilder returnMessage = new StringBuilder();
        if(cipherOffset != null && !cipherOffset.trim().equals("")) {
            returnMessage.append(cipherOffset);
        }
        for (int i = 0; i < originalMessage.length(); i++) {
            log.info(String.valueOf(originalMessage.charAt(i)));
            if(originalMessage.charAt(i) == ' ') {
                returnMessage.append(" ");
            } else {
                int index = from.indexOf(originalMessage.charAt(i));
                if (index == -1) {
                    returnMessage.append(originalMessage.charAt(i));
                } else {
                    returnMessage.append(to.charAt(index));
                }}
        }
        return returnMessage.toString();
    }

    private static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }
}
