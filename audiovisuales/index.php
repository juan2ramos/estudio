<?php

/*
 * -------------------------------------
 * Description of index
 * desde aca de ejecuatara todo la aplicación
 * @author juan2ramos
 * -------------------------------------
 *
 */
date_default_timezone_set("America/Bogota");

ini_set('display_errors', 1);


/*
 *
 * Definimos la constante con rura del directorio principal
 *
 */

define('DS', DIRECTORY_SEPARATOR);//Separador dependiendo del sistema operativo
define('ROOT', realpath(dirname(__FILE__)) . DS);//Ruta real del archivo
define('APP_PATH', ROOT . 'application' . DS);//APP_PATH = ruta donde se encuentra los archivos principales de la aplcación

/*
 *
 * incluimos archivos  principales
 *
 */
try{

    require_once APP_PATH . 'Config.php';
    require_once            'config.php';
    require_once APP_PATH . 'Request.php';
    require_once APP_PATH . 'Bootstrap.php';
    require_once APP_PATH . 'Controller.php';
    require_once APP_PATH . 'Model.php';
    require_once APP_PATH . 'View.php';
    require_once APP_PATH . 'Registro.php';
    require_once APP_PATH . 'Database.php';
    require_once APP_PATH . 'Session.php';
    require_once APP_PATH . 'Hash.php';
    require_once APP_PATH . 'Acl.php';
    require_once APP_PATH . 'sql.php';


    Session::init();
    $config = Config::singleton();
    Bootstrap::ejecutar(new Request, $config);
}
catch(Exception $e){
    echo $e->getMessage();
}
