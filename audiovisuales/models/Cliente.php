<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Administrador
 *
 * @author juan2ramos
 */
$rutaModelo = ROOT . 'models' . DS . 'Usuario' . '.php';

if (is_readable($rutaModelo)) {
    require_once $rutaModelo;
} else {
    throw new Exception($rutaModelo);
}

class Cliente extends Usuario
{

    //private $_menu;
    
    

    public function __construct()
    {
        
        parent::__construct();
        $this->usuario = 2;
        
    }

    public function crearMenu()
    {
        parent::crearMenu();
        return $this->menu;
    }

}

