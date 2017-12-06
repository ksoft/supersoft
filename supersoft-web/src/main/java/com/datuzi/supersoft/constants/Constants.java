package com.datuzi.supersoft.constants;

import java.io.File;

/**
 * 公用常量类
 */
public final class Constants {
    /**
     * 登录身份key
     */
    public static final String PRINCIPAL_ATTRIBUTE_NAME = "store_show_ticket";

    /**
     * 微信openid
     */
    public static final String WECHAT_OPENID_ATTRIBUTE_NAME = "store_show_openid";

    /**
     * 列表页，每页的数量
     */
    public static final int PAGE_SIZE = 10;

    public static final String DZ_COOKIE = "store_show_dz_cookie";

    /**
     * auth key
     */
    public static final String REQ_HEADER_AUTH_KEY = "Token";
    /**
     * auth user key
     */
    public static final String REQ_HEADER_API_KEY = "api-user";
    public static final int MAX_FRONT_PICS_SIZE = 1;
    public static final int MAX_TYRE_PICS_SIZE = 6;
    public static final int MAX_SCENE_PICS_SIZE = 1;
    public static final char SEPERATOR_FILE = File.separatorChar;
    /**
     * 数据库图片保存的分隔符
     */
    public static final String SEPARATOR_PICS = "@#@";

    public static final String SESSION_KEY_KAPTCHA = "RESCUE_KAPTCHA_CODE";


    private Constants(){

    }
}
