<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">
            html { height: 100% }
            body { height: 100%; margin: 0px; padding: 0px }
            #map_canvas { height: 100% }
        </style>
        <script type="text/javascript"
                src="https://maps.google.com/maps/api/js?sensor=true">
        </script>
        <script type="text/javascript">
            var map;
            var markersArray = [];
            
            function initialize() {
                var myLatlng  = new google.maps.LatLng(4.597386,-74.101415);
                var stylez =
                    [ 
                    { featureType: "road.local", 
                        elementType: "labels", 
                        stylers: [ { visibility: "simplified" } ] },
                    { stylers: [ { saturation: -99} ] },
                    { featureType: "road", elementType: "geometry",
                        stylers: [ { invert_lightness: false }, 
                            { lightness: 90 } ] },
                    { featureType: "road.arterial", elementType: "labels", 
                        stylers: [ { visibility: "on" }, { gamma: 2.46 } ] },
                    { featureType: "road.arterial", stylers: [ { lightness: -6 } ] },
                    { featureType: "water", stylers: [ { lightness: 14 } ] } ,
                    { featureType: "administrative", elementType: "labels", 
                        stylers: [ { visibility: "off" } ] } 
                ];
                var mapOptions = {
                    zoom: 17,
                    mapTypeId: 'smart',
                    center: myLatlng,
                    disableDefaultUI: false,
                    zoomControl: true,
                    zoomControlOptions: {
                        style: google.maps.ZoomControlStyle.SMALL,
                        position: google.maps.ControlPosition.RIGHT_TOP
                    },
                    streetViewControl: true,
                    streetViewControlOptions: {
                        position: google.maps.ControlPosition.RIGHT_TOP
                    },
                    mapTypeControl: false,
                    panControl: false
                    
                    
                    //mapTypeId: google.maps.MapTypeId.TERRAIN
                };
 
                map =  new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
                var jayzMapType = new google.maps.StyledMapType(stylez, {name: "smart"});
                map.mapTypes.set('smart', jayzMapType);
                var marker = new google.maps.Marker({
                    position: myLatlng, 
                    map: map,
                    title:"Hello World!",
                    icon: "http://www.estudio-creativo.com/web/wp-content/themes/roots/img/EC_pin.png"
                });
  
            }



        </script>
    </head>
    <body onload="initialize()">
        <div id="map_canvas" style="width:100%; height:100%"></div>

        <h1>Contacto</h1>
    </body>
</html>