<?php

/*
 *
 * -------------------------------------
 * Descripción de indexController
 * Controlador inicial
 * @autor juan2ramos
 * -------------------------------------
 *
 */

class indexController extends Controller
{

    public function __construct() {
        parent::__construct();#llamado al constructor padre el cual tiene como  atributo vista.
        $this->_item = 'Iniciar Sesi&oacte;n';
    }

    public function index()
    {
       
        $this->_view->titulo = 'Audiovisuales';
        parent::menu();
        $this->_view->renderizar('index',$this->_menu);

    }
     public function cerrar()
    {
        Session::destroy();
        $this->redireccionar();
    }

}

?>