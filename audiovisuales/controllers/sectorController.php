<?php

class sectorController extends Controller
{
    private $_sector;
    
    public function __construct() {
        parent::__construct();
        $this->_sector = $this->loadModel('sector');
    }
    
    public function index()
    {
        parent::menu();
        $this->_view->sector = $this->_sector->getSectores();
        $this->_view->titulo = 'Sector';
        $this->_view->renderizar('index', 'sector',$this->_menu);
    }
    
    public function crear()
    {
        //Session::accesoEstricto(array('usuario'));
        $this->_view->usuario = Session::get('usuario');
        parent::menu();
        $this->_view->titulo = 'Sector';
        $this->_view->setJs(array('nuevo'));
        
        if($this->getInt('guardar') == 1){
            $this->_view->datos = $_POST;
            
            if(!$this->getTexto('nombre')){
                $this->_view->_error = 'Debe introducir el nombre del sector';
                $this->_view->renderizar('nuevo', 'sector',$this->_menu);
                exit;
            }
            
            if(!$this->getTexto('descripcion')){
                $this->_view->_error = 'Debe introducir la descripcion del sector';
                $this->_view->renderizar('nuevo', 'sector',$this->_menu);
                exit;
            }
            
            $this->_sector->insertarSector(
                    $this->getPostParam('nombre'),
                    $this->getPostParam('descripcion')
                    );
            
            $this->redireccionar('sector');
        }       
        
        $this->_view->renderizar('nuevo', 'sector',$this->_menu);
    }
    
    public function editar($id = false)
    {
        parent::menu();
        if(!$this->filtrarInt($id)){            
            $this->redireccionar('sector');
        }
        
        if(!$this->_sector->getSector($id)){            
            $this->redireccionar('sector');
        }
        
        $this->_view->titulo = 'Editar Sectores';
        $this->_view->setJs(array('nuevo'));
        
        if($this->getInt('guardar') == 1){
            $this->_view->datos = $_POST;
            
            if(!$this->getTexto('nombre')){
                $this->_view->_error = 'Debe introducir el nombre del post';
                $this->_view->renderizar('editar', 'sector');
                exit;
            }
            
            if(!$this->getTexto('descripcion')){
                $this->_view->_error = 'Debe introducir el descripcion  del post';
                $this->_view->renderizar('editar', 'sector');
                exit;
            }
            
            $this->_sector->editarSector(
                    $this->filtrarInt($id),
                    $this->getPostParam('nombre'),
                    $this->getPostParam('descripcion')
                    );
            
            $this->redireccionar('sector');
        }
        
        $this->_view->datos = $this->_sector->getSector($this->filtrarInt($id));
        $this->_view->renderizar('editar', 'sector',  $this->_menu);
    }
    
    public function eliminar($id = false)
    {
        parent::menu();
        
        if(!$this->filtrarInt($id)){
            $this->redireccionar('sector');
        }
        
        if(!$this->_sector->getSector($this->filtrarInt($id))){
            $this->redireccionar('sector');
        }
        
        $this->_sector->eliminarSector($this->filtrarInt($id));
        $this->redireccionar('sector');
    }
}

?>
