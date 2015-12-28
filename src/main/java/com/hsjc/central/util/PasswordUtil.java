package com.hsjc.central.util;

import com.hsjc.central.domain.UserMain;
import com.hsjc.central.domain.UserTemp;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @author : zga
 * @date : 2015-12-2
 *
 * password 帮助类
 */
@Component
public class PasswordUtil {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(UserTemp userTemp) {

        userTemp.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(algorithmName, userTemp.getPassword(), ByteSource.Util.bytes(userTemp
                .getCredentialsSalt()), hashIterations).toHex();

        userTemp.setPassword(newPassword);
    }

    public void encryptPassword(UserMain userMain) {

        userMain.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(algorithmName, userMain.getPassword(), ByteSource.Util.bytes(userMain
                .getCredentialsSalt()), hashIterations).toHex();

        userMain.setPassword(newPassword);
    }

    public void test(){
        String salt = randomNumberGenerator.nextBytes().toHex();
        String newPassword = new SimpleHash(algorithmName, "123456", ByteSource.Util.bytes("admin"+salt), hashIterations).toHex();

        System.out.println("salt is >>" + salt);
        System.out.println("newPassword is >>" + newPassword);
    }

    public static void main(String[] args) {
        new PasswordUtil().test();
    }
}