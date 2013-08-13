<?php
/*
 *
 * -------------------------------------
 * Descripción Config
 * Crea todas las constantes de configuración utilizando patrón singleton
 * @autor juan2ramos
 * -------------------------------------
 *
 */

class Config
{
    private $_conts;
    private static $instance;

    private function __construct()
    {
        $this->_conts = array();
    }

    //Agregar constante al arreglo conts.
    public function set($name, $value)
    {
        if(!isset($this->_conts[$name]))
        {
            $this->_conts[$name] = $value;
        }
    }

    //Recuperar la constante.
    public function get($name)
    {
        if(isset($this->_conts[$name]))
        {
            return $this->_conts[$name];
        }
    }

    #Patrón Singleton
        public static function singleton()
    {
        if (!isset(self::$instance)) {
            $c = __CLASS__;
            self::$instance = new $c;
        }

        return self::$instance;
    }
}

