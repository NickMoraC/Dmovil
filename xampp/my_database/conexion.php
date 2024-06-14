<?php
$hostname='localhost';
$database='my_database';
$username='root';
$password='';

$conexion=new mysqli($hostname,$username,$password,$database);
if ($conexion->connect_errno) {
    echo "lo sentimos, el sitio no sirve";
}
else{
echo "a ingresado correctamente a xampp";
}

?>