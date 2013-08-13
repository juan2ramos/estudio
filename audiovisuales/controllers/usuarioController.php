<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of usuariosController
 *
 * @author juan2ramos
 */
class usuarioController extends Controller {

    private $_usuario;

    public function __construct() {
        parent::__construct();
        $this->_usuario = $this->loadModel('usuario');
        $this->_item = 'usuarios';
        $key = 'CrearUsuario';
        $this->autentificar($key);
        parent::menu();
        $this->_view->titulo = 'Usuarios';
    }

    public function index() {
        
    }

    private function rolUsuario() {
        $datosrol = $this->_usuario->getRoles();
        $this->_view->datosrol = $datosrol;
    }

    public function crear() {



        $this->rolUsuario();

        if ($this->getInt('enviar') == 1) {

            $this->validaciones('index');

            if ($this->_usuario->insertarUsuario($_POST, $this->_config)) {
                $this->_view->_error = 'El usuario fue ingresado correctamente ';
                $this->_view->renderizar('index', $this->_menu);
                exit;
            } else {
                $this->error('Este usuario ya se encuentra registrado ', 'index');
            }
        }

        $this->_view->renderizar('index', $this->_menu);
    }

    public function consultar() {

        if ($this->getInt('enviar') == 1) {

            if (!$this->getTexto('cedula'))
                $this->error('Debe introducir el numero de cedula  ', 'consultar');
            $usuario = $this->_usuario->buscarUsuarios($_POST['cedula']);
           

            if (!$usuario) {
                $this->error('el usuario con la cedula ' . $_POST['cedula'] . ' no se encuentra registrado', 'consultar');
            }
        }

        if ($this->getInt('enviar') == 2) {
            $usuario = $this->_usuario->buscarUsuarios($_POST['cedula']);
            $this->_view->usuario = $usuario;
            $this->validaciones('consultar');
            if($this->_usuario->actualizarUsuario($_POST,$this->_config)){
                $this->_view->_error = 'El usuario fue actualizado correctamente ';
                unset($this->_view->usuario);
                $this->_view->renderizar('consultar', $this->_menu);
                exit;
            }
            
        }
        $this->rolUsuario();
        if (isset($usuario))
            $this->_view->usuario = $usuario;
        $this->_view->renderizar('consultar', $this->_menu);
    }

    private function error($msj, $pagina) {
        $this->_view->_error = $msj;
        $this->_view->datos = $_POST;
        $this->_view->renderizar($pagina, $this->_menu);
        exit;
    }



    private function validaciones($pagina) {

        if (!$this->getTexto('cedula'))
            $this->error('Debe introducir el numero de cedula  ', $pagina);

        if (!$this->getTexto('nombre'))
            $this->error('Debe introducir el nombre  ', $pagina);


        if (!$this->getTexto('apellido')) {
            $this->error('Debe introducir el apellido  ', $pagina);
        }
        if (!$this->getTexto('fecha_nacimiento'))
            $this->error('Debe introducir el fecha nacimiento  ', $pagina);


        if (!$this->getTexto('email'))
            $this->error('Debe introducir el email  ', $pagina);


        if (!$this->getTexto('contrasenia'))
            $this->error('Debe introducir el contrase&ntilde;a  ', $pagina);

        if (!$this->getTexto('nombreusuario'))
            $this->error('Debe introducir el nombre de usuario  ', $pagina);

        if (!$this->getTexto('id_rol'))
            $this->error('Debe introducir el tipo de usuario  ', $pagina);
    }

}

