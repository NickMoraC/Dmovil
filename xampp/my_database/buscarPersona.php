<?php

include 'conexion.php';

$cedula = $_GET['cedula'];

$consulta = "select * from personas where cedula = '$cedula'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
	$personas[] = array_map('utf8_encode', $fila);
}

echo json_encode($personas);
$resultado -> close();

?>
