<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of campaniaController
 *
 * @author juan2ramos
 */
class campaniaController extends Controller
{

    private $_campanias;
    private $_sector;
    private $_ofertas;
    private $_plantillas;
    private $_usuarios;

    public function __construct()
    {
        parent::__construct(); //llamado al constructor padre el cual tiene como atributo  vista
        $this->_campanias = $this->loadModel('campanias');
        $this->_ofertas = $this->loadClass('Ofertas');
        $this->_sector = $this->loadModel('sector');
        $this->_plantillas = $this->loadModel('plantillas');
        $this->_usuarios = $this->loadModel('usuario');
    }
    public function enviar()
    {
        parent::menu();
        $this->_view->titulo = 'Enviar campañas';
        $this->_view->campanias = $this->_campanias->getCampanias();
        $this->_view->renderizar('enviar', 'campania', $this->_menu);
    }
    public function pagar($id)
    {        
        $this->_campanias->pagarCampanias($id);
        $this->enviar();
    }
    public function envioCampania($id)
    {        
        $this->_campanias->envioCampania($id,$this->_usuarios);
        $this->enviar();
    }
    public function campaniaRecibida($usuario ,$campania)
    {
        $this->_campanias->campaniaRecibida($usuario,$campania);
    }

    public function index()
    {
        parent::menu();
        $this->_view->sectores = $this->_campanias->getSectores($this->_sector);
        $this->_view->sector = $this->_sector->getSectores();
        $this->_view->ofertas = $this->_campanias->getOfertas($this->_ofertas);
        
        $this->_view->plantillas = $this->_campanias->getPlantillas($this->_plantillas);
        $this->_view->titulo = 'Campañas';


        if ($this->getInt('guardar') == 1) {
           

            if (!$this->getTexto('nombre')) {
                $this->_view->_error = 'Debe introducir el nombre de la campaña';
                $this->_view->renderizar('index', 'campania', $this->_menu);
                exit;
            }

            if (!$this->getTexto('descripcion')) {
                $this->_view->_error = 'Debe introducir la descripcion de la campaña';
                $this->_view->renderizar('index', 'campania', $this->_menu);
                exit;
            }
            if (!$this->getSelect('oferta')) {
                $this->_view->_error = 'Debe introducir una oferta ';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'campania', $this->_menu);
                exit;
            }
            if (!$this->getTexto('titulo')) {
                $this->_view->_error = 'Debe introducir un titulo ';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'campania', $this->_menu);
                exit;
            }
            if (!$this->getTexto('plantilla')) {
                $this->_view->_error = 'Debe introducir una plantilla ';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'campania', $this->_menu);
                exit;
            }
            if (!$this->getTexto('mensaje')) {
                $this->_view->_error = 'Debe introducir un mensaje ';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'campania', $this->_menu);
                exit;
            }
            if (!$this->getTexto('pie')) {
                $this->_view->_error = 'Debe introducir un pie ';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'campania', $this->_menu);
                exit;
            }
            $sectores = $_POST['sectores'];

            if (empty($sectores)) {
                $this->_view->_error = 'debe ingresar al menos un Sector';
                $this->_view->datos = $_POST;
                $this->_view->renderizar('index', 'importarcontactos', $this->_menu);
                exit;
            }
            $this->_campanias->insertarCampanias(
                    $this->getPostParam('nombre'), $this->getPostParam('descripcion'), $this->getPostParam('oferta'), $this->getPostParam('plantilla'), $this->getPostParam('titulo'), $this->getPostParam('mensaje'), $this->getPostParam('pie'), $sectores 
            );

            $this->_view->_error = 'Campaña Agregada ';
            
            $this->_view->renderizar('index', 'importarcontactos', $this->_menu);
        }

        $this->_view->renderizar('index', 'campania', $this->_menu);
    }

}

