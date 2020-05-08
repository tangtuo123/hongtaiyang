package com.hongtaiyang.common.encrypt;

import com.alibaba.fastjson.JSONObject;
import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;
import com.hongtaiyang.common.utils.RSAUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.InputStream;
import java.util.Map;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/5/8 10:57
 */
public class DecryptHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;
    private InputStream body;

    private static final String PARAMETER_NAME = "encryptStr";

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }

    public DecryptHttpInputMessage(HttpInputMessage inputMessage, String privateKey, String charset) throws Exception {
        if (StringUtils.isBlank(privateKey)) {
            throw SysException.asException(SystemCode.ENCRYPT_ERROR);
        }
        //获取请求头内容
        this.headers = inputMessage.getHeaders();
        String bodyStr = IOUtils.toString(inputMessage.getBody(), charset);
        Map<String, String> map = JSONObject.parseObject(bodyStr, Map.class);
        if (null == map || StringUtils.isBlank(map.get(PARAMETER_NAME))) {
            throw SysException.asException(SystemCode.ENCRYPT_ERROR);
        }
        String encryptStr = map.get(PARAMETER_NAME);
        //直接对内容进行解密
        String decryptBody = new String(RSAUtils.decryptByPrivateKey(Base64.decodeBase64(encryptStr), privateKey), charset);
        //数据写回
        this.body = IOUtils.toInputStream(decryptBody, charset);
    }
}
