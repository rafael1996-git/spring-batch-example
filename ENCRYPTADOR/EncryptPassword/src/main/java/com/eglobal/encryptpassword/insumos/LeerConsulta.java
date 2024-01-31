package com.eglobal.encryptpassword.insumos;

import java.io.File;
import java.io.IOException;



import com.eglobal.encryptpassword.IDaoServices.LeerConsultaDao;
import com.eglobal.encryptpassword.utils.ConfigDBProperties;
import java.io.FileInputStream;
import java.util.Properties;

public class LeerConsulta implements LeerConsultaDao<String> {

    @Override
    public String obtenerScript(String nArchivo) throws Exception {
        String map = null;
        String rutaXML = PathsProjectProvider.getConf().getHomeConfBatch() + nArchivo;
        File file = new File(rutaXML);
        System.out.println("rutaXML: " + rutaXML);
        // Crear un objeto Properties
        Properties prop = new Properties();
        try {
            // Cargar el archivo de propiedades externo

            FileInputStream fis = new FileInputStream(rutaXML);
            prop.load(fis);
            // Leer las propiedades del archivo de propiedades externo
            map = prop.getProperty("query.info");
        } catch (IOException ex) {
            throw new Exception("No fue posible abrir o cerrar el archivo de propiedades de los DataSource.");
        }
        return map;
    }

    
    
    public ConfigDBProperties obtenerCredentials(String nArchivo) throws Exception {
        ConfigDBProperties map = new ConfigDBProperties();
        String rutaProperties = PathsProjectProvider.getConf().getHomeConfBatch() + nArchivo;
        File file = new File(rutaProperties);
        System.out.println("rutaProperties: " + rutaProperties);
        // Crear un objeto Properties
        Properties prop = new Properties();
        try {
            // Cargar el archivo de propiedades externo

            FileInputStream fis = new FileInputStream(rutaProperties);
            prop.load(fis);
            // Leer las propiedades del archivo de propiedades externo
            
            String drive=prop.getProperty("DRIVERCLASSNAME");
            String url=prop.getProperty("URL");
            String pwd=prop.getProperty("PWD");
            String user=prop.getProperty("USER");
            map.setDriver(drive);
            map.setHost(url);
            map.setPass(pwd);
            map.setUser(user);
        } catch (IOException ex) {
            throw new Exception("No fue posible abrir o cerrar el archivo de propiedades de los DataSource Properties");
        }
        System.out.println("credentials:map:  " + map);

        return map;
    }


}
