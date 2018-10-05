$(document).ready(function () {
    const toast = swal.mixin({
        toast: true,
        position: 'bottom-end',
        showConfirmButton: false,
        timer: 3000
    });

    var texto = $('#respuesta').val();
    console.log(texto);
    if (texto != "" && texto != undefined) {
        toast({
            type: 'success',
            title: texto
        })
    }
    var error = $("#error").val();
    if (error != "" && error != undefined) {
        toast({
            type: 'error',
            title: error
        })
    }

    $("#data").DataTable({
        "pageLength": 5,
        "lengthMenu": [5, 10],
        "language": {
            "lengthMenu": "Filas _MENU_ por pagina",
            "zeroRecords": "No se encontro - sorry",
            "infoEmpty": "No hay informacion que mostrar",
            "search": "Buscar:",
            "info": "Mostrando pagina _PAGE_ de _PAGES_",
        }
    });

    $("#departamento").change(function () {
        $("#cambiarMunicipios").click();
    });
    $("#municipio").change(function () {
        $("#cambiarCDV").click();
    });
})

function confirmacion(msg) {
    return confirm(msg);
}


