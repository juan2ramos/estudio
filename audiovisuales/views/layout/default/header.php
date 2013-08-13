<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title><?php if(isset($this->titulo)) echo $this->titulo; ?></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale= 1">
        <meta name="description" content="">
        <meta name="author" content="juan2ramos">

        <!-- Estilos -->
        <link rel= "stylesheet" href="<?php echo $_layoutParams['ruta_css']; ?>style.css" >
        <link rel= "stylesheet" href="<?php echo $_layoutParams['ruta_css']; ?>responsive.css" >

        <!-- JavaScript -->
        <script src="<?php echo $this->_config->get('BaseUrl'); ?>/public/js/jquery.js" type="text/javascript"></script>
        <script src="<?php echo $this->_config->get('BaseUrl'); ?>/public/js/jquery.validate.js" type="text/javascript"></script>

        <?php if(isset($_layoutParams['js']) && count($_layoutParams['js'])): ?>

            <?php for($i=0; $i < count($_layoutParams['js']); $i++): ?>
                <script src="<?php echo $_layoutParams['js'][$i] ?>" type="text/javascript"></script>
            <?php endfor; ?>


        <?php endif;

       /* if($item && $menu[0]['id'] == $item ){
            $_item_style = 'current';
        }else {$_item_style = '';}*/

        ?>
    </head>

    <body>
        <body>

            <header>
                <div id = "logo">
                    <a href="<?php echo $this->_config->get('BaseUrl'); ?>" >
                        <img src="<?php echo $_layoutParams['ruta_img']; ?>logoUC.png">
                    </a>
                </div>
                <h1><?php echo $this->_config->get('AppName'); ?></h1>
            </header>
            <nav>                
                     <?php echo $menu; ?>  
            </nav>  
 

