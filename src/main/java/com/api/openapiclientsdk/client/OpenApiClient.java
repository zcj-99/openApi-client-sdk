package com.api.openapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.api.openapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.api.openapiclientsdk.utils.SignUtils.genSign;


/**
 * 调用第三方接口客户端
 * @author zhuchengji
 * @date 2023年03月10日下午3:53
 * @Description
 */
public class OpenApiClient {
    private String accessKey;
    private String secretKey;
    public OpenApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.get("http://localhost:8213/api/name/", paramMap);
        System.out.println(result);
        return result;
    }
    public String getNameByPost( String name){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.post("http://localhost:8213/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    private Map<String,String> getHeaders(String body){
        Map<String ,String> hashmap = new HashMap<>();
        hashmap.put("accessKey",accessKey);
        //一定不能直接发送
        //hashmap.put("secretKey",secretKey);
        hashmap.put("nonce", RandomUtil.randomNumbers(4));
        hashmap.put("body",body);
        hashmap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        hashmap.put("sign",genSign(body,secretKey));
        return hashmap;
    }

    public String getUserNameByPost( User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8213/api/name/user/")
                .addHeaders(getHeaders(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;

    }
}
