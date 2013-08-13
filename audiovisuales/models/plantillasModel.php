<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of plantillaModel
 *
 * @author juan2ramos
 */
class plantillasModel extends Model
{

    public function __construct()
    {
        parent::__construct();
    }

    public function getPlantillas()
    {
        $i = 0;
        $sql = 'SELECT
                    plantillas.id_plantillas,
                    plantillas.nombre AS plantilla,
                    plantillas.descripcion,
                    tipo_plantillas.nombre AS tipo_plantilla
                    FROM
                    plantillas
                    LEFT Join tipo_plantillas ON tipo_plantillas.id_tipo_plantillas = plantillas.id_tipo_plantillas';
        $post = $this->_dbc->ejecutar($sql);
        while ($x = $this->_dbc->obtener_fila($post, 0)) {
            $a[$i] = $x;
            $i++;
        }
        return $a;
    }

    public function getTipoPlantillas()
    {
        $i = 0;
        $sql = 'SELECT * FROM tipo_plantillas';
        $post = $this->_dbc->ejecutar($sql);
        while ($x = $this->_dbc->obtener_fila($post, 0)) {
            $a[$i] = $x;
            $i++;
        }
        return $a;
    }

    public function getPlantilla($id)
    {
        $sql = ("select * from plantillas where id_plantillas = $id ");
        $post = $this->_dbc->ejecutar($sql);
        $x = $this->_dbc->obtener_fila($post, 0);
        return $x;
    }

    public function obtenerPlantillas($nombre)
    {
        $sql = ("select * from plantillas where nombre = '$nombre'");
        $post = $this->_dbc->ejecutar($sql);
        $numero = $this->_dbc->obtenerNumeroConsulta($post);

        if ($numero == 0)
            return false;
        return true;
    }

    public function actualizarPlantillas($id, $nombre, $descripcion, $id_tipo_plantillas,$destino)
    {
        $sql = ("UPDATE plantillas SET 
                nombre = '$nombre', 
                descripcion = '$descripcion',
                id_tipo_plantillas = '$id_tipo_plantillas',
                url = '$destino',
                WHERE (id_plantillas = $id) LIMIT 1 ");
        $this->_dbc->ejecutar($sql);
    }

    public function insertarPlantillas($nombre, $descripcion, $id_tipo_plantillas,$destino)
    {
        $sql = ("insert into plantillas
            (nombre, descripcion, id_tipo_plantillas,url) values
            ('$nombre', '$descripcion', '$id_tipo_plantillas', '$destino')");
        $this->_dbc->ejecutar($sql);
    }

    public function eliminarPlantilla($id)
    {
        $sql = ("delete from plantillas where id_plantillas = $id");
        $this->_dbc->ejecutar($sql);
    }

}

