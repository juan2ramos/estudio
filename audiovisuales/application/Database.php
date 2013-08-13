<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

class Db {

    private $_servidor;
    private $_usuario;
    private $_password;
    private $_baseDatos;
    private $link;
    private $stmt;
    private $array;
    private $numero;
    private $config;
    static $_instance;

    /* La función construct es privada para evitar que el objeto pueda ser creado mediante new */

    private function __construct() {
        $this->config = Config::singleton();
        $this->setConexion();
        $this->conectar();
    }

    /* Método para establecer los parámetros de la conexión */

    private function setConexion() {
        $this->_servidor = $this->config->get('DBHost');
        $this->_baseDatos = $this->config->get('DBName');
        $this->_usuario = $this->config->get('DBUser');
        $this->_password = $this->config->get('DBPass');
    }

    /* Evitamos el clonaje del objeto. Patrón Singleton */

    private function __clone() {
        
    }

    /* Función encargada de crear, si es necesario, el objeto. Esta es la función que debemos llamar desde fuera de la clase para instanciar el objeto, y así, poder utilizar sus métodos */

    public static function getInstance() {
        if (!(self::$_instance instanceof self)) {
            self::$_instance = new self();
        }
        return self::$_instance;
    }

    /* Realiza la conexión a la base de datos. */

    private function conectar() {
        //$this->link = mysql_connect($this->_servidor, $this->_usuario, $this->_password);
        $this->link = mysqli_connect($this->_servidor, $this->_usuario, $this->_password, $this->_baseDatos);
        //mysql_select_db($this->_baseDatos, $this->link);
        //@mysql_query("SET NAMES 'utf8'");
     
    }

    /* Método para ejecutar una sentencia sql */

    public function ejecutar($sql) {
        if ($this->stmt = mysqli_query($this->link, "select id_usuario from usuarios")) {
            print_r('ssds');exit;
            return $this->stmt;
            
        }
        print_r('ssd');exit;
    }

    public function up($sql) {
        mysql_query($sql, $this->link);
    }

    /* Método para obtener una fila de resultados de la sentencia sql */

    public function obtener_fila($stmt, $fila) {
        if ($fila == 0) {
            $this->array = mysqli_fetch_assoc($stmt);
            return $this->array;
            //$this->array = mysql_fetch_assoc($stmt);
        } else {
            
            while ($row = mysqli_fetch_array($stmt, MYSQLI_ASSOC)) {
               $rows[] = $row;
               
            }
            if (empty($rows))
                return FALSE;
            return $rows;
        }
        if ($fila == 2)
        mysqli_num_rows($stmt);
        
    }

    public function obtenerNumeroConsulta($stmt) {
        $this->numero = mysql_num_rows($stmt);
        return $this->numero;
    }

//Devuelve el último id del insert introducido
    public function lastID() {
        return mysql_insert_id($this->link);
    }

}

