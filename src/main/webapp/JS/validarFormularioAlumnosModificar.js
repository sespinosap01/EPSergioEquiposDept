/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let inputModificar = document.getElementById("modificar");

function validarNombre() {
    let inputNombre = document.getElementById("nombre");
    let nombre = inputNombre.value;

    let regex = /^[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+$/;
    if (regex.test(nombre)) {
        inputNombre.style.borderColor = "";
        inputModificar.disabled = false;
    } else {
        inputNombre.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}

function validarApellidos() {
    let inputApellidos = document.getElementById("apellidos");
    let apellidos = inputApellidos.value;

    let regex = /^[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+$/;
    if (regex.test(apellidos)) {
        inputApellidos.style.borderColor = "";
        inputModificar.disabled = false;
    } else {
        inputApellidos.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}

function validarDNIModulo23(dni) {
    let letras = "TRWAGMYFPDXBNJZSQVHLCKE";
    let numero = parseInt(dni.substr(0, 8));
    let letra = dni.charAt(8).toUpperCase();
    let posicion = numero % 23;
    let letraCalculada = letras.charAt(posicion);
    return letra === letraCalculada;
}


function validarNIF() {
    let inputNIF = document.getElementById("nif");
    let nif = inputNIF.value;
    let regex = /^[0-9]{8}[A-Z]$/;

    if (regex.test(nif) && validarDNIModulo23(nif)) {
        inputNIF.style.borderColor = "";
        inputModificar.disabled = false;
    } else {
        inputNIF.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}

function validarEmail() {
    let inputEmail = document.getElementById("email");
    let email = inputEmail.value;

    let regex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;

    if (regex.test(email)) {
        const http = new XMLHttpRequest();
        const url = `Ajax`;
        const parametros = {
            "ayax": 1,
            "email": email
        };
        const params = JSON.stringify(parametros);

        http.onload = function () {
            if (http.readyState === 4 && this.status === 200) {
                let respuesta = http.responseText;
                var existe = JSON.parse(respuesta);

                if (existe[0] === "false") {
                    inputEmail.style.borderColor = "";
                    inputModificar.disabled = false;
                } else {
                    inputEmail.style.borderColor = "red";
                    inputModificar.disabled = true;
                    const notificacion = document.createElement("div");
                    notificacion.classList.add("notificacion");
                    notificacion.textContent = "El correo ya existe";
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
        inputEmail.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}