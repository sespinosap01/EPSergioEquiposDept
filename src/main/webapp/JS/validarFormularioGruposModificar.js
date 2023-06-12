/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let inputModificar = document.getElementById("modificar");

function validarDenominacion() {
    let inputDenominacion = document.getElementById("denominacion");
    let denominacion = inputDenominacion.value;

    let regex = /^[A-Za-z0-9\s]+$/;
    if (regex.test(denominacion)) {
        inputDenominacion.style.borderColor = "";
        inputModificar.disabled = false;
    } else {
        inputDenominacion.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}

function validarTutor() {
    let inputTutor = document.getElementById("tutor");
    let tutor = inputTutor.value;

    let regex = /^[A-Za-z\s]+$/;
    if (regex.test(tutor)) {
        inputTutor.style.borderColor = "";
        inputModificar.disabled = false;
    } else {
        inputTutor.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}

