<?php

class ACL {

    private $_id;
    private $_role;
    private $_permisos;
    private $_dbc;

    public function __construct($id = false) {
        if ($id) {
            $this->_id = (int) $id;
        } else {
            if (Session::get('id_usuario')) {
                $this->_id = Session::get('id_usuario');
            } else {
                $this->_id = 0;
            }
        }
        $this->_dbc = Db::getInstance();
        $this->_role = $this->getRole();
        $this->_permisos = $this->getPermisosRole();
        $this->compilarAcl();
       
    }

    public function compilarAcl() {
        $this->_permisos = array_merge(
                  $this->_permisos ,$this->getPermisosUsuario()
        );
        //echo '<pre>';
        //print_r($this->_permisos);
        //exit;
    }

    public function getRole() {
        $tabla = "usuarios";
        $datos = array("id_rol");
        $where = "id_usuario = '$this->_id'";

        $sql = new sql($tabla, $datos, $where);
        $sql1 = $sql->select();
        $ejecutar = $this->_dbc->ejecutar($sql1);
        $role = $this->_dbc->obtener_fila($ejecutar, 0);

        return $role['id_rol'];
    }

    public function getPermisosRoleId() {
        $tabla = "permisos_role";
        $datos = array("permiso");
        $where = "rol = '$this->_role'";

        $sql = new sql($tabla, $datos, $where);
        $sql1 = $sql->select();

        $ejecutar = $this->_dbc->ejecutar($sql1);
        $ids = $this->_dbc->obtener_fila($ejecutar, 1);
        

         

        $id = array();

        for ($i = 0; $i < count($ids); $i++) {
            $id[] = $ids[$i]['permiso'];
        }

        return $id;
    }

    public function getPermisosRole() {
        $tabla = "permisos_role";
        $where = "rol = '$this->_role'";

        $sql = new sql($tabla, NULL, $where);
        $sql1 = $sql->select();
        
        $ejecutar = $this->_dbc->ejecutar($sql1);
        $permisos = $this->_dbc->obtener_fila($ejecutar, 1);

        $data = array();

        for ($i = 0; $i < count($permisos); $i++) {
            $key = $this->getPermisoKey($permisos[$i]['permiso']);
            if ($key == '') {
                continue;
            }

            if ($permisos[$i]['valor'] == 1) {
                $v = true;
            } else {
                $v = false;
            }

            $data[$key] = array(
                'key' => $key,
                'permiso' => $this->getPermisoNombre($permisos[$i]['permiso']),
                'valor' => $v,
                'heredado' => true,
                'id' => $permisos[$i]['permiso']
            );
        }

        return $data;
    }

    public function getPermisoKey($permisoID) {
        $permisoID = (int) $permisoID;
        $tabla = "permisos";
        $datos = array("`key`");
        $where = "id_permiso = '$permisoID'";

        $sql = new sql($tabla, $datos, $where);
        $sql1 = $sql->select();

        $ejecutar = $this->_dbc->ejecutar($sql1);
        $key = $this->_dbc->obtener_fila($ejecutar, 0);

        return $key['key'];
    }

    public function getPermisoNombre($permisoID) {
        $permisoID = (int) $permisoID;
        $tabla = "permisos";
        $datos = array("`permiso`");
        $where = "id_permiso = '$permisoID'";

        $sql = new sql($tabla, $datos, $where);
        $sql1 = $sql->select();

        $ejecutar = $this->_dbc->ejecutar($sql1);
        $key = $this->_dbc->obtener_fila($ejecutar, 0);

        return $key['permiso'];
    }

    public function getPermisosUsuario() {
        $ids = $this->getPermisosRoleId();


        if (count($ids)) {

            $tabla = "permisos_usuario";
            $where = "usuario = '$this->_id' and permiso in (" . implode(",", $ids) . ")";

            $sql = new sql($tabla, NULL, $where);
            $sql1 = $sql->select();
            $ejecutar = $this->_dbc->ejecutar($sql1);
            $permisos = $this->_dbc->obtener_fila($ejecutar, 1);
            if (!$permisos){
                $permisos = array();
            }
        } else {
            $permisos = array();
        }
        $data = array();

        for ($i = 0; $i < count($permisos); $i++) {
            $key = $this->getPermisoKey($permisos[$i]['permiso']);
            if ($key == '') {
                continue;
            }

            if ($permisos[$i]['valor'] == 1) {
                $v = true;
            } else {
                $v = false;
            }

            $data[$key] = array(
                'key' => $key,
                'permiso' => $this->getPermisoNombre($permisos[$i]['permiso']),
                'valor' => $v,
                'heredado' => false,
                'id' => $permisos[$i]['permiso']
            );
        }

        return $data;
    }

    public function getPermisos() {
        
        if (isset($this->_permisos) && count($this->_permisos))
            return $this->_permisos;
    }

    public function permiso($key) {
        if (array_key_exists($key, $this->_permisos)) {
            if ($this->_permisos[$key]['valor'] == true || $this->_permisos[$key]['valor'] == 1) {
                return true;
            }
        }

        return false;
    }

    public function acceso($key) {
        if ($this->permiso($key)) {
            return;
        }

        header("location:" . BASE_URL . "error/access/5050");
    }

}

?>
