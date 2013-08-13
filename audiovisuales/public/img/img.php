<?php
require('../Classes/mails.class.php');
$objEnvio = new Mails;
$usuario=$_GET['usuario'];
$campania=$_GET['campania'];
header('location:' . 'http://juan2ramos.com/campania/campaniaRecibida/'.$usuario.'/'.$campania);
header('content-type: image/png');
$img = file_get_contents('http://juan2ramos.com/campaniasemail/public/img/img.png');
echo $img;
?> 