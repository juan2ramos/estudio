<?php

/*
 *
 * -------------------------------------
 * Description of Model
 * Modelo Principal
 * @author juan2ramos
* -------------------------------------
 * 
 */



abstract class Model
{
    protected $_dbc;


    public function __construct() 
    {
        $this->_dbc = Db::getInstance();
                       
    }
    
}

?>
