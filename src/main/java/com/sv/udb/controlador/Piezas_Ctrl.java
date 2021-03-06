/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Piezas;
import com.sv.udb.recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vergo_000
 */
public class Piezas_Ctrl {
    private final Connection conn;

    public Piezas_Ctrl() {
        this.conn = new Conexion().getCon();
    }
    public List<Piezas> consTodo()
    {
       List<Piezas> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM piezas");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Piezas(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar las piezas: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
}
