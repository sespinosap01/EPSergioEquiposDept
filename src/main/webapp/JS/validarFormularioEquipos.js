/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let inputCrear = document.getElementById("crear");

//Funcion para validar la marca, esta funcion valida que el formato sea correcto
function validarMarca() {
    let inputMarca = document.getElementById("marca");
    let marca = inputMarca.value;

    let regex = /^[A-Za-z0-9\s]+$/;
    if (regex.test(marca)) {
        inputMarca.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputMarca.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}

/*Funcion para validar el numero de serie por ajax, esta funcion valida que el formato 
sea correcto y nos lanza una notificacion en caso de que el numero de serie exista en la base de datos */
function validarNumSerie() {
    let inputNumSerie = document.getElementById("numSerie");
    let numSerie = inputNumSerie.value;

    let regex = /^[A-Z]{3}-[0-9]{5}$/;

    if (regex.test(numSerie)) {
        const http = new XMLHttpRequest();
        const url = `Ajax`;
        const parametros = {
            "ajax": 2,
            "numSerie": numSerie
        };
        const params = JSON.stringify(parametros);

        http.onload = function () {
            if (http.readyState === 4 && this.status === 200) {
                let respuesta = http.responseText;
                var existe = JSON.parse(respuesta);

                if (existe[0] === "false") {
                    inputNumSerie.style.borderColor = "";
                    inputCrear.disabled = false;
                } else {
                    inputNumSerie.style.borderColor = "red";
                    inputCrear.disabled = true;
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
        inputCrear.disabled = true;
    }
}