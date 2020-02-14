package com.changgou.system;

import com.changgou.system.util.JwtUtil;
import org.junit.Test;

public class GenerateJwt {
    @Test
    public void test(){
        String jwt = JwtUtil.createJWT("888", "subject", null);
        System.out.println(jwt);
    }
}
