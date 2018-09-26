$(document).ready(function(){
    //$(".imagen")[0].addEventListener('change', archivo, false);
    //console.log($(".imagen")[0].value)
    $(".imagen").on('change',archivo);
    if($(".imagen")[0].value != null){
        $(".imagen").trigger('change');
    }
})

function archivo(evt) {
    var files = evt.target.files; // FileList object

    //Obtenemos la imagen del campo "file". 
    for (var i = 0, f; f = files[i]; i++) {
        //Solo admitimos imágenes.
        if (!f.type.match('image.*')) {
            continue;
        }

        var reader = new FileReader();

        reader.onload = (function (theFile) {
            return function (e) {
                // Creamos la imagen.
                document.getElementById("list").innerHTML = ['<img class="circle cambio" src="', e.target.result, '" title="', escape(theFile.name), '"/>'].join('');
            };
        })(f);

        reader.readAsDataURL(f);
    }
}
