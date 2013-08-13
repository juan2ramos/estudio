<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of campaniasModel
 *
 * @author juan2ramos
 */
class campaniasModel extends Model
{

    private $_ofertas;

    public function __construct()
    {
        parent::__construct();
    }

    private function phpmailer($class)
    {
        $rutaClass = ROOT . 'libs' . DS . 'phpmailer' . DS . 'class.' . $class . '.php';

        if (is_readable($rutaClass)) {
            require_once $rutaClass;
        } else {
            throw new Exception($rutaClass);
        }
    }

    public function getSectores(sectorModel $sector)
    {
        $this->_sectores = $sector->getSectores();
        return $this->_sectores;
    }

    public function getOfertas(Ofertas $ofertas)
    {
        $this->_ofertas = $ofertas;
        $ofertas = $this->_ofertas->getOfertas();
        return $ofertas;
    }

    public function getPlantillas(plantillasModel $plantillas)
    {
        $plantillas = $plantillas->getPlantillas();
        return $plantillas;
    }

    public function insertarCampanias($nombre, $descripcion, $oferta, $plantilla, $titulo, $contenido, $pie, $sectores)
    {
        $id_usuario = session::get('id_usuario');
        $sql = "INSERT INTO `campanias` (`nombre`, `descrpcion`,`fecha_creacion`, `id_usuarios`, `pago_realizado`, `id_ofertas`, `id_plantillas`,`titulo`,`contenido`,`pie`) VALUES ('$nombre', '$descripcion',now(), '$id_usuario', '0', '$oferta', '$plantilla','$titulo','$contenido','$pie')";
        $this->_dbc->ejecutar($sql);
        $this->insertarCategoriaCampania($nombre, $sectores, $oferta);
    }

    private function insertarCategoriaCampania($nombre, $sectores, $oferta)
    {
        $sql2 = "select id_campania from campanias where nombre = '$nombre'";
        $post = $this->_dbc->ejecutar($sql2);
        $x = $this->_dbc->obtener_fila($post, 0);
        $idcampania = $x['id_campania'];
        for ($i = 0; $i < count($sectores); $i++) {

            $sql3 = ("INSERT INTO campanias_categorias (id_categoria,id_campania) VALUES ('$sectores[$i]','$idcampania')");
            $this->_dbc->ejecutar($sql3);
        }
        $this->insertarContatoCampania($idcampania, $oferta);
    }

    private function insertarContatoCampania($idcampania, $oferta)
    {
        $i = 0;
        $sql = "SELECT
                    contactos.id_contactos,
                    contactos.nombre,
                    contactos.email,
                    categorias.id_categorias
                    FROM
                    contactos
                    Inner Join categorias_contactos ON contactos.id_contactos = categorias_contactos.id_contactos
                    Inner Join categorias ON categorias.id_categorias = categorias_contactos.id_categorias
                    WHERE categorias.id_categorias IN
                    (SELECT
                    categorias.id_categorias
                    FROM
                    campanias
                    Inner Join campanias_categorias ON campanias.id_campania = campanias_categorias.id_campania
                    Inner Join categorias ON categorias.id_categorias = campanias_categorias.id_categoria
                    WHERE
                    campanias.id_campania =  '$idcampania')
                    GROUP BY contactos.id_contactos            

               ";
        $post = $this->_dbc->ejecutar($sql);

        $numeroOferta = $this->_ofertas->cantidadOferta($oferta);

        while ($x = $this->_dbc->obtener_fila($post, 0)) {
            $a[$i] = $x;
            $i++;
        }
        $numeroContactos = count($a);
        //echo 'sdsd '.$numeroOferta.'  '.$numeroContactos.'</br> ';

        $num = Array();
        reset($num);
        for ($i = 1; $i <= $numeroOferta; $i++) {
            $num[$i] = rand(1, $numeroContactos);
            if ($i > 1) {
                for ($x = 1; $x < $i; $x++) {
                    if ($num[$i] == $num[$x]) {
                        $i--;
                        break;
                    }
                }
            }
        }
        echo $numeroContactos;

        for ($i = 0; $i < $numeroOferta; $i++) {
            
            $j = $i+1;
            $contacto = $a[$j]['id_contactos'];
            echo '$idcampania ' . $idcampania . 'id_contactos' .  $contacto . ' </br>';
            $sql2 = ("INSERT INTO contactos_campania (id_campania,id_contactos) VALUES ('$idcampania','$contacto')");
            $this->_dbc->ejecutar($sql2);
        }
    }

    public function getCampanias()
    {
        $i = 0;
        $usuario = session::get('id_usuario');
        $sql = ("SELECT
                    campanias.id_campania,
                    campanias.nombre,
                    campanias.descrpcion,
                    campanias.fecha_creacion,
                    campanias.pago_realizado
                    FROM
                    campanias
                    Inner Join usuarios ON campanias.id_usuarios = usuarios.id_usuarios
                    WHERE
                    usuarios.id_usuarios =  '$usuario' ");
        $post = $this->_dbc->ejecutar($sql);
        while ($x = $this->_dbc->obtener_fila($post, 0)) {
            $a[$i] = $x;
            $i++;
        }
        return $a;
    }

    public function pagarCampanias($id)
    {
        $sql = "UPDATE `campanias` SET `pago_realizado`='1' WHERE (`id_campania`='$id') LIMIT 1";
        $this->_dbc->ejecutar($sql);
    }

    public function envioCampania($id, usuarioModel $usuario)
    {
        $idUsuario = session::get('id_usuario'); 
        $contactosCampania = $this->contactosCampania($id);
        $usuarioCampania = $usuario->getUsuario($idUsuario); 
        print_r($contactosCampania);
        $this->envioMail($id, $contactosCampania, $usuarioCampania);
    }

    private function envioMail($id, $contactosCampania, $usuarioCampania)
    {
        $this->phpmailer('phpmailer');
        $this->phpmailer('smtp');
        $mail = new PHPMailer();
        $mail->IsSendmail();
        //$mail->FromName =$contactosCampania[0][''] ;
        
        $mail->From = $usuarioCampania['email'];
        echo $usuarioCampania['email'];
        $mail->Subject = $contactosCampania[0]['descrpcion'];
        $mail->MsgHTML('Mensaje con HTML');
        $template = file_get_contents($contactosCampania[0]['url'], 'r');
        $display = str_replace("%%titulo%%", $contactosCampania[0]['titulo'], $template);
        $display = str_replace("%%contenido%%", $contactosCampania[0]['contenido'], $display);
        $display = str_replace("%%pie%%", $contactosCampania[0]['pie'], $display);
        $display.= "<img src='http://juan2ramos.com/campaniasemail/public/img/img.php?contacto=" . $contactosCampania[0]['id_contactos'] . "&campania=" . $id . "' alt=''>";
        $mail->Body = $display;

        for ($i = 0; $i < count($contactosCampania); $i++) {

            $email = strtolower($contactosCampania[$i]['email']);
            $mail->AddAddress($email, $contactosCampania[$i]['nombre_contactos']);

            if (!$mail->Send()) {
                echo $mail->ErrorInfo;
            } else {
                $this->insertar_envio($contactosCampania[$i]['id_contactos'], $id);
                
            }
            $mail->ClearAddresses();
            sleep(2);
        }
    }

    private function insertar_envio($campania, $contacto)
    {
        $sql = "UPDATE Persons SET enviado = 1 WHERE id_campania = '$campania' AND id_contactos = '$contacto'";
        $this->_dbc->ejecutar($sql);
    }

    private function contactosCampania($id)
    {
        $i = 0;
        $sql = "select 
                    contactos.id_contactos,
                    contactos.nombre as nombre_contactos,
                    contactos.email,
                    campanias.nombre as nombre_campanias,
                    campanias.descrpcion,
                    campanias.titulo,
                    campanias.contenido,
                    campanias.pie,
                    plantillas.url
                    from
                    plantillas
                    inner join campanias ON plantillas.id_plantillas = campanias.id_plantillas
                    inner join contactos_campania ON campanias.id_campania = contactos_campania.id_campania
                    inner join contactos ON contactos_campania.id_contactos = contactos.id_contactos
                    WHERE
                    campanias.id_campania =  '$id'
                    GROUP BY contactos.id_contactos";
        $post = $this->_dbc->ejecutar($sql);
        while ($x = $this->_dbc->obtener_fila($post, 0)) {
            $a[$i] = $x;
            $i++;
        }
        return $a;
    }
    
    public function campaniaRecibida ($usuario ,$campania)
    {
        $sql = ("UPDATE contactos_campania SET reviso = 1 WHERE id_contacto=".$usuario." and id_campania=".$campania.";");
        $this->_dbc->ejecutar($sql);    
    }
        


}

