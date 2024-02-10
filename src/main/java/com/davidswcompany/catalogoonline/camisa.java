/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.davidswcompany.catalogoonline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David Caiza
 */
public class camisa {

    public camisa(int Id, String Descripcion, String Talla, String Color, float precio) {
        this.Id=Id;
        this.Descripcion = Descripcion;
        this.Talla = Talla;
        this.Color = Color;
        this.precio = precio;
    }
    
    public camisa(){
        
    }
    
    int Id;
    String Descripcion;
    String Talla;
    String Color;
    float precio;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String Talla) {
        this.Talla = Talla;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public void GuardarCamisa(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "INSERT INTO camisas (Id, descripcion, color,talla,precio) VALUES (null,?,?,?,?)";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getDescripcion());
            parametro.setString(2, this.getColor());
            parametro.setString(3, this.getTalla());
            parametro.setFloat(4, this.getPrecio());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
     public void ModificarCamisa(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "UPDATE camisas SET descripcion=?, color=?,talla=?,precio=? WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getDescripcion());
            parametro.setString(2, this.getColor());
            parametro.setString(3, this.getTalla());
            parametro.setFloat(4, this.getPrecio());
            parametro.setInt(5, this.getId());


            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     
    
    public ArrayList<camisa> ObtenerCamisas(){
        Connection conexionDb = ConexionDb.getConnection();
        ResultSet rsCamisas;
        
        var camisas = new ArrayList<camisa>();
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "SELECT * FROM camisas";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            
            //Ejecutar la sentecia SQL
             rsCamisas=parametro.executeQuery();           
            
            while(rsCamisas.next()){              
                    camisas.add(new camisa(rsCamisas.getInt("Id"),rsCamisas.getString("descripcion"),rsCamisas.getString("talla"),rsCamisas.getString("color"),rsCamisas.getFloat("precio")));

            }
            
            conexionDb.close();
            return camisas;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }

    void EliminarCamisa() {
Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "DELETE FROM camisas WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setInt(1, this.getId());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
