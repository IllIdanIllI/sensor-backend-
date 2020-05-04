package com.sensor.util.encoder.impl;

import com.sensor.util.encoder.ApplicationCoder;
import org.springframework.stereotype.Service;

@Service
public class SimpleIdCoder implements ApplicationCoder<Long, String> {


    @Override
    public String encode(Long param) {
        StringBuilder sb = new StringBuilder();
        String str = String.valueOf(param);
        char tmp = str.charAt(0);
        int count = 1;
        for (int idx = 1; idx < str.length(); idx++) {
            char curr = str.charAt(idx);
            if (curr == tmp) {
                count++;
            } else {
                sb.append(tmp).append(count);
                count = 1;
            }
            tmp = curr;
        }
        sb.append(tmp).append(count);
        return sb.toString();
    }

    @Override
    public Long decode(String param) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < param.length(); i += 2) {
            int count = Integer.valueOf("" + param.charAt(i + 1));
            for (int j = 0; j < count; j++) {
                sb.append(param.charAt(i));
            }
        }
        return Long.parseLong(sb.toString());
    }
}
