<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of enviarModel
 *
 * @author juan2ramos
 */
class enviarModel
{
    private $_campanias;
    public function __construct()
    {
        parent::__construct();
       
    }

    public function getCampanias(campaniasModel $campanias)
    {
        
        $this->_campanias = $campanias;
        $campanias = $this->_campanias->getCampanias();
        return $campanias;
    
    }
}

