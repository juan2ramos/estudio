/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snackfinal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan2ramos
 */
public final class Producto {

    private int id;
    private String NombreProducto;
    private int ValorProducto;
    private int CantidadProducto;
    private int posicionProducto;
    public ArrayList listaProductos = new ArrayList();
    private generateSql pSql = new generateSql();

    Producto() {
        try {
            fillProductos();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Producto(int ids, String nombre, int valor, int cantidad, int posicion) {

        NombreProducto = nombre;
        ValorProducto = valor;
        CantidadProducto = cantidad;
        posicionProducto = posicion;
        id = ids;


    }

    int getId() {
        return id;

    }

    int getPosicion() {
        return posicionProducto;

    }

    void setCantidad(int cantidad) {
        String query = "update productos set cantidad = " + cantidad + " where nombre = '" + NombreProducto + "';";
        
        CantidadProducto = cantidad;
        pSql.update(query);
    }

    void setCantidadTot(int cantidad) {
        CantidadProducto = cantidad;
    }

    int getCantidad() {
        return CantidadProducto;
    }

    void setValor(int cantidad) {
        ValorProducto += cantidad;
    }

    void setValorTot(int cantidad) {
        ValorProducto = cantidad;
    }

    int getValor() {
        return ValorProducto;
    }

    void setNombre(String Nombre) {
        NombreProducto = Nombre;
    }

    String getNombre() {
        return NombreProducto;
    }

    void insertProduct(String name, String val, String quantity, int position) throws SQLException {

        pSql.update("insert into productos (nombre,valor,cantidad,posicion) values ('" + name + "'," + val + "," + quantity + "," + position + ");");
    }

    void deleteProduct(String nombre) {
        pSql.update("UPDATE `productos` SET `posicion`='0' WHERE (`nombre`='" + nombre + "')");
    }

    @SuppressWarnings("empty-statement")
    private void fillProductos() throws SQLException {


        List<HashMap<String, Object>> lista = pSql.selectSql("select * from productos where posicion <> 0 order by posicion");
        for (int i = 0; i < lista.size(); i++) {
            int ids = (int) lista.get(i).get("id");
            String nombre = (String) lista.get(i).get("nombre");
            int valor = (int) lista.get(i).get("valor");
            int cantidad = (int) lista.get(i).get("cantidad");
            int posicion = (int) lista.get(i).get("posicion");
            listaProductos.add(new Producto(ids, nombre, valor, cantidad, posicion));

        }
    }

    public List<HashMap<String, Object>> fillProductosE() throws SQLException {


        List<HashMap<String, Object>> listProductE = pSql.selectSql("select * from productos  ");
        /*for (int i = 0; i < listProductE.size(); i++) {
           
           
         String nombre = (String) listProductE.get(i).get("nombre");
         int valor = (int) listProductE.get(i).get("valor");
         int cantidad = (int) listProductE.get(i).get("cantidad");
            
         }*/
        return listProductE;
    }

    public List<HashMap<String, Object>> fillProductosP() throws SQLException {


        List<HashMap<String, Object>> listProductp = pSql.selectSql("select * from productos where posicion = 0 ");

        return listProductp;
    }

    public void updateProduct(String nombre, String valor, String cantidad, int id) throws SQLException {
        String sql = "UPDATE productos SET nombre = '" + nombre + "', valor ='" + valor + "', cantidad = '" + cantidad + "' WHERE (id ='" + id + "') ;";
        pSql.update(sql);
    }

    public void updateProductR(String nombre, int id) throws SQLException {
        String sql;
        sql = "UPDATE productos SET posicion = '" + id + "' WHERE (nombre ='" + nombre + "') ;";
        pSql.update(sql);
    }

    public void insertBuy(int ids, int val) {
        String sql;
        sql = ("insert into compras (idproducto,valor) values (" + ids + "," + val + ");");
        pSql.update(sql);
    }

    String selectId(String nombre) throws SQLException {
        List<HashMap<String, Object>> listProductE = pSql.selectSql("select id from productos  where nombre = '" + nombre + "'  ;");
        String h = listProductE.get(0).get("id").toString();
        return  h;
    }

    public List<HashMap<String, Object>> fillProductosBuy(String fechaI,String fechaF,String nombre) throws SQLException {

        String sql = "SELECT "
                + " productos.nombre, "
                + " compras.valor,"
                + " compras.fecha "
                + " FROM "
                +  "compras "
                + " INNER JOIN productos ON compras.idproducto = productos.id "
                + " WHERE "
                + " fecha BETWEEN '"+fechaI+" 00:00:00 ' and '"+fechaF+" 23:59:59' and productos.nombre = '"+nombre+"'";
        List<HashMap<String, Object>> listProductp = pSql.selectSql(sql);

        return listProductp;
    }
    
    String selectSum() throws SQLException {
        String sql = "SELECT SUM(valor) as total FROM compras ";
        List<HashMap<String, Object>> listProductE = pSql.selectSql(sql);
        String h = listProductE.get(0).get("total").toString();
        return  h;
    }
    
}
