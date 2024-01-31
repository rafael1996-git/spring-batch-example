/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eglobal.encryptpassword.models;

/**
 *
 * @author xhls08
 */
public class Param {

    private String user;
    private String pass;
    private String Scrip;
    private Integer id;

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

    public String getScrip() {
        return Scrip;
    }

    public void setScrip(String Scrip) {
        this.Scrip = Scrip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Param(String user, String pass, String Scrip, Integer id) {
        this.user = user;
        this.pass = pass;
        this.Scrip = Scrip;
        this.id = id;
    }

    public Param() {
    }


}
