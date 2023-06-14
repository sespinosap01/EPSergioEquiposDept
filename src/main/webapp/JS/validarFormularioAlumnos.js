/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let inputCrear = document.getElementById("crear");

//Funcion para validar el nombre, esta funcion valida que el formato sea correcto
function validarNombre() {
    let inputNombre = document.getElementById("nombre");
    let nombre = inputNombre.value;

    let regex = /^[A-Za-zÁÉÍÓÚáéíóú\s]+$/;
    if (regex.test(nombre)) {
        inputNombre.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputNombre.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}

//Funcion para validar los apellidos, esta funcion valida que el formato sea correcto
function validarApellidos() {
    let inputApellidos = document.getElementById("apellidos");
    let apellidos = inputApellidos.value;

    let regex = /^[A-Za-zÁÉÍÓÚáéíóú\s]+$/;
    if (regex.test(apellidos)) {
        inputApellidos.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputApellidos.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}

//Logica para poder usar la funcion validarNIF() y validar que el nif tenga que ser correcto
function validarDNIModulo23(dni) {
    let letras = "TRWAGMYFPDXBNJZSQVHLCKE";
    let numero = parseInt(dni.substr(0, 8));
    let letra = dni.charAt(8).toUpperCase();
    let posicion = numero % 23;
    let letraCalculada = letras.charAt(posicion);
    return letra === letraCalculada;
}

//Esta funcion aplica la anterior, haciendo que valide tanto el propio nif como el formato
function validarNIF() {
    let inputNIF = document.getElementById("nif");
    let nif = inputNIF.value;
    let regex = /^[0-9]{8}[A-Z]$/;

    if (regex.test(nif) && validarDNIModulo23(nif)) {
        inputNIF.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputNIF.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}

/*Funcion para validar el email por ajax, esta funcion valida que el formato 
sea correcto y nos lanza una notificacion en caso de que el email exista en la base de datos */
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
                    inputCrear.disabled = false;
                } else {
                    inputEmail.style.borderColor = "red";
                    inputCrear.disabled = true;
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
        inputCrear.disabled = true;
    }
}

