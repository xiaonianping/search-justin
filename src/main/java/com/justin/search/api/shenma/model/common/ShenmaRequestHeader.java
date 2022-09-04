package com.justin.search.api.shenma.model.common;

/**
 * @author Administrator
 */
public class ShenmaRequestHeader {

    private String username;

    private String password;

    private String token;

    public ShenmaRequestHeader() {
    }

    public ShenmaRequestHeader(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
