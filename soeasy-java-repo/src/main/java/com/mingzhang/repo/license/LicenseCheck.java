package com.mingzhang.repo.license; /**
 * @Description
 * @auther Administrator
 * @create 2018-12-18 17:51
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成license
 *
 * @author happyqing
 * 2014.6.15
 */
public class LicenseCheck {

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCH/M01PLgbfPGAuh/1xbHPfvcXJhqCampFBgUW\n" +
            "/mZxydnKVoi+m4l/W18zidXMismmJiilpVampqKfSrko0V/fVjQ3WBBhMZ5Wh756O1Babm2/SB7k\n" +
            "FkVsKrXkswDXcK9g7HhA+H2Uawd64xJoEZGu5YeVdkDb0wY/fALspwo+lwIDAQAB";

    public static String decode(String path) throws Exception {
        byte[] encodedData = Base64Utils.fileToByte(path);
        //解密
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        return target;
    }

    public static boolean isHavaRight() {
        boolean flag = false;
        try {
            String path = CommonParameter.confFilePath;
            String file = path + "license.dat";
            String decode = decode(file);
            String[] split = decode.split(";");
            String time = split[1].split("=")[1];
            System.out.println("有效期到:" + time);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date licenseDate = df.parse(time);
            Date now = new Date();
            flag = licenseDate.after(now);
            return flag;
        } catch (Exception e) {
            flag = false;
        } finally {
            return flag;
        }
    }


}