/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eglobal.encryptpassword.utils;

/**
 *
 * @author xhls08
 */
public class ConfigDBProperties {
      private String user;
    private String pass;
    private String host;
    private String driver;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public ConfigDBProperties(String user, String pass, String host, String driver) {
        this.user = user;
        this.pass = pass;
        this.host = host;
        this.driver = driver;
    }

    public ConfigDBProperties() {
    }

}
