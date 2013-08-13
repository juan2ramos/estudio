<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of enviarController
 *
 * @author juan2ramos
 */
class enviarController extends Controller
{
    private $_enviar;
    private $_campanias;

    public function __construct()
    {
        parent::__construct(); //llamado al constructor padre el cual tiene como atributo  vista
        $this->_enviar = $this->loadModel('enviar');
        $this->_campanias = $this->loadClass('campanias');
    }

    public function index()
    {
        parent::menu();
        $this->_view->titulo = 'Enviar campañas';
        $this->_view->campanias = $this->_enviar->getCampanias($this->_campanias);
    }
}

