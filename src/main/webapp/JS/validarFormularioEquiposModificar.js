/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let inputModificar = document.getElementById("modificar");

function validarMarca() {
    let inputMarca = document.getElementById("marca");
    let marca = inputMarca.value;

    let regex = /^[A-Za-z\s]+$/;
    if (regex.test(marca)) {
        inputMarca.style.borderColor = "";
        inputModificar.disabled = false;
    } else {
        inputMarca.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}



function validarNumSerie() {
    let inputNumSerie = document.getElementById("numSerie");
    let numSerie = inputNumSerie.value;

    let regex = /^[A-Z]{3}-[0-9]{5}$/;

    if (regex.test(numSerie)) {
        const http = new XMLHttpRequest();
        const url = `Ajax`;
        const parametros = {
            "ayax": 2,
            "numSerie": numSerie
        };
        const params = JSON.stringify(parametros);

        http.onload = function () {
            if (http.readyState === 4 && this.status === 200) {
                let respuesta = http.responseText;
                var existe = JSON.parse(respuesta);

                if (existe[0] === "false") {
                    inputNumSerie.style.borderColor = "";
                    inputModificar.disabled = false;
                } else {
                    inputNumSerie.style.borderColor = "red";
                    inputModificar.disabled = true;
                    const notificacion = document.createElement("div");
                    notificacion.classList.add("notificacion");
                    notificacion.textContent = "El numero de serie está en uso";
                    document.body.appendChild(notificacion);
                    setTimeout(function () {
                        notificacion.remove();
                    }, 3000);
                }
            }
        }

        http.open('POST', url, true);
        http.setRequestHeader("Content-type", "application/json");
        http.send(params);
    } else {
        inputNumSerie.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}
