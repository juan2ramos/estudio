<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of reportesController
 *
 * @author USUARIO
 */
class reportesController extends Controller{
    
    private $_reportes;

    public function __construct()
    {
        parent::__construct(); //llamado al constructor padre el cual tiene como atributo  vista
        $this->_campanias = $this->loadModel('reportes');
        
    }
    public function index()
    {
        parent::menu();
        $this->_view->titulo = 'Reporte';
        $this->_view->renderizar('index', 'reportes', $this->_menu);
    }
}

?>
