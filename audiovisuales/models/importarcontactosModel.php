<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of importarcontactosmodel
 *
 * @author juan2ramos
 */
class importarcontactosModel extends Model
{

    private $_sectores = array();

    public function __construct()
    {
        parent::__construct();
    }

    public function getSectores(sectorModel $sector)
    {
        $this->_sectores = $sector->getSectores();
        return $this->_sectores;
    }

    public function insertarContacto($nombre, $apellido, $email, $id_usuario, $sectores)
    {
        $sql = ("INSERT INTO contactos (nombre,apellido,email,fecha_ingreso,contacto_de_usuario) VALUES ('$nombre','$apellido','$email',now(),'$id_usuario')");
        $this->_dbc->ejecutar($sql);
        $this->insertarContatoSector($email, $sectores);
    }
    
    private function insertarContatoSector($email,$sectores)
    {
        $sql2 = "select id_contactos from contactos where email = '$email'";
        $post = $this->_dbc->ejecutar($sql2);
        $x = $this->_dbc->obtener_fila($post, 0);
        $idcontacto = $x['id_contactos'];
        for ($i = 0; $i < count($sectores); $i++) {
                      
            $sql3 = ("INSERT INTO categorias_contactos (id_categorias,id_contactos) VALUES ('$sectores[$i]','$idcontacto')");
            $this->_dbc->ejecutar($sql3);
            
        }
        
    }

}

