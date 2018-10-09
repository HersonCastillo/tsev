var path = "http://localhost:8080/tsev/api/combo";

function llenarDepartamentos(){
    $.ajax({
        crossDomain: true,
        url: path + "/departamentos",
        type: "GET",
        success: function(data){
            data.forEach(function(departamento){
                $('#departamentosWS').append(new Option(departamento.descripcion, departamento.id));
            });
        }
    })
}

function llenarMunicipios(id){
    $.ajax({
        crossDomain: true,
        url: path + "/municipios/"+id,
        type: "GET",
        success: function(data){
            data.forEach(function(municipio){
                $('#municipiosWS').append(new Option(municipio.descripcion,municipio.id));
            })
        }
    })
}

function llenarCDV(id){
    $.ajax({
        crossDomain: true,
        url: path + "/cdv/"+id,
        type: "GET",
        success: function(data){
            data.forEach(function(cdv){
                $('#cdvWS').append(new Option(cdv.direccion,cdv.id));
            })
        }
    })
}

$(document).ready(function(){
    llenarDepartamentos();
    
    $('#departamentosWS').on('change',function(){
        var id = $('#departamentosWS').val();
        $('#municipiosWS').empty();
        $('#cdvWS').empty();
        llenarMunicipios(id);
        $('#cdvWS').append(new Option('Seleccione una opcion',0));
    })
    
    $('#municipiosWS').on('change',function(){
        var id = $('#municipiosWS').val();
        $('#cdvWS').empty();
        $('#cdvWS').append(new Option('Seleccione una opcion',0));
        llenarCDV(id);
    })
    
    $('#cdvWS').on('change',function(){
        var id = $('#cdvWS').val();
        console.log(id);
        $('#cdvValue').val(id);
    })
})


