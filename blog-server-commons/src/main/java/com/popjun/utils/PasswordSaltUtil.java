package com.popjun.utils;

import com.popjun.constants.Constant;
import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

/**
 * 对密码进行加盐处理
 */
public class PasswordSaltUtil {

    public static String  passwordAddSalt(String loginName,String pswd) {
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(Constant.UserConstants.STATIC_SALT));
        hashService.setHashAlgorithmName(Constant.UserConstants.ALGORITHM_MD5);
        hashService.setHashIterations(2);
        HashRequest request = new HashRequest.Builder().setSalt(loginName).setSource(pswd).build();
        String res = hashService.computeHash(request).toHex();
        return res;
    }

}
