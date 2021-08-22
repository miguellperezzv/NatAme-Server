/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import negocio.modelos.Pedido;

/**
 *
 * @author asus
 */
public class PedidoDAO {

    private String mensaje = "";

    public PedidoDAO() {

    }

    public String agregarPedido(Connection conn, Pedido pedido, String[] productos) {

        PreparedStatement pstPedido = null;
        PreparedStatement pstProductos = null;

        String sqlPedido = "INSERT INTO NATAME.PEDIDO VALUES(NULL, 0.1,CURRENT_DATE,'PENDIENTE','PSE',NULL,NULL,'rep123','usercli')";

        System.out.println(sqlPedido);

        try {

            pstPedido = conn.prepareStatement(sqlPedido, new String[]{"K_PEDIDO"});
            pstPedido.execute();
            ResultSet generated = pstPedido.getGeneratedKeys();

            String sqlProducto = null;
            if (generated.next()) {
                int K_PEDIDO = generated.getInt(1);
                System.out.println(K_PEDIDO + " " + productos.length);
                for (int i = 0; i < productos.length; i++) {

                    sqlProducto = "INSERT INTO NATAME.ITEM VALUES(" + K_PEDIDO + "," + productos[i] + ", 1,1,2)";
                    System.out.println(sqlProducto);
                    pstProductos = conn.prepareStatement(sqlProducto);
                    pstProductos.execute();
                }
                pstProductos.close();
            } else {
                System.out.println("No entró al if");
            }
            pstPedido.close();
            return "Guardado correctamente.";

        } catch (SQLException e) {
            System.out.println(e);
            return e.toString();
        }
        //return "Respuesta desde DAO";
    }

}
