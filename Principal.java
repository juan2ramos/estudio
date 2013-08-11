/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snackfinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.border.EmptyBorder;
import org.w3c.dom.html.HTMLTextAreaElement;

/**
 *
 * @author juan2ramos
 */
public class Principal extends JFrame implements ActionListener, ItemListener {

    private JPanel contentPane;
    private Caja caja = new Caja();
    private Money money = new Money();
    private Producto producto = new Producto();
    private JButton button[] = new JButton[6];
    private JButton moneyB[] = new JButton[10];
    private boolean Position[] = new boolean[6];
    private JComboBox comboProducts;
    private JComboBox comboFree;
    private JComboBox comboProductsE;
    private JComboBox comboProductsR;
    private JComboBox comboProductsT;
    int posicion;
    JLabel areaReport = new JLabel();
    java.util.List<HashMap<String, Object>> listProductR = null;
    java.util.List<HashMap<String, Object>> listProductE;
    private JLabel nameText = new JLabel("Nombre :");
    private JLabel quantityText = new JLabel("Catidad :");
    private JLabel valText = new JLabel("Valor :");
    private JLabel positionText = new JLabel("Posicion :");
    private JLabel nameProductL = new JLabel("Producto :");
    private JLabel dateI = new JLabel("Fecha Inicial :");
    private JLabel dateF = new JLabel("Fecha Final :");
    JTextField nameProduct = new JTextField();
    JTextField quantityProduct = new JTextField();
    JTextField valProduct = new JTextField();
    JTextField fechai = new JTextField();
    JTextField fechaf = new JTextField();
    JButton insertar = new JButton();
    JButton reasignar = new JButton();
    JButton eliminar = new JButton();
    JButton cancelar = new JButton();
    JButton actualizar = new JButton();
    JLabel pantalla = new JLabel("Bienvenido!");
    JButton admin = new JButton();
    JButton buscar = new JButton();
    JButton back = new JButton();
    JButton newProduct = new JButton();
    JButton updateProduct = new JButton();
    JButton deleteProduct = new JButton();
    JButton againProduct = new JButton();
    JPanel contentPaneP = new JPanel();
    JButton report1 = new JButton();
    JButton report2 = new JButton();
    JButton report3 = new JButton();
    JPasswordField pass = new JPasswordField();
    JScrollPane scrollPane = new JScrollPane(areaReport);
    int saldo = 0;
    int id;

    public Principal() {

        setLayout(null);
        initComponet();

    }

    private void initComponet() {


        Dimension tk = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = (int) tk.getWidth() / 2 - 600;


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(ancho, 100, 1200, 500);
        contentPane = new JPanel();
        contentPaneP = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maquina de Snack", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Sans", 1, 16), java.awt.Color.orange)); // NOI18N

        setContentPane(contentPane);
        contentPane.setLayout(null);


        pantalla.setBounds(10, 0, 500, 250);
        Font fuente = new Font("Monospaced", Font.BOLD, 16);
        pantalla.setFont(fuente);
        contentPane.add(pantalla);


        comboProducts = new JComboBox();
        comboProducts.setBounds(620, 170, 150, 30);
        add(comboProducts);
        comboProducts.setVisible(false);

        comboProductsE = new JComboBox();
        comboProductsE.setBounds(780, 130, 150, 30);
        add(comboProductsE);
        comboProductsE.setVisible(false);
        comboProductsE.addItemListener(this);

        nameProductL.setBounds(10, 220, 80, 30);
        dateI.setBounds(200, 220, 100, 30);
        dateF.setBounds(420, 220, 100, 30);

        fechai.setBounds(310, 220, 100, 30);
        fechaf.setBounds(520, 220, 100, 30);

        add(nameProductL);
        add(fechai);
        add(dateI);
        add(fechaf);
        add(dateF);

        nameProductL.setVisible(false);
        fechai.setVisible(false);
        dateI.setVisible(false);
        fechaf.setVisible(false);
        dateF.setVisible(false);

        comboProductsT = new JComboBox();
        comboProductsT.setBounds(90, 220, 100, 30);
        add(comboProductsT);
        comboProductsT.setVisible(false);

        buscar.setText("Buscar");
        buscar.setBounds(760, 220, 120, 30);
        buscar.addActionListener(this);
        buscar.setActionCommand("buscar");
        add(buscar);
        buscar.setVisible(false);

        comboProductsR = new JComboBox();
        comboProductsR.setBounds(620, 130, 150, 30);
        add(comboProductsR);
        comboProductsR.setVisible(false);

        createProducts();
        try {
            createMoney();
        } catch (SQLException ex) {
        }

        scrollPane.setBounds(300, 30, 590, 180);
        add(scrollPane);
        scrollPane.setVisible(false);


        admin.setText("Admin");
        admin.setBounds(1065, 200, 120, 30);
        admin.addActionListener(this);
        admin.setActionCommand("admin");
        add(admin);


        newProduct.setText("Crear");
        newProduct.setBounds(500, 30, 150, 30);
        newProduct.addActionListener(this);
        newProduct.setVisible(true);
        newProduct.setActionCommand("new");
        add(newProduct);

        report1.setText("Reporte Dinero");
        report1.setBounds(500, 110, 150, 30);
        report1.addActionListener(this);
        report1.setVisible(true);
        report1.setActionCommand("report1");
        add(report1);

        report2.setText("Reporte Productos");
        report2.setBounds(660, 110, 150, 30);
        report2.addActionListener(this);
        report2.setVisible(true);
        report2.setActionCommand("report2");
        add(report2);

        report3.setText("Reporte Ventas");
        report3.setBounds(500, 150, 150, 30);
        report3.addActionListener(this);
        report3.setVisible(true);
        report3.setActionCommand("report3");
        add(report3);

        updateProduct.setText("Actualizar");
        updateProduct.setBounds(660, 30, 150, 30);
        updateProduct.addActionListener(this);
        updateProduct.setVisible(true);
        updateProduct.setActionCommand("update");
        add(updateProduct);


        againProduct.setText("Reasignar");
        againProduct.setBounds(660, 70, 150, 30);
        againProduct.addActionListener(this);
        againProduct.setVisible(true);
        againProduct.setActionCommand("again");
        add(againProduct);

        deleteProduct.setText("delete");
        deleteProduct.setBounds(500, 70, 150, 30);
        deleteProduct.addActionListener(this);
        deleteProduct.setVisible(true);
        deleteProduct.setActionCommand("delete");
        add(deleteProduct);

        newProduct.setVisible(false);
        updateProduct.setVisible(false);
        deleteProduct.setVisible(false);
        againProduct.setVisible(false);
        report1.setVisible(false);
        report2.setVisible(false);
        report3.setVisible(false);

        back.setText("Delvolucion dinero");
        back.setBounds(1000, 100, 180, 30);
        back.addActionListener(this);
        back.setActionCommand("back");
        add(back);

        pass.setBounds(895, 201, 155, 30);
        add(pass);
        pass.enable(false);

        //Form productos
        nameText.setBounds(540, 30, 150, 30);
        quantityText.setBounds(540, 80, 150, 30);
        valText.setBounds(540, 130, 150, 30);
        positionText.setBounds(540, 170, 150, 30);

        nameProduct.setBounds(620, 30, 150, 30);
        quantityProduct.setBounds(620, 80, 150, 30);
        valProduct.setBounds(620, 130, 150, 30);

        comboFree = new JComboBox();
        comboFree.setBounds(620, 170, 150, 30);
        comboFree.addItem("Ninguno");
        createComboFree();

        add(nameText);
        add(quantityText);
        add(valText);
        add(positionText);

        add(nameProduct);
        add(quantityProduct);
        add(valProduct);
        add(comboFree);

        visibleForm(false);

        eliminar.setText("Eliminar");
        eliminar.setBounds(500, 220, 120, 30);
        eliminar.addActionListener(this);
        eliminar.setActionCommand("eliminar");
        add(eliminar);
        eliminar.setVisible(false);

        insertar.setText("Insertar");
        insertar.setBounds(500, 220, 120, 30);
        insertar.addActionListener(this);
        insertar.setActionCommand("insertar");
        add(insertar);
        insertar.setVisible(false);

        reasignar.setText("Reasigar");
        reasignar.setBounds(500, 220, 120, 30);
        reasignar.addActionListener(this);
        reasignar.setActionCommand("reasignar");
        add(reasignar);
        reasignar.setVisible(false);

        actualizar.setText("actualizar");
        actualizar.setBounds(500, 220, 120, 30);
        actualizar.addActionListener(this);
        actualizar.setActionCommand("updateProduct");
        add(actualizar);
        actualizar.setVisible(false);


        cancelar.setText("Cancelar");
        cancelar.setBounds(630, 220, 120, 30);
        cancelar.addActionListener(this);
        cancelar.setActionCommand("cancelar");
        add(cancelar);
        cancelar.setVisible(false);





    }

    public void itemStateChanged(ItemEvent e) {


        int i = comboProductsE.getSelectedIndex();
        if (i >= 0) {
            id = (int) listProductE.get(i).get("id");
            String nombre = (String) listProductE.get(i).get("nombre");
            String valor = listProductE.get(i).get("valor").toString();
            String cantidad = listProductE.get(i).get("cantidad").toString();
            nameProduct.setText(nombre);
            valProduct.setText(valor);
            quantityProduct.setText(cantidad);
        }


    }

    private void createComboFree() {

        for (int i = 0; i < 6; i++) {
            if (Position[i] == false) {
                comboFree.addItem(i + 1);
            }
        }
    }

    private void createProducts() {
        int sizePro = producto.listaProductos.size();
        for (int i = 0; i < sizePro; i++) {
            Producto p = (Producto) producto.listaProductos.get(i);
            posicion = p.getPosicion();
            posicion--;
            Position[posicion] = true;
            button[i] = new JButton();
            button[i].setText(p.getNombre() + " " + p.getCantidad() + " " + p.getValor());
            button[i].setBounds(203 * posicion + 7, 380, 170, 50);
            add(button[i]);
            comboProducts.addItem(p.getNombre());
            button[i].setActionCommand("products");
            button[i].addActionListener(this);

        }


    }

    private void createMoney() throws SQLException {
        java.util.List<Integer> moneyList = money.getMoney();

        int sizemoney = moneyList.size();
        for (int i = 0; i < sizemoney; i++) {
            moneyB[i] = new JButton();
            String valorMoneda = Integer.toString(moneyList.get(i));
            moneyB[i].setText(valorMoneda);
            moneyB[i].setBounds(150 * i + 14, 280, 120, 50);
            add(moneyB[i]);
            moneyB[i].setActionCommand("money");
            moneyB[i].addActionListener(this);
        }

    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("report1".equals(e.getActionCommand())) {
            cancelar();
            String ret;
            ret = "<html>"
                    + "<table style = 'background-color: #FFFFFF ; width: 435px'><tr><td>"
                    + "<p style = 'font-size: 15px;font-weight: bold' >Dinero en caja :</p></br></br>";
            ret += getBalance();
            ret += "</tr></td></table></html>";

            areaReport.setText(ret);
            cancelar.setVisible(true);
            scrollPane.setVisible(true);

        }
        if ("report2".equals(e.getActionCommand())) {

            cancelar();
            String ret = "<html>"
                    + "<table border= '1'style = 'background-color: #FFFFFF ; width: 435px; "
                    + "border-width: 1px;"
                    + "padding: 1px;"
                    + "border-style: inset;"
                    + "border-color: gray;"
                    + "-moz-border-radius: ;'>"
                    + "<thead '>"
                    + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>ID</th>\n"
                    + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>NOMBRE</th>\n"
                    + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>VALOR</th>\n"
                    + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>CANTIDAD</th>\n"
                    + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>POSICION</th>"
                    + "</thead>"
                    + "<tbody>";
            try {
                listProductE = producto.fillProductosE();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < listProductE.size(); i++) {
                String ids = (String) listProductE.get(i).get("id").toString();
                String nombre = (String) listProductE.get(i).get("nombre").toString();
                String valor = (String) listProductE.get(i).get("valor").toString();
                String cantidad = (String) listProductE.get(i).get("cantidad").toString();
                String pos = (String) listProductE.get(i).get("posicion").toString();

                ret += "<tr><td>" + ids + "</td>\n"
                        + "<td>" + nombre + "</td>\n"
                        + "<td>" + valor + "</td>\n"
                        + "<td>" + cantidad + "</td>\n"
                        + "<td>" + pos + "</td> <tr>";
            }

            ret += "</tbody></table>";

            areaReport.setText(ret);
            cancelar.setVisible(true);
            scrollPane.setVisible(true);
        }

        if ("report3".equals(e.getActionCommand())) {
            cancelar();
            nameProductL.setVisible(true);
            fechai.setVisible(true);
            dateI.setVisible(true);
            fechaf.setVisible(true);
            dateF.setVisible(true);
            buscar.setVisible(true);
            comboProductsT.setVisible(true);
            cancelar.setVisible(true);
            try {
                listProductE = producto.fillProductosE();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < listProductE.size(); i++) {
                String nombre = (String) listProductE.get(i).get("nombre");
                comboProductsT.addItem(nombre);
            }

        }
        if ("buscar".equals(e.getActionCommand())) {

            String names = (String) comboProductsT.getSelectedItem();
            String fechaIn = fechai.getText();
            String fechaFin = fechaf.getText();
            if (fechaIn.equals("") || fechaFin.equals("")) {

                pantalla.setForeground(Color.red);
                pantalla.setText("Digita toda la informacion");
            } else {
                pantalla.setText("");
                int valTo = 0;
                String sum = null;
                String ret = "<html>"
                        + "<table border= '1'style = 'background-color: #FFFFFF ; width: 435px; "
                        + "border-width: 1px;"
                        + "padding: 1px;"
                        + "border-style: inset;"
                        + "border-color: gray;"
                        + "-moz-border-radius: ;'>"
                        + "<thead '>"
                        + "<th></th>"
                        + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>NOMBRE</th>\n"
                        + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>VALOR</th>\n"
                        + "<th style = 'background-color: #AAAAAA; color : #FFFFFF'>FECHA</th>\n"
                        + "</thead>"
                        + "<tbody>";
                try {
                    listProductE = producto.fillProductosBuy(fechaIn, fechaFin, names);
                    sum = producto.selectSum();
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < listProductE.size(); i++) {
                    String nombre = (String) listProductE.get(i).get("nombre").toString();
                    String valor = (String) listProductE.get(i).get("valor").toString();
                    String fecha = (String) listProductE.get(i).get("fecha").toString();
                    int valorint = Integer.parseInt(valor);
                    valTo += valorint;
                    ret += "<td></td>"
                            + "<td>" + nombre + "</td>\n"
                            + "<td>" + valor + "</td>\n"
                            + "<td>" + fecha + "</td> <tr>";
                }

                ret += "<tr><td>Ganancias <br>totales producto</td> "
                        + "<td colspan = 3>" + valTo + "</td><tr>"
                        + "<tr><td>Ganancias <br> totales productos</td> "
                        + "<td colspan = 3>" + sum + "</td><tr>";

                ret += "</tbody></table>";

                areaReport.setText(ret);
                scrollPane.setVisible(true);

            }
        }



        if ("updateProduct".equals(e.getActionCommand())) {
            String name = nameProduct.getText();
            String val = valProduct.getText();
            String quantity = quantityProduct.getText();
            try {
                producto.updateProduct(name, val, quantity, id);
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            int valN = Integer.parseInt(val);
            int quantityN = Integer.parseInt(quantity);
            int i = 0;
            String names = (String) comboProductsE.getSelectedItem();
            for (Iterator it = producto.listaProductos.iterator(); it.hasNext();) {
                Producto p = (Producto) it.next();

                String nombrePro = p.getNombre();
                if (nombrePro.equals(names)) {
                    p.setValorTot(valN);
                    p.setNombre(name);
                    p.setCantidadTot(quantityN);
                    button[i].setText(name + " " + quantity + " " + valN);
                    
                    
                    break;
                }
                i++;
            }
            cancelar();
            visibleButton(true);

        }
        if ("eliminar".equals(e.getActionCommand())) {
            int sizePro = producto.listaProductos.size();

            for (int i = 0; i < sizePro; i++) {
                Producto p = (Producto) producto.listaProductos.get(i);
                String nameProd = (String) comboProducts.getSelectedItem();
                if (p.getNombre().equals(nameProd)) {
                    producto.listaProductos.remove(i);
                    button[i].setVisible(false);
                    for (int j = i; j < sizePro - 1; j++) {
                        button[j] = button[j + 1];
                    }
                    pantalla.setText("Producto Eliminado");
                    comboProducts.removeItem(nameProd);
                    comboFree.addItem(i + 1);
                    producto.deleteProduct(nameProd);
                    break;
                }
            }
            cancelar();
            visibleButton(true);
        }

        if ("again".equals(e.getActionCommand())) {
            comboProductsE.setVisible(false);
            visibleButton(false);
            reasignar.setVisible(true);
            cancelar.setVisible(true);

            try {
                listProductR = producto.fillProductosP();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < listProductR.size(); i++) {

                String nombre = (String) listProductR.get(i).get("nombre");
                comboProductsR.addItem(nombre);
                comboProductsR.setVisible(true);
                comboFree.setVisible(true);

            }


        }

        if ("insertar".equals(e.getActionCommand())) {

            comboProductsE.setVisible(false);
            String name = nameProduct.getText();
            String val = valProduct.getText();
            String quantity = quantityProduct.getText();
            int pos = comboFree.getSelectedIndex();
            if (!isNumeric(val) || !isNumeric(quantity)) {
                pantalla.setText("No es numerico");
            } else {

                if (name.equals("") || val.equals("") || quantity.equals("")) {
                    pantalla.setForeground(Color.red);
                    pantalla.setText("Digita toda la informacion");
                } else {
                    pantalla.setText("");
                    int vals = Integer.parseInt(val);
                    int quantitys = Integer.parseInt(quantity);



                    if (pos != 0) {
                        String ids = null;
                        pos = (int) comboFree.getSelectedItem();
                        int sizePro = producto.listaProductos.size();
                        for (int i = sizePro - 1; i >= pos - 1; i--) {
                            button[i + 1] = button[i];

                        }
                        button[pos - 1] = new JButton();
                        button[pos - 1].setText(name + " " + quantity + " " + val);
                        button[pos - 1].setBounds(203 * (pos - 1) + 7, 380, 170, 50);
                        add(button[pos - 1]);
                        comboProducts.addItem(name);
                        button[pos - 1].setActionCommand("products");
                        button[pos - 1].addActionListener(this);
                        try {
                            producto.insertProduct(name, val, quantity, pos);
                            ids = producto.selectId(name);
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        int idsI = Integer.parseInt(ids);
                        producto.listaProductos.add(pos - 1, new Producto(idsI, name, vals, quantitys, pos));
                        comboFree.removeItem(pos);

                    } else {
                        try {
                            producto.insertProduct(name, val, quantity, 0);
                        } catch (SQLException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }


                    pantalla.setForeground(Color.BLACK);
                    pantalla.setText("Producto Ingresado");
                    visibleForm(false);
                    insertar.setVisible(false);
                    visibleButton(true);

                }
            }

        }

        if ("reasignar".equals(e.getActionCommand())) {
            String val = null;
            String quantity = null;
            int pos = comboFree.getSelectedIndex();
            int vals = 0;
            int quantitys = 0;

            String name = comboProductsR.getSelectedItem().toString();
            for (int i = 0; i < listProductR.size(); i++) {

                String namePro = listProductR.get(i).get("nombre").toString();
                if (namePro.equals(name)) {
                    val = listProductR.get(i).get("valor").toString();
                    vals = Integer.parseInt(val);
                    quantity = listProductR.get(i).get("cantidad").toString();
                    quantitys = Integer.parseInt(quantity);
                    break;
                }
            }

            if (pos != 0) {
                try {
                    pos = (int) comboFree.getSelectedItem();

                    int sizePro = producto.listaProductos.size();
                    int tam = sizePro - 1;
                    System.out.print(pos + " " + sizePro);
                    for (int i = tam; i >= pos - 1; i--) {
                        button[i + 1] = button[i];
                        System.out.print(i + " ");
                    }
                    button[pos - 1] = new JButton();
                    button[pos - 1].setText(name + " " + quantity + " " + val);
                    button[pos - 1].setBounds(203 * (pos - 1) + 7, 380, 170, 50);
                    add(button[pos - 1]);
                    comboProducts.addItem(name);
                    button[pos - 1].setActionCommand("products");
                    button[pos - 1].addActionListener(this);
                    String ids = producto.selectId(name);
                    int idsI = Integer.parseInt(ids);
                    producto.listaProductos.add(pos - 1, new Producto(idsI, name, vals, quantitys, pos));
                    comboFree.removeItem(pos);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                producto.updateProductR(name, pos);
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            pantalla.setForeground(Color.BLACK);
            pantalla.setText("Producto Reinsertado");
            visibleButton(true);
            insertar.setVisible(false);

            cancelar();
            visibleButton(true);

        }



        if ("new".equals(e.getActionCommand())) {
            visibleButton(false);
            newProductM();
            insertar.setVisible(true);

        }

        if ("update".equals(e.getActionCommand())) {
            visibleButton(false);
            comboProductsE.setVisible(true);
            try {
                listProductE = producto.fillProductosE();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < listProductE.size(); i++) {

                String nombre = (String) listProductE.get(i).get("nombre");
                comboProductsE.addItem(nombre);

            }

            visibleForm(true);
            comboProductsE.setVisible(true);
            actualizar.setVisible(true);


        }

        if ("delete".equals(e.getActionCommand())) {
            comboProducts.setVisible(true);
            visibleButton(false);
            eliminar.setVisible(true);
            cancelar.setVisible(true);

        }
        if ("cancelar".equals(e.getActionCommand())) {
            cancelar();
            visibleButton(true);
        }


        if ("back".equals(e.getActionCommand())) {
            if (saldo <= 0) {
                pantalla.setText("<html>No hay nada que devolver</html>");
            } else {
                String ret = "";
                ret += "<html>";
                HashMap hmSaldo = caja.getSaldo();
                ret += "Devolucion del dinero<br>";
                ret += ReturnSaldo(hmSaldo);
                ret += "</html>";

                saldo = 0;
                caja.UpdateCaja(hmSaldo);
                caja.setSaldo();


                pantalla.setText(ret);

            }
        }
        if ("admin".equals(e.getActionCommand())) {
            if (saldo > 0) {
                pantalla.setText("<html>Primero realice la transaccion<br>su saldo va en " + saldo + "</html>");
                return;
            }

            if (admin.getText().compareTo("Admin") == 0) {
                pantalla.setText("Ingrese su clave");
                admin.setText("listo");
                pass.enable(true);
                pass.requestFocus(true);

            } else if (admin.getText().compareTo("listo") == 0) {
                if (pass.getText().compareTo("pass") == 0) {
                    String ret = "<html>";
                    ret += getBalance();
                    ret += "<br></html>";
                    pantalla.setText(ret);
                    newProduct.setVisible(true);
                    admin.setText("Aceptar");
                    visibleButton(true);


                } else {
                    pantalla.setText("Error! contraseña invalida");
                    admin.setText("Admin");
                    pass.setText("");
                    pass.enable(false);
                    pass.requestFocus(false);
                }
            } else if (admin.getText().compareTo("Aceptar") == 0) {
                admin.setText("Admin");
                pantalla.setText("Bienvenido!");
                pass.setText("");
                pass.enable(false);
                pass.requestFocus(false);
                cancelar();
            }
        }

        if ("money".equals(e.getActionCommand())) {

            JButton boton = (JButton) e.getSource();
            int c = Integer.parseInt(boton.getText());

            if (admin.getText().compareTo("Aceptar") == 0) {
                String ret = "<html>";
                caja.addMoney(c, 1);
                ret += getBalance();
                ret += "</html>";
                pantalla.setText(ret);
            } else if (admin.getText().compareTo("listo") != 0) {

                caja.addMoney(c, 1);
                caja.addSaldo(c, 1);
                saldo += c;
                pantalla.setText("Gracias, su saldo va en " + saldo);
            }
        }

        if ("products".equals(e.getActionCommand())) {

            JButton tempB = (JButton) e.getSource();
            int sizePro = producto.listaProductos.size();
            int lB = locationPorduct(tempB, sizePro);
            Producto p = (Producto) producto.listaProductos.get(lB);
            int cant = p.getCantidad();
            if (admin.getText().compareTo("Aceptar") == 0) {
                String cadena1 = changeProduct(p, true);
                tempB.setText(cadena1);

            } else {
                if (cant > 0) {


                    int val = p.getValor();
                    boolean buy = Buy(val);
                    if (buy) {

                        String cadena = changeProduct(p, false);
                        tempB.setText(cadena);

                    }
                } else {
                    pantalla.setText("No hay existencia del producto");
                }
            }
        }

    }

    private boolean Buy(int val) {

        if (val > saldo) {
            int num = val - saldo;
            pantalla.setText("<html>El producto tiene un valor de " + val + " <br>aun te faltan " + num + "</html>");
            return false;
        } else if (val < saldo) {

            HashMap hm = caja.MakeReturn(saldo - val);

            if (hm != null) {

                int total = 0;
                String ret = "", ret1 = "";
                java.util.List sortedKeys = new ArrayList(hm.keySet());
                Collections.sort(sortedKeys);
                ret += "<html>";

                for (int i = 0; i < sortedKeys.size(); i++) {
                    ret1 += "Monedas de " + (Integer) sortedKeys.get(i) + ": " + hm.get(sortedKeys.get(i)) + "<br>";
                    total += (Integer) hm.get(sortedKeys.get(i)) * (Integer) sortedKeys.get(i);
                }
                ret += "Estas son sus vueltas <br>";
                ret += ret1;
                ret += " Total de vueltas: " + total + "<br>Gracias por su compra!!</html>";
                pantalla.setText(ret);
                saldo = 0;
                caja.setSaldo();
                return true;

            } else {
                HashMap hmSaldo = caja.getSaldo();
                String ret = ReturnSaldo(hmSaldo);
                pantalla.setText("</html>No se puede gestionar la compra,<br> dinero insuficiente en caja pra dar vueltas<br>" + ret + "</html>");
                return false;
            }
        } else {
            pantalla.setText("Gracias por su compra");
            caja.setSaldo();
            saldo = 0;
            return true;
        }

    }

    private String ReturnSaldo(HashMap hmSaldo) {

        String ret = "";

        java.util.List sortedKeys = new ArrayList(hmSaldo.keySet());
        Collections.sort(sortedKeys);

        for (int i = 0; i < sortedKeys.size(); i++) {
            ret += "Monedas de " + (Integer) sortedKeys.get(i) + ": " + hmSaldo.get(sortedKeys.get(i)) + "<br>";

        }

        return ret;

    }

    private String changeProduct(Producto p, boolean operation) {

        String cadena = " ";
        int cantidad = p.getCantidad();
        if (operation) {
            cantidad++;
        } else {
            cantidad--;
            int idPro = p.getId();
            int val = p.getValor();
            producto.insertBuy(idPro, val);
        }
        String nombre = p.getNombre();
        String valor = Integer.toString(p.getValor());
        p.setCantidad(cantidad);
        cadena = nombre + " " + Integer.toString(cantidad) + " " + valor;
        return cadena;

    }

    private int locationPorduct(JButton temp, int sizePro) {

        int j = 0;
        boolean band = true;
        while (j < sizePro && band == true) {


            if (button[j] == temp) {
                band = false;

            }
            j++;

        }
        j--;
        return j;

    }

    private String getBalance() {

        HashMap hmCaja = caja.getCaja();
        String ret = "";
        int total = 0;

        java.util.List sortedKeys = new ArrayList(hmCaja.keySet());
        Collections.sort(sortedKeys);
        for (int i = 0; i < sortedKeys.size(); i++) {
            if ((Integer) hmCaja.get(sortedKeys.get(i)) > 0) {
                ret += "Monedas de " + (Integer) sortedKeys.get(i) + ": " + hmCaja.get(sortedKeys.get(i)) + "<br>";
            }
            total += (Integer) hmCaja.get(sortedKeys.get(i)) * (Integer) sortedKeys.get(i);
        }


        ret += ("Total en caja: " + total + "<br>");
        return ret;
    }

    private void visibleButton(boolean visible) {
        newProduct.setVisible(visible);
        updateProduct.setVisible(visible);
        deleteProduct.setVisible(visible);
        againProduct.setVisible(visible);
        report1.setVisible(visible);
        report2.setVisible(visible);
        report3.setVisible(visible);
    }

    private void visibleForm(boolean visible) {

        nameText.setVisible(visible);
        quantityText.setVisible(visible);
        valText.setVisible(visible);

        nameProduct.setVisible(visible);
        quantityProduct.setVisible(visible);
        valProduct.setVisible(visible);
        cancelar.setVisible(visible);
        comboFree.setVisible(visible);
        positionText.setVisible(visible);

        nameProduct.setText("");
        quantityProduct.setText("");
        valProduct.setText("");
    }

    private void newProductM() {

        visibleForm(true);

    }

    private void cancelar() {
        visibleForm(false);
        visibleButton(false);
        insertar.setVisible(false);
        eliminar.setVisible(false);
        comboProducts.setVisible(false);
        actualizar.setVisible(false);
        comboProductsE.setVisible(false);
        againProduct.setVisible(false);
        reasignar.setVisible(false);
        comboProductsR.setVisible(false);
        scrollPane.setVisible(false);
        nameProductL.setVisible(false);
        fechai.setVisible(false);
        dateI.setVisible(false);
        fechaf.setVisible(false);
        dateF.setVisible(false);
        buscar.setVisible(false);
        comboProductsT.setVisible(false);
        comboProductsE.removeAllItems();
        comboProductsR.removeAllItems();
        comboProductsT.removeAllItems();

    }
}
