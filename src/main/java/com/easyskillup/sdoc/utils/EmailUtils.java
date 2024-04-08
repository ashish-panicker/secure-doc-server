package com.easyskillup.sdoc.utils;

public class EmailUtils {

    public static String getNewAccountVerifyMessage(String name, String host, String token) {
        return String.format("""
                Hello %s
                                
                Your new account has been created. Please click on the link to verify your account.
                %s
                                
                                
                Support Team
                """, name, getVerificationUrl(host, token, "account"));
    }

    private static String getVerificationUrl(String host, String token, String action) {
        return String.format("%s/verify/%s?token=%s", host, action, token);
    }

    public static String getResetPasswordMessage(String name, String host, String token) {
        return String.format("""
                Hello %s
                                
                Please click on the link to reset password of your account.
                %s
                                
                                
                Support Team
                """, name, getVerificationUrl(host, token, "password"));
    }
}
