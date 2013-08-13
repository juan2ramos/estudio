<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Ofertas
 *
 * @author juan2ramos
 */
class Ofertas extends Model
{

    public function __construct()
    {
        parent::__construct();
       
    }

    public function getOfertas()
    {
        $i=0;
        $sql = ("select * from ofertas ");
        $post = $this->_dbc->ejecutar($sql);
        while ($x = $this->_dbc->obtener_fila($post, 0)) {
            $a[$i] = $x;
            $i++;
        }
        return $a;
    }
    public function  cantidadOferta($oferta)
    {
        $sql = ("select * from ofertas where id_ofertas = $oferta ");
        $post = $this->_dbc->ejecutar($sql);
        $x = $this->_dbc->obtener_fila($post, 0);
        return $x['cantidad'];
    }

}

