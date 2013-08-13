<?php

/*
 *
 * -------------------------------------
 * Descripción de Bootstrap
 * LLama a los controladores y métodos
 * @autor juan2ramos
 * -------------------------------------
 *
 */


class Bootstrap
{
    public static function ejecutar(Request $peticion, Config $config)
    {
        $controller = $peticion->getControlador() . 'Controller';//Obtiene el controlador
        $rutaControlador = ROOT . 'controllers' . DS . $controller . '.php';//ruta física de controlador
        $metodo = $peticion->getMetodo();//Obtiene el método
        $args = $peticion->getArgs();//Obtiene los argumentos

        if(is_readable($rutaControlador)){// busca si el archivo existe y si es se puede leer
            require_once $rutaControlador;
            $controller = new $controller();

            if(is_callable(array($controller, $metodo))){// revisa si el método es valido
                $metodo = $peticion->getMetodo();
            }
            else{
                $metodo = 'index';
            }

            if(isset($args)){//revisa si los argumentos existen
                call_user_func_array(array($controller, $metodo), $args);//llamamos al metodo de la clase y le pasamos los argumentos
            }
            else{
                call_user_func(array($controller, $metodo));//llama la clase y el metodo
            }

        } else {            
            header('location:' . $config->get('BaseUrl') . '/404.shtml');
            exit;
            //throw new Exception('no encontrado');//lanzamos la excepcion en el caso de no encontrar el metodo

        }
        
    }
}

?>