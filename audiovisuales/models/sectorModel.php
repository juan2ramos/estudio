<?php

class sectorModel extends Model
{

    //private $_dbc;

    public function __construct()
    {
        parent::__construct();
       
    }

    public function getSectores()
    {
        $i=0;
        $sql = 'SELECT * FROM  categorias';
        $post = $this->_dbc->ejecutar($sql);
        while ($x = $this->_dbc->obtener_fila($post,0)) {            
            $a[$i]= $x;
            $i++;
        }
         return $a;
    }

    public function getSector($id)
    {
        $id = (int) $id;
        $sql = 'SELECT * FROM categorias where id_categorias = '.$id;
        $post = $this->_dbc->ejecutar($sql);
        $x = $this->_dbc->obtener_fila($post,0)   ;          
            $a[0]= $x;
            
         return $x;
    }

    public function insertarSector($nombre, $descripcion)
    {
        $sql = ("INSERT INTO categorias (nombre,descripcion) VALUES ('$nombre','$descripcion')");
        $this->_dbc->ejecutar($sql);        
    }

    public function editarSector($id, $nombre, $descripcion)
    {
        $id = (int) $id;
        $sql = "UPDATE  categorias  SET  nombre = '$nombre',  descripcion = '$descripcion' WHERE ( id_categorias = $id)";
        $post = $this->_dbc->ejecutar($sql);      
    }

    public function eliminarSector($id)
    {
        $id = (int) $id;       
        $this->_dbc->ejecutar("DELETE FROM categorias WHERE id_categorias = $id");
    }

}

?>
