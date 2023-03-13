package com.api.openapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;


/**
 * @author zhuchengji
 * @date 2023年03月13日下午3:10
 * @Description
 */
public class SignUtils {
    /**
     * 生成签名
     * @author zhuchengji
     * @date 13/3/2023 下午3:01
     * @param body
     * @param secretKey
     * @return java.lang.String
     * @Description:
     */
    public static String genSign(String body, String secretKey){
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body +"." + secretKey;
        return md5.digestHex(content);
    }
}
