package com.eglobal.encryptpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eglobal.encryptpassword.encryptdecrypt.EncryptDecrypt;
import com.eglobal.encryptpassword.models.Dto_ConnectionPool;
import com.eglobal.encryptpassword.models.Param;
import com.eglobal.encryptpassword.models.dto_consulta;
import com.eglobal.encryptpassword.respository.CTPropertiesDBRepository;
import com.eglobal.encryptpassword.utils.ConfigDBProperties;
import com.eglobal.encryptpassword.utils.SpringJdbcTemplateConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class EncryptPasswordApplication implements CommandLineRunner {

    @Autowired
    private CTPropertiesDBRepository ctPropertiesDBRepository;

    @Autowired
    private EncryptDecrypt decrypt;

    public static void main(String[] args) {
        SpringApplication.run(EncryptPasswordApplication.class, args);

    }

    @Override
    public void run(String... args) {
        Dto_ConnectionPool dto = null;
        Param Newdto = new Param();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String usuario = null;
        String password = null;
        String id_adquirente = null;
        String Script = null;
        String file = null;
        dto_consulta consulta = null;
        System.out.println("PARAMETROS ESPERADOS :");
        System.out.println("1- : [user]");
        System.out.println("2- : [pass] :");
        System.out.println("3- : [script]:");
        System.out.println("4- : [id_adquirente] :");

        /* validacion mayor a 0 */
        if (args.length > 0) {
            /* validacion mayor a 4 lanzar error */
            if (args.length > 4) {
                System.out.println("Se agregaron mas de 4 parametros favor de validar");
                System.exit(0);
            }
            /* validacion menor a 4 lanzar error */
            if (args.length < 4) {
                System.out.println("Se agregaron menos de 4 parametros favor de validar");
                System.exit(0);
            }

            if (args[0].equals("na") && args[1].equals("na") && args[2].equals("na") && args[3].equals("na")) {
                System.out.println("no se realizo ninguna accion por que se paso el parametro 4 : [na]");
                System.exit(0);
            } else {
                /* caso especifico para modificar solo el scrips */

                usuario = args[0];
                password = args[1];
                file = args[2];
                id_adquirente = args[3];

                if (!id_adquirente.equals("na")) {
                    if (ctPropertiesDBRepository.validaUsuario(id_adquirente)) {
                        System.out.println("id_adquirente localizado : " + id_adquirente);
                        String pass = "";
                        consulta = ctPropertiesDBRepository.getPoolID(Integer.parseInt(id_adquirente));
                        System.out.println("=====json: " + consulta.getJson());

                        if (!file.equals("na")) {

                            try {
                                Script = ctPropertiesDBRepository.leerQuerys();
                                Newdto.setScrip(Script.trim());
                            } catch (Exception ex) {
                                Logger.getLogger(EncryptPasswordApplication.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            Newdto.setScrip(consulta.getScript());
                        }

                        dto = gson.fromJson(consulta.getJson(), Dto_ConnectionPool.class);
                        pass = decrypt.encryptBase64(password);
                        dto.setUser(usuario.equals("na") ? dto.getUser() : usuario);
                        dto.setPass(password.equals("na") ? dto.getPass() : pass);
                        System.err.println("==== user: " + dto.getUser());
                        System.err.println("==== pass: " + dto.getPass());
                        System.err.println("==== Scrip: " + Newdto.getScrip());
                        String NewJson = gson.toJson(dto);
                        System.out.println("=====NewJson: " + NewJson);
                        ctPropertiesDBRepository.updateScript(NewJson, Newdto.getScrip().trim(), Integer.parseInt(id_adquirente));
                        System.out.println("======Se actualiza Script del adquirente : " + id_adquirente);
                        System.exit(0);

                    } else {
                        System.out.println("datos  no localizados");
                        System.exit(0);
                    }
                } else {
                    System.out.println("datos id_adquirente no localizado");
                    System.exit(0);
                }

            }
        } else {
            System.out.println(
                    "\n \nNo hay argumentos para aplicacion intente [ java -jar EncryptPassword.jar usuario password ] o [ java -jar EncryptPassword.jar password usuario ]");
        }

    }

}
