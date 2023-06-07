/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let inputCrear = document.getElementById("crear");

function validarMarca() {
    let inputMarca = document.getElementById("marca");
    let marca = inputMarca.value;

    let regex = /^[A-Za-z\s]+$/;
    if (regex.test(marca)) {
        inputMarca.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputMarca.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}

function validarNumSerie() {
    let inputNumSerie = document.getElementById("numSerie");
    let numSerie = inputNumSerie.value;

    let regex = /^[A-Z]{3}-[0-9]{5}$/;

    if (regex.test(numSerie)) {
        inputNumSerie.style.borderColor = "";
        inputCrear.disabled = false;
    } else {
        inputNumSerie.style.borderColor = "red";
        inputCrear.disabled = true;
    }
}
