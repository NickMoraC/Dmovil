<?php

include 'conexion.php';

$cedula = $_POST['cedula'];
$nombre = $_POST['nombre'];
$apellido = $_POST['apellido'];
$email = $_POST['email'];
$telefono = $_POST['telefono'];

// Verificar si la cédula existe antes de actualizar los datos
$consulta_existe = "SELECT COUNT(*) AS total FROM personas WHERE cedula = '".$cedula."'";
$resultado_existe = mysqli_query($conexion, $consulta_existe);
$fila_existe = mysqli_fetch_assoc($resultado_existe);

if ($fila_existe['total'] == 1) {
    // Si la cédula existe, actualizar los datos
    $consulta_actualizar = "UPDATE personas SET nombre = '".$nombre."', apellido = '".$apellido."', email ='".$email."', telefono = '".$telefono."' WHERE cedula = '".$cedula."'";
    
    mysqli_query($conexion, $consulta_actualizar) or die(mysqli_error());
    echo "Los datos se han actualizado correctamente.";
} elseif ($fila_existe['total'] == 0) {
    // Si la cédula no existe, mostrar un mensaje de error
    echo "La cédula proporcionada no está registrada en la base de datos.";
} else {
    // Si hay más de un registro con la misma cédula, mostrar un mensaje de error
    echo "Error: Existen múltiples registros con la misma cédula en la base de datos.";
}

mysqli_close($conexion);

?>
