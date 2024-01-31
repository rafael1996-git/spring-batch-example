package com.eglobal.encryptpassword.IDaoServices;

import com.eglobal.encryptpassword.utils.ConfigDBProperties;

public interface LeerConsultaDao <T>{
    public T obtenerScript(String nArchivo) throws Exception;
}
