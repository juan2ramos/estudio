<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of importarcontactos
 *
 * @author juan2ramos
 */
class importarcontactosController extends Controller {

    private $_importarcontactos;
    private $_sector;

    public function __construct() {
        parent::__construct(); //llamado al constructor padre el cual tiene como atributo  vista
        $this->_importarcontactos = $this->loadModel('importarcontactos');
        $this->_sector = $this->loadModel('sector');
    }

    public function index() {
        parent::menu();
        $this->_view->sectores = $this->_importarcontactos->getSectores($this->_sector);
        $this->_view->titulo = 'Importar Contactos';

        if ($this->getInt('guardar') == 1) {


            if (!$this->getTexto('nombre')) {
                $this->_view->_error = 'Debe introducir el nombre del contacto ';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'importarcontactos', $this->_menu);
                exit;
            }

            if (!$this->getTexto('apellido')) {
                $this->_view->_error = 'Debe introducir el apellido del contacto';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'importarcontactos', $this->_menu);
                exit;
            }
            if (!$this->validarEmail('email')) {
                $this->_view->_error = 'email no valido ';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'importarcontactos', $this->_menu);
                exit;
            }
            $sectores = $_POST['sectores'];

            if (empty($sectores)) {
                $this->_view->_error = 'debe ingresar al menos un Sector';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'importarcontactos', $this->_menu);
                exit;
            }
            if (session::get('level') == 'Administrador') {
                $id_usuario = session::get('id_usuario');
                $this->_importarcontactos->insertarContacto(
                        $this->getPostParam('nombre'), $this->getPostParam('apellido'), $this->getPostParam('email'), $id_usuario, $sectores
                );

                $this->_view->_error = 'Usuario ingresado Correctamente';
            }
        }

        $this->_view->renderizar('index', 'importarcontactos', $this->_menu);
    }

    public function multiples() 
    {
        parent::menu();
        $this->_view->titulo = 'Importar Contactos multiples';
        $this->_view->renderizar('multiples', 'importarcontactos', $this->_menu);
    }
    

}

