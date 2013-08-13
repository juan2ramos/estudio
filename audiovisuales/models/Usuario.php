<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Usuario
 *
 * @author juan2ramos
 */
class Usuario extends Model
{

    protected $usuario;
    protected $menu = array();

    public function __construct()
    {
        parent::__construct();
    }

    public function crearMenu()
    {
        $sql = "SELECT
                menu.id_menu as id
                FROM
                menu
                Inner Join submenu ON menu.id_menu = submenu.id_menu
                Inner Join submenu_tipo_usuario ON submenu_tipo_usuario.id_submenu = submenu.id_submenu
                Inner Join tipo_usuarios ON submenu_tipo_usuario.id_tipo_usuarios = tipo_usuarios.id_tipo_usuarios
                WHERE
                tipo_usuarios.id_tipo_usuarios = $this->usuario 
                GROUP BY
                menu.nombre
                ORDER BY
                menu.id_menu ASC";
        $sql1 = " SELECT
                    submenu.nombre  AS submenu,
                    menu.nombre AS menu,
                    submenu.id_submenu,
                    menu.id_menu ,
                    menu.Titulo
                    FROM submenu_tipo_usuario
                  Inner Join submenu ON submenu.id_submenu = submenu_tipo_usuario.id_submenu
                  Inner Join tipo_usuarios ON tipo_usuarios.id_tipo_usuarios = submenu_tipo_usuario.id_tipo_usuarios
                  Inner Join menu ON menu.id_menu = submenu.id_menu
                  WHERE   tipo_usuarios.id_tipo_usuarios =  $this->usuario order by id_menu
         ";
        $post = $this->_dbc->ejecutar($sql);
        //$numero = $this->_dbc->obtenerNumeroConsulta($post);
        $post1 = $this->_dbc->ejecutar($sql1);
        $i = 0;
        $j = 0;
        $k = 0;

        while ($result = $this->_dbc->obtener_fila($post, 0)) :
            $vector[$i] = $result['id'];
            $i++;
        endwhile;
        $tamaño = count($vector);

        while ($result = $this->_dbc->obtener_fila($post1, 0)) :
            $vector1[$j]['submenu'] = $result['submenu'];
            $vector1[$j]['menu'] = $result['menu'];
            $vector1[$j]['id_submenu'] = $result['id_submenu'];
            $vector1[$j]['id_menu'] = $result['id_menu'];
            $vector1[$j]['Titulo'] = $result['Titulo'];
            $j++;
        endwhile;
        $tamaño1 = count($vector1);


        for ($i = 0; $i < $tamaño; $i++):
            for ($j = 0; $j < $tamaño1; $j++):
                if ($vector[$i] == $vector1[$j]['id_menu']) :

                    $menu[$i][$j] = array(
                        'submenu' => $vector1[$j]['submenu'],
                        'menu' => $vector1[$j]['menu'],
                        'titulo' => $vector1[$j]['menu'] . ' ' . $vector1[$j]['submenu'],
                        'enlace' => BASE_URL . $vector1[$j]['menu'] . '/' . $vector1[$j]['submenu']
                    );

                endif;

            endfor;
        endfor;

        

        $this->menu = $menu;
       
    }

}

