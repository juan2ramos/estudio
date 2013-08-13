<?php

class postModel extends Model
{

    

    public function __construct()
    {
        parent::__construct();
    }

    public function getPosts()
    {
        $i=0;
        $sql = 'SELECT * FROM tipo_usuarios';
        $post = $this->_dbc->ejecutar($sql);
        while ($x = $this->_dbc->obtener_fila($post,0)) {            
            $a[$i]= $x;
            $i++;
        }
         return $a;
    }

    public function getPost($id)
    {
        $id = (int) $id;
        $sql = 'SELECT * FROM tipo_usuarios where id_tipo_usuarios = '.$id;
        $post = $this->_dbc->ejecutar($sql);
        $x = $this->_dbc->obtener_fila($post,0)   ;          
            $a[0]= $x;
            
         return $x;
    }

    public function insertarPost($titulo, $cuerpo)
    {
        $this->_db->prepare("INSERT INTO posts VALUES (null, :titulo, :cuerpo)")
                ->execute(
                        array(
                            ':titulo' => $titulo,
                            ':cuerpo' => $cuerpo
                ));
    }

    public function editarPost($id, $nombre, $descripcion)
    {
        $id = (int) $id;
        $sql = "UPDATE  tipo_usuarios  SET  nombre = '$nombre',  descripcion = '$descripcion' WHERE ( id_tipo_usuarios = $id)";
        $post = $this->_dbc->ejecutar($sql);
       // $x = $this->_dbc->obtener_fila($post,0)   ;
    }

    public function eliminarPost($id)
    {
        $id = (int) $id;
        $this->_db->query("DELETE FROM posts WHERE id = $id");
    }

}

?>
