<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of plantillas
 *
 * @author juan2ramos
 */
class plantillasController extends Controller
{

    private $_plantillas;

    public function __construct()
    {
        parent::__construct();
        $this->_plantillas = $this->loadModel('plantillas');
    }

    public function index()
    {
        parent::menu();
        $this->_view->titulo = 'Plantillas';
        $this->_view->plantillas = $this->_plantillas->getPlantillas();
        $this->_view->renderizar('index', 'plantillas ', $this->_menu);
        ;
    }

    public function ingresar($id = false)
    {
        parent::menu();
        $this->_view->titulo = 'Plantillas';
        $this->_view->datostipo = $this->_plantillas->getTipoPlantillas();
        $nombre_archivo = $HTTP_POST_FILES['archivo']['name'];
        print_r($HTTP_POST_FILES) ;
        move_uploaded_file($HTTP_POST_FILES['archivo']['tmp_name'],BASE_URL . 'public/plantillas' . $nombre_archivo); 
          
    
        if ($this->getInt('guardar') == 1) {

            $this->validaciones();
            //$nombre_archivo = $HTTP_POST_FILES['archivo']['name'];
            //move_uploaded_file($HTTP_POST_FILES['archivo']['tmp_name'], $nombre_archivo);

            if (!$id) :

                $numeroplantillas = $this->_plantillas->obtenerPlantillas($this->getTexto('nombre'));

                if ($numeroplantillas):
                    $this->_view->_error = 'esta plantilla  ya existe ';
                    $this->_view->datos = $_POST;
                    $this->_view->renderizar('actualizar', 'plantillas ', $this->_menu);
                    exit();
                endif;
                $this->_plantillas->insertarPlantillas($this->getPostParam('nombre'), $this->getPostParam('descripcion'), $this->getPostParam('id_tipo_plantillas'), $destino);

            else:

                $this->_plantillas->actualizarPlantillas($id, $this->getPostParam('nombre'), $this->getPostParam('descripcion'), $this->getPostParam('id_tipo_plantillas'), $destino);
            endif;

            $this->redireccionar('plantillas ');
        }
        if ($id)
            $this->_view->datos = $this->_plantillas->getPlantilla($id);

        $this->_view->renderizar('actualizar', 'plantillas', $this->_menu);
    }

    public function eliminar($id = false)
    {
        $this->_plantillas->eliminarPlantilla($id);
        $this->redireccionar('plantillas');
    }

    private function validaciones()
    {


        if (!$this->getTexto('nombre')) {
            $this->_view->_error = 'Debe introducir el nombre  ';
            $this->_view->datos = $_POST;

            $this->_view->renderizar('actualizar', 'plantillas', $this->_menu);
            exit;
        }

        if (!$this->getTexto('descripcion')) {
            $this->_view->_error = 'Debe introducir la descripcion  ';
            $this->_view->datos = $_POST;
            $this->_view->renderizar('actualizar', 'plantillas', $this->_menu);
            exit;
        }
        if (!$this->getSelect('id_tipo_plantillas')) {
            $this->_view->_error = 'Debe introducir un tipo de usuario ';
            $this->_view->datos = $_POST;
            $this->_view->renderizar('actualizar', 'usuario', $this->_menu);
            exit;
        }
    }

}

