/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.impuestobase.dao;

import co.edu.uniandes.impuestobase.objects.ImpuestoObject;
import co.edu.uniandes.impuestobase.objects.PersonaObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Soto
 */
public class ImpuestoBaseDAO {

    private Connection conexion;
    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    String dbName = "//localhost:1527/sun-appserv-samples";
    String connectionURL = "jdbc:derby:" + dbName;

    public void inicializarConexion() {
        try {
            Class.forName(driver);
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conexion = DriverManager.getConnection(connectionURL);

            //body of code to go here
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void crearTablas() {
        String sql = "CREATE TABLE IMPUESTOS "
                + "(id BIGINT NOT NULL, "
                + " aniogravable VARCHAR(50), "
                + " base DOUBLE, "
                + " descuento DOUBLE, "
                + " placa VARCHAR(50), "
                + " total DOUBLE, "
                + " pagado INTEGER, "
                + " PRIMARY KEY ( id ))";
        String sql2 = "CREATE TABLE PERSONAS "
                + "(cc VARCHAR(50) not NULL, "
                + " nombre VARCHAR(50), "
                + " apellido VARCHAR(50), "
                + " PRIMARY KEY ( cc ))";
        String sql3 = "CREATE TABLE IMP_USUARIOS "
                + "(idimpuesto BIGINT not NULL, "
                + " cc VARCHAR(50), "
                + " PRIMARY KEY ( idimpuesto, cc ),"
                + " FOREIGN KEY (idimpuesto) REFERENCES IMPUESTOS(id),"
                + " FOREIGN KEY (cc) REFERENCES PERSONAS(cc))";
        try {
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
            st.executeUpdate(sql3);
            st.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }

    }

    public boolean guardarImpuesto(ImpuestoObject impto, String cc) {
        try {
            String sql = "INSERT INTO IMPUESTOS VALUES(" + impto.getIdImpuesto() + ",'" + impto.getAnioGravable() + "'," + impto.getBase() + "," + impto.getDescuento() + ",'" + impto.getPlaca() + "'," + impto.getTotal() + ",0)";
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            String sql2 = "INSERT INTO IMP_USUARIO VALUES('" + cc + "'," + impto.getIdImpuesto() + ")";
            st.executeUpdate(sql2);
            st.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean pagarImpuesto(long idImpto) {
        try {
            String sql = "UPDATE IMPUESTOS SET pagado=1 WHERE id=" + idImpto;
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean guardarTotalImpuesto(double total, long idImpto) {
        try {
            String sql = "UPDATE IMPUESTOS SET total=" + total + " WHERE id=" + idImpto;
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<ImpuestoObject> darImpuestosUsuario(String cc) {
        ArrayList<ImpuestoObject> rta = new ArrayList<ImpuestoObject>();
        try {
            String sql = "SELECT * FROM IMPUESTOS JOIN IMP_USUARIOS ON IMPUESTOS.id=IMP_USUARIOS.idimpuesto WHERE IMP_USUARIOS.cc='" + cc + "'";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ImpuestoObject imp = new ImpuestoObject();
                imp.setIdImpuesto(rs.getLong("id"));
                imp.setAnioGravable(rs.getString("aniogravable"));
                imp.setBase(rs.getDouble("base"));
                imp.setDescuento(rs.getDouble("descuento"));
                if (rs.getInt("pagado") == 1) {
                    imp.setPagado(true);
                } else {
                    imp.setPagado(false);
                }
                imp.setPlaca(rs.getString("placa"));
                imp.setTotal(rs.getDouble("total"));

                rta.add(imp);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rta;
    }

    public ArrayList<ImpuestoObject> darImpuestosAnioGravable(String anio) {
        ArrayList<ImpuestoObject> rta = new ArrayList<ImpuestoObject>();
        try {
            String sql = "SELECT * FROM IMPUESTOS WHERE aniogravable='" + anio + "'";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ImpuestoObject imp = new ImpuestoObject();
                imp.setIdImpuesto(rs.getLong("id"));
                imp.setAnioGravable(rs.getString("aniogravable"));
                imp.setBase(rs.getDouble("base"));
                imp.setDescuento(rs.getDouble("descuento"));
                if (rs.getInt("pagado") == 1) {
                    imp.setPagado(true);
                } else {
                    imp.setPagado(false);
                }
                imp.setPlaca(rs.getString("placa"));
                imp.setTotal(rs.getDouble("total"));

                rta.add(imp);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rta;
    }

    public boolean guardarPersona(PersonaObject persona) {
        try {
            String sql = "INSERT INTO PERSONAS VALUES('" + persona.getIdIPersona() + "','" + persona.getNombre() + "','" + persona.getApellido() + "')";
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizarPersona(PersonaObject persona) {
        try {
            String sql = "UPDATE PERSONAS SET nombre='" + persona.getNombre() + "', apellido='" + persona.getApellido() + "' WHERE cc='" + persona.getIdIPersona() + "'";
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            st.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> darCedulas() {
        ArrayList<String> rta = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PERSONAS";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rta.add(rs.getString("cc"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rta;
    }

    public ArrayList<String> darAniosGravables() {
        ArrayList<String> rta = new ArrayList<String>();
        try {
            String sql = "SELECT DISTINCT aniogravable FROM IMPUESTOS";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rta.add(rs.getString("aniogravable"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rta;
    }

    public PersonaObject darInfoPersona(String cc) {
        try {
            String sql = "SELECT * FROM PERSONAS WHERE cc='" + cc + "'";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                PersonaObject p = new PersonaObject();
                p.setIdIPersona(rs.getString("cc"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                rs.close();
                st.close();
                return p;
            }

            rs.close();
            st.close();
            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
