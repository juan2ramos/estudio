<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of usuarioModel
 *
 * @author juan2ramos
 */
class usuarioModel extends Model
{

    public function __construct()
    {
        parent::__construct();
    }

    public function getRoles()
    {
        $tabla = "roles";               
        $sql = new sql($tabla,NULL, NULL);
        $sql1= $sql->select();
        $ejecutar = $this->_dbc->ejecutar($sql1);
        $roles = $this->_dbc->obtener_fila($ejecutar,1);
        return $roles;
        
       
    }
    private function verificarUsuario($cedula,$email,$nombreusuario){
        $tabla = "usuarios"; 
        $where = "cedula = '$cedula' or email = '$email' or  nombreusuario = '$nombreusuario'";        
        $sql = new sql($tabla,NULL, $where);
        $sql1= $sql->select();
        $ejecutar = $this->_dbc->ejecutar($sql1);
        $usuario = $this->_dbc->obtener_fila($ejecutar,2); 
        if ($usuario > 0)
            return FALSE;
        return TRUE;
        
    }

    public function insertarUsuario($post, $config)
    {
        if(!$this->verificarUsuario($post['cedula'],$post['email'],$post['nombreusuario']))
                return FALSE;
        $tabla = "usuarios";               
        $sql = new sql($tabla);
        $hashKey = $config->get('HASH_KEY');
        $post['contrasenia'] = Hash::getHash('sha1', $post['contrasenia'], $hashKey);
        $post['fecha_ingreso'] = date("d/m/y H:i:s");        
        $sql1= $sql->insert($post);
        if ($this->_dbc->ejecutar($sql1))
            return true;
        return FALSE;
    }

    public function buscarUsuarios($cedula)
    {
        $tabla = "usuarios";
        $where = " cedula =  '$cedula'";
        $sql = new sql($tabla, NULL, $where);
        $sql1= $sql->select();
        $ejecutar = $this->_dbc->ejecutar($sql1);
        $usuario = $this->_dbc->obtener_fila($ejecutar,1);
      
        if ($usuario){
            return $usuario;
        }
        return FALSE;
    }
    
    public function actualizarUsuario($post,$config)            
    {
       
        $tabla = "usuarios";               
        $sql = new sql($tabla);
        $hashKey = $config->get('HASH_KEY');
        $post['contrasenia'] = Hash::getHash('sha1', $post['contrasenia'], $hashKey);
        $post['fecha_ingreso'] = date("d/m/y H:i:s");        
        $sql1= $sql->update($post);
        if ($this->_dbc->ejecutar($sql1))
            return true;
        return FALSE;
    }
    

}

