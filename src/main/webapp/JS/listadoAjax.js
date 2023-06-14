$(document).ready(function () {
    $("#volver").attr("disabled", "disabled");
alert("estoy aqui");
    $("input[id='enviar']").click(function () {
alert("estoy aqui2");

        $.ajax({
            type: "post",
            url: "Ajax",
            data: {
                accion: "3"
            },
            success: function (respuesta) {
                $("tbody").empty();
                console.log(respuesta);
                $('thead').append("<tr><th>Nombre</th><th>Apellidos</th></tr>");
                $.each(respuesta, function (i, option) {
                    $('tbody').append(`<tr><td>${option.nombre}</td><td>${option.apellidos}</td></tr>`);

                });
                $("#volver").removeAttr("disabled");
                $("#enviar").attr("disabled", "disabled");

            }

        });

    });

});

