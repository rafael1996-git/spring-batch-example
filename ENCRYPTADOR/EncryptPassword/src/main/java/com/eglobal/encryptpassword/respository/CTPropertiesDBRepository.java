package com.eglobal.encryptpassword.respository;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.eglobal.encryptpassword.insumos.LeerConsulta;
import com.eglobal.encryptpassword.models.dto_consulta;
import com.eglobal.encryptpassword.utils.ConfigDBProperties;

@Repository
public class CTPropertiesDBRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void init(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public boolean validaUsuario(String id_adquirente) {
//		return jdbcTemplate.query("select exists (select c_proper_username from apis_eglobal_gral.ct_propertiesdb where c_proper_username = '"+ usuario + "') as resultado;",
        return jdbcTemplate.query(
                "select exists (select *  from eglobal_sicrsch.c_connection_pool t where t.cod_adquirente_pais  = '"
                + id_adquirente + "') as resultado;",
                new ResultSetExtractor<Boolean>() {
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                boolean resultado = false;
                while (rs.next()) {
                    resultado = rs.getBoolean("resultado");
                }
                return resultado;
            }
        });
    }

    public void updateScript(String newCredential, String Script, Integer id_adquirente) {
        jdbcTemplate.update(
                "update eglobal_sicrsch.c_connection_pool   set conexion_json = ?, script = ? where cod_adquirente_pais = ?",
                newCredential, Script, id_adquirente);
    }

    public dto_consulta getPoolID(int id_adquirente) {

        return jdbcTemplate.query(
                "select t.conexion_json,t.script  from eglobal_sicrsch.c_connection_pool t where t.cod_adquirente_pais ="
                + id_adquirente + ";",
                new ResultSetExtractor<dto_consulta>() {
            public dto_consulta extractData(ResultSet rs) throws SQLException, DataAccessException {
                dto_consulta resultado = new dto_consulta();
                while (rs.next()) {
                    resultado.setJson(rs.getString(1));
                    resultado.setScript(rs.getString(2));
                }
                return resultado;
            }
        });
    }

    public String leerQuerys() throws Exception {
        LeerConsulta lecp = new LeerConsulta();
        String mapaEmisores = lecp.obtenerScript("consultas.properties");
        return mapaEmisores;
    }


}
