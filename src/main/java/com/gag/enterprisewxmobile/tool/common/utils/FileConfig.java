package com.gag.enterprisewxmobile.tool.common.utils;

import org.springframework.stereotype.Component;

@Component
public class FileConfig {

    /** 上传路径 */
    private static String profile = "D:/profile/";


    public static String getProfile() {
        return profile;
    }

    public static void setProfile(String profile) {
        FileConfig.profile = profile;
    }
}
