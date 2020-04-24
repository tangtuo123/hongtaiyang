package com.hongtaiyang.common.constant;

import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 15:27
 * @description：
 * @version: 1.0.0
 */
public class RedisConstant {
    /**
     * token存入redis的key值的前缀
     */
    public static String TOKEN_ADMIN_PREFIX = "token:admin:";
    public static String TOKEN_CLIENT_PREFIX = "token:client:";
    public static String TOKEN_ARTIFICER_PREFIX = "token:artificer:";

    public static String getTokenPrefixByTerminalType(String terminalType) {
        String tokenPrefix;
        switch (terminalType) {
            case TerminalTypeConstant.TYPE_ADMIN:
                tokenPrefix = TOKEN_ADMIN_PREFIX;
                break;
            case TerminalTypeConstant.TYPE_ARTIFICER:
                tokenPrefix = TOKEN_CLIENT_PREFIX;
                break;
            case TerminalTypeConstant.TYPE_CLIENT:
                tokenPrefix = TOKEN_ARTIFICER_PREFIX;
                break;
            default:
                throw SysException.asException(SystemCode.RUNTIME_ERROR, "unknown terminalType");
        }
        return tokenPrefix;
    }

}
