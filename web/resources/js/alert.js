$(document).ready(function () {
    var texto = $('#respuesta').val();
    console.log(texto);
    if (texto != "" && texto != undefined) {
        alertify.notify(texto,"success",5,function(){console.log("dismised")});
    }
    var error = $("#error").val();
    if(error != "" && error != undefined){
        alertify.notify(texto,"error",5,function(){console.log("dismised")});
    }
})


