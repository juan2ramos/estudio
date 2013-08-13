<?php

class MenuModelo extends Model {

    private $_menuFinal;
    private $_item;
    private $_acl;
    protected $_config;

    public function __construct($item, $acl) {

        parent::__construct();
        $this->_acl = $acl;
        $this->_item  = $item;
        $this->_config = Config::singleton();
        $this->menu();   
        
        
    }
    public function getMenu(){
        if ($this->_menuFinal){
            return $this->_menuFinal;
        }
       
    }

    private function menu() {


        $menu = array(
            'items' => array(),
            'parents' => array()
        );
        $tabla = "menu";
        $datos = array("id_menu", "nombre", "`key`", "url", "id_padre",);
        $otros = "ORDER BY id_padre, orden, nombre";

        $sql = new sql($tabla, $datos, NULL, $otros);
        $sql1 = $sql->select();
        $ejecutar = $this->_dbc->ejecutar($sql1);
        $menui = $this->_dbc->obtener_fila($ejecutar, 1);
        foreach ($menui as $items) {
            
            $menu['items'][$items['id_menu']] = $items;
            $menu['parents'][$items['id_padre']][] = $items['id_menu'];
            
        }
        $this->_menuFinal = $this->construirMenu(0, $menu);
        
    }

    private function construirMenu($padre, $menu) {
        $html = "";
        if (isset($menu['parents'][$padre])) {
            $html .= "<ul";
            if ($padre == 0) {               
                $html .= ' class="nav " ';
            }
            $html .= ">\n";
            foreach ($menu['parents'][$padre] as $itemId) {
                
               
                if (!isset($menu['parents'][$itemId])) {
                    if($menu['items'][$itemId]['nombre'] == $this->_item ){
                        $_item_style = 'current';                        
                    }else{$_item_style = ' ';}
                    
                    if ($this->_acl->permiso($menu['items'][$itemId]['key'])){
                         $html .= "<li>\n <a href='" .$this->_config->get('BaseUrl'). $menu['items'][$itemId]['url'] . "' class ='".$_item_style."' >" . $menu['items'][$itemId]['nombre'] . "</a>\n</li> \n";
                    }
                }
                if (isset($menu['parents'][$itemId])) {
                    if($menu['items'][$itemId]['nombre'] == $this->_item ){
                        $_item_style = 'current';                        
                    }else{$_item_style = ' ';}
                        if ($this->_acl->permiso($menu['items'][$itemId]['key'])){
                        $html .= "<li>\n  <a href='#'  class ='".$_item_style."' >" . $menu['items'][$itemId]['nombre'] . "</a> \n";
                        $html .= $this->construirMenu($itemId, $menu);
                        $html .= "</li> \n";
                    }
                }
            }
            $html .= "</ul> \n";
        }
      
            
        return $html;
    }

}
