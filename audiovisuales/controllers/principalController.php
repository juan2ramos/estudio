<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of principalController
 *
 * @author juan2ramos
 */
class principalController extends Controller
    {

    public function __construct() {
        parent::__construct();#llamado al constructor padre el cual tiene como  atributo vista.
        $this->_item = 'Inicio';
    }


    public function index(){
       
        $this->_view->titulo = 'Inicio';
        $this->_view->usuario = Session::get('usuario');
        parent::menu();
        $this->_view->renderizar('index',$this->_menu);
    }
}

