package br.com.configurations;

import br.com.dtos.requests.manager.ChangePasswordDTO;

public class Authentication {
    private static String token;
    private static ChangePasswordDTO changePasswordDTO =  new ChangePasswordDTO();

    public static ChangePasswordDTO getChangePasswordDTO() {
        return changePasswordDTO;
    }

    public static void setEmail(String email) {
        changePasswordDTO.setEmail(email);
    }

    public static void setPassword(String password) {
        changePasswordDTO.setPassword(password);
    }

    public static void setRepeat(String password) {
        changePasswordDTO.setRepeatPassword(password);
    }

    public static void setToken(String token) {
        Authentication.token = token;
    }

    public static String getToken() {
        return token;
    }
}
