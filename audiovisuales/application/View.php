<?php

/*
 *
 * -------------------------------------
 * Descriptcon of View
 * Vista, manejador de las vistas
 * @author juan2ramos
 * -------------------------------------
 *
 */

class View
{

    private $_controlador;
    private $_js;
    private $_config;

    public function __construct(Request $peticion)
    {
        $this->_controlador = $peticion->getControlador();
        $this->_js = array();
        $this->_config = Config::singleton();
    }

    public function renderizar($vista, $menu = false)
    {

        /*
         * menu
         */


        if (!Session::get('autenticado')) {
            
        /*    $menu = array(
                
                array(
                    'id' => 'login',
                    'nombre' => 'Iniciar Sesi&oacute;n',
                    'enlace' => $this->_config->get('BaseUrl') . 'login'
                )
            );
            
            $menu = array(
                array(
                    'id' => 'inicio',
                    'titulo' => 'Inicio',
                    'enlace' => BaseUrl
                ),
                array(
                    'id' => 'post',
                    'titulo' => 'Post',
                    'enlace' => BaseUrl . 'post'
                ),
                array(
                    'id' => 'login',
                    'titulo' => 'Cerrar Sesion',
                    'enlace' => BaseUrl . 'index/cerrar'
                )
            );*/
        } 

        $js = array();

        if (count($this->_js)) {
            $js = $this->_js;
        }

        $_layoutParams = array(
            'ruta_css' => $this->_config->get('BaseUrl') . DS . 'views/layout/' . $this->_config->get('DefaultLayout') . '/css/',
            'ruta_img' => $this->_config->get('BaseUrl') . DS . 'views/layout/' . $this->_config->get('DefaultLayout') . '/img/',
            'ruta_js'  => $this->_config->get('BaseUrl') . DS . 'views/layout/' . $this->_config->get('DefaultLayout') . '/js/',
            'menu'     => $menu,
            'js'       => $js
        );

        $rutaView = ROOT . 'views' . DS . $this->_controlador . DS . $vista . '.phtml'; //ruta odne esta el archivo html
        /*
         * archivos con la configuracion html para visualizar el contenido   de la pagina 
         */
        if (is_readable($rutaView)) {
            include_once ROOT . 'views' . DS . 'layout' . DS . $this->_config->get('DefaultLayout') . DS . 'header.php';
            include_once $rutaView;
            include_once ROOT . 'views' . DS . 'layout' . DS . $this->_config->get('DefaultLayout') . DS . 'footer.php';
        } else {

            throw new Exception($rutaView);
        }
    }

    public function setJs(array $js)
    {
        if (is_array($js) && count($js)) {
            for ($i = 0; $i < count($js); $i++) {
                $this->_js[] = $this->_config->get('BaseUrl') . DS . 'views/' . $this->_controlador . '/js/' . $js[$i] . '.js';
            }
        } else {
            throw new Exception('Error de js');
        }
    }

}

?>
