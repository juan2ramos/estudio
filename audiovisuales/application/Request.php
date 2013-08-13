<?php

/*
 *
 * -------------------------------------
 * Description of Request
 * ejecuta y maneja las URL
 * @author juan2ramos
 * -------------------------------------
 *
 */


class Request
{
    private $_controlador;
    private $_metodo;
    private $_argumentos;

    public function __construct() {

        $config = Config::singleton();

        if(isset($_GET['url'])){
            $url = filter_input(INPUT_GET, 'url', FILTER_SANITIZE_URL);//Filtramos la url para q no tenga ningun caracter extraño
            $url = explode('/', $url);//convertimos en un array
            $url = array_filter($url);//flilto a la url

            $this->_controlador = strtolower(array_shift($url));//Añadimos el controlador asociandolo con el primer elemnto del arreglo
            $this->_metodo = strtolower(array_shift($url));//Añadimos el metodo asociandolo con el primer elemnto del arreglo que quedo
            $this->_argumentos = $url;// los demas elemntos son los argumetos
        }

        if(!$this->_controlador){
            $this->_controlador = $config->get('DefaultControllers');// controlador por defecto
        }

        if(!$this->_metodo){
            $this->_metodo = 'index';// metodo por defecto
        }

        if(!isset($this->_argumentos)){
            $this->_argumentos = array();// argumentos por defecto
        }
    }


    /*
     *
     * Metodos get
     *
     */

    public function getControlador()
    {
        return $this->_controlador;
    }

    public function getMetodo()
    {
        return $this->_metodo;
    }

    public function getArgs()
    {
        return $this->_argumentos;
    }
}

