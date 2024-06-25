package mx.edu.utez.pruebaf.dao;

import mx.edu.utez.pruebaf.model.User;
import mx.edu.utez.pruebaf.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    public User getOne(String user, String contra){
        User u = new User();
        String query = "select * from users where nombre = ? and contra = sha2(?,256)";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, contra);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                u.setNombre(rs.getString("nombre"));
                u.setContra(rs.getString("contra1"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    public boolean insert(User u){
        boolean respuesta = false;
        String query = "insert into users(nombre,correo,contra) values(?,?,sha2(?,256))";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,u.getNombre());
            ps.setString(2,u.getCorreo());
            ps.setString(3,u.getContra());

            if(ps.executeUpdate()>0){
                respuesta = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return respuesta;
    }

    public ArrayList<User> getAll(){
        ArrayList<User> lista = new ArrayList<>();
        String query = "select * from users";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setContra(rs.getString("contra"));
                u.setCodigo(rs.getString("codigo"));
                //u.setEstado(rs.getBoolean("estado"));
                lista.add(u);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    public boolean update(User i){
        boolean flag = false;
        String query = "update users set nombre=?, contra=?, correo=? where id=?";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            if(ps.executeUpdate()>0){
                //que si se hizo la modificacion o modificaciones
                flag = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
}
