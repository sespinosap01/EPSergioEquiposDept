/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let inputCrear = document.getElementById("crear");

//Funcion para validar la denominacion, esta funcion valida que el formato sea correcto
function validarDenominacion() {
    let inputDenominacion = document.getElementById("denominacion");
    let denominacion = inputDenominacion.value;

    let regex = /^[A-Za-z0-9\s]+$/;
    if (regex.test(denominacion)) {
        inputDenominacion.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputDenominacion.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}

//Funcion para validar el nombre del tutor, esta funcion valida que el formato sea correcto
function validarTutor() {
    let inputTutor = document.getElementById("tutor");
    let tutor = inputTutor.value;

    let regex = /^[A-Za-zÁÉÍÓÚáéíóúñÑ\s]+$/;
    if (regex.test(tutor)) {
        inputTutor.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputTutor.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}

