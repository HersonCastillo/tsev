$(document).ready(function () {
    var texto = $('#respuesta').val();
    console.log(texto);
    if (texto != "" && texto != undefined) {
        alertify.success(texto);
    }
})


