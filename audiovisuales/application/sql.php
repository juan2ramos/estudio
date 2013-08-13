<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of sql
 *
 * @author USUARIO
 * 
 */
class sql {
    
    private $_tabla;
    private $_datos;
    private $_where;
    private $_otros;
        
    function __construct($tabla, $datos = false, $where = false, $otros = NULL) {
        $this->_tabla = $tabla;
        $this->_datos = $datos;
        if($where)
            $this->_where = $where;
        else
            $this->_where = NULL;
        if($datos)
            $this->_datos = $datos;
        else 
            $this->_datos = NULL;
        if($datos)
            $this->_otros = $otros;
        else 
            $this->_otros = NULL;
            
        
        
    }
    
    function select(){
        
        $query = "select ";
        if($this->_datos){
            $query .= implode(",", $this->_datos);
        }
        else {
            $query .= "* ";
        }
        
        $query .= " from ". $this->_tabla;
        if ($this->_where){
            $query .= " where ".$this->_where;  
        }
        if ($this->_otros){
            $query .= " ".$this->_otros;  
        }
        $query .= " ;";
        
        return $query;

    }
    function insert($post){
        
        array_shift($post);
        $keys = array_keys($post);
        $query = " insert into ";
        $query .= $this->_tabla;
        $query .= " (";
        $query .= implode(", ", $keys);
        $query .= ")";
        $query .= " values ('" ;
        $query .= implode("', '", $post);
        $query .= "') ;";
        
        return $query;
        
    }
    function update($post){
        array_shift($post);
        $id = array_shift($post);
        $query = " update ";
        $query .= $this->_tabla;
        $query .= " set ";
        $ultimo = end( $post );
        foreach ($post as $clave => $valor){
            if($ultimo == $valor)
                $query .= " $clave = '$valor' ";
            else 
                $query .= " $clave = '$valor' ,";           
            
        }       
        
        $query .= " where id_usuario = $id ;";
        return $query;
        
    }
    
    function delete(){  
        ;
    }
    
    
}


