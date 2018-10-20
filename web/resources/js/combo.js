var path = "http://localhost:8080/tsev/api/combo";

function llenarDepartamentos() {
    $.ajax({
        crossDomain: true,
        url: path + "/departamentos",
        type: "GET",
        success: function (data) {
            data.forEach(function (departamento) {
                $('#departamentosWS').append(new Option(departamento.descripcion, departamento.id));
            });
        }
    })
}

function llenarMunicipios(id) {
    $('#municipiosWS').empty();
    $('#cdvWS').empty();
    $.ajax({
        crossDomain: true,
        url: path + "/municipios/" + id,
        type: "GET",
        success: function (data) {
            data.forEach(function (municipio) {
                $('#municipiosWS').append(new Option(municipio.descripcion, municipio.id));
            })
        }
    })
    $('#cdvWS').append(new Option('Seleccione una opcion', 0));
}

function llenarCDV(id,cdv) {
    $('#cdvWS').empty();
    $.ajax({
        crossDomain: true,
        url: path + "/cdv/" + id,
        type: "GET",
        success: function (data) {
            data.forEach(function (cdv) {
                $('#cdvWS').append(new Option(cdv.direccion, cdv.id));
            })
            if(cdv != 0 || cdv != undefined){
                $('#cdvWS option[value='+cdv+']').attr('selected','selected');
            }
        }
    })
}

function seleccionarDepartamento(cdv) {
    $.ajax({
        crossDomain: true,
        url: path + "/informacion/departamento/" + cdv,
        type: "GET",
        success: function (departamento) {
            //$('#departamentosWS').prop('selectValue',departamento);
            //$('#departamentosWS > option[value=' + departamento + ']').attr("selected", true);
            llenarMunicipios(departamento);
            seleccionarMunicipio(cdv);
        }
    });
}
function seleccionarMunicipio(cdv){
    $.ajax({
        crossDomain: true,
        url: path + "/informacion/municipio/" + cdv,
        type: "GET",
        success: function (municipio) {
            //$('#municipiosWS option[value=' + municipio + ']').attr("selected", 'selected');
            llenarCDV(municipio,cdv);
        }
    });
}

$(document).ready(function () {
    llenarDepartamentos();

    var cdv = $('#cdvValue').val();
    if (cdv != "0" && cdv != undefined) {
        seleccionarDepartamento(cdv);
    }

    $('#departamentosWS').on('change', function () {
        var id = $('#departamentosWS').val();
        llenarMunicipios(id);
        $('#cdvValue').val(0);
    })

    $('#municipiosWS').on('change', function () {
        var id = $('#municipiosWS').val();
        llenarCDV(id,0);
        $('#cdvValue').val(0);
    })

    $('#cdvWS').on('change', function () {
        var id = $('#cdvWS').val();
        $('#cdvValue').val(id);
    })
})


