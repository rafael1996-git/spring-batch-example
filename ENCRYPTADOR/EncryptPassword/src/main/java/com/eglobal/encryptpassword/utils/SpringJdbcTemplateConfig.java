/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eglobal.encryptpassword.utils;

import com.eglobal.encryptpassword.EncryptPasswordApplication;
import com.eglobal.encryptpassword.encryptdecrypt.EncryptDecrypt;
import com.eglobal.encryptpassword.insumos.LeerConsulta;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author xhls08
 */
@Configuration

@ComponentScan("com.baeldung.jdbc")
public class SpringJdbcTemplateConfig {

    @Autowired
    private EncryptDecrypt decrypt;

    public ConfigDBProperties leerPropertiesDB() throws Exception {
        LeerConsulta lecp = new LeerConsulta();
        ConfigDBProperties mapaEmisores = lecp.obtenerCredentials("Localdb.properties");
        return mapaEmisores;
    }

    @Bean

    public DataSource mysqlDataSource() {
        ConfigDBProperties o = new ConfigDBProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        String pass = "";
        try {

            o = SpringJdbcTemplateConfig.this.leerPropertiesDB();
//            pass = decrypt.decryptBase64(o.getPass());
            dataSource.setDriverClassName(o.getDriver());
            dataSource.setUrl(o.getHost());
            dataSource.setUsername(o.getUser());
            dataSource.setPassword(o.getPass());

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("ERROR: ");

        }

        return dataSource;

    }
}
