<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of UsuarioFactory
 *
 * @author juan2ramos
 */
class UsuarioFactoryModel
{

    CONST ADMINISTRADOR = "Administrador";
    CONST CLIENTE = "Cliente";

    public  function crearUsuario($tipoUsuario)
    {
        $rutaModelo = ROOT . 'models' . DS . $tipoUsuario . '.php';

        if (is_readable($rutaModelo)) {
            require_once $rutaModelo;
        } else {
            //throw new Exception(Session::get('tipoUsuarios'));
            print_r($_SESSION);
        }
        switch ($tipoUsuario) {
            case self::ADMINISTRADOR:
                return new Administrador();
                break;
            case self::CLIENTE:
                return new Cliente();
                break;
        }
        die("Error en usuario.");
    }

}

