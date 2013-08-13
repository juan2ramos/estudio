<?php

/*
 *
2 * -------------------------------------
 * Archivo con los datos de configuración
 * @autor juan2ramos
 * -------------------------------------
 *
 */

$config = Config::singleton();

$config->set('BaseUrl', 'http://napsdt.com/software/');
$config->set('DefaultControllers', 'index');
$config->set('DefaultLayout', 'default');

$config->set('AppName', 'RESERVAS AUDIOVISUALES');
$config->set('AppSlogan', 'Gesti&oacute;n de AudioVisuales');
$config->set('AppCompany', 'Ucentral');
$config->set('SessionTime', 100);
$config->set('HASH_KEY', '4f6a6d832be79');

$config->set('DBHost', 'localhost');
$config->set('DBUser', 'napsdtco_juan');
$config->set('DBPass', 'J2r*&*amos');
$config->set('DBName', 'napsdtco_audiovisuales');
$config->set('DBChar', 'utf8');