<?php


include 'conexion.php';
$cedula=$_POST['cedula'];

$consulta="delete from personas where cedula ='".$cedula"'";

mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
?>
