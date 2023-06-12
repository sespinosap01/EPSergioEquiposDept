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
        inputNumSerie.style.borderColor = "";
        inputModificar.disabled = false;
    } else {
        inputNumSerie.style.borderColor = "red";
        inputModificar.disabled = true;
    }
}
