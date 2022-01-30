/**
 * 
 */

"use strict";

const display = document.getElementById('display');
let hasToClear = false;

function sendToServlet(data){
    const URL = 'api/ctrl';
    const jsonReqData = {
		method: 'POST',
	    cache: 'no-cache',
	    headers: {'Content-Type': 'application/json'},
	    body: JSON.stringify(data) 
  	};
	return fetch(URL,jsonReqData);
}

function fetchResultFromServet(){
    const data = {
        equation: display.innerText
    };
	sendToServlet(data).then(response => {
			console.log(response.status);
			return response.json();
		}) 
		.then(jsonArray => {
 			console.log(jsonArray);
			for (const studente of jsonArray) {				
 				console.log(studente);
			}
		});
}

function onClickPrintToDisplay(event){
    if(hasToClear){
        display.textNode = document.createTextNode('');
        hasToClear = false;
    }

    event.stopPropagation();
    const number = event.target.innerText;
    display.innerText+=number;
}

function initButton(text, onClick){
    const button = document.createElement("button");
    button.addEventListener('click',onClick)
    const textNode = document.createTextNode(text);
    button.appendChild(textNode);
    return button
}

function initKeyboard(){
    const divContainer = document.getElementById("numpad");
    let divRow = null;    
    for(let i = 1; i<10; i++){
        if(i%3==1){
            if(divRow!=null){
                divContainer.appendChild(divRow);
            }
            divRow = document.createElement("div");
            divRow.classList.add("centered");
        }
        divRow.appendChild(initButton(i,onClickPrintToDisplay));
    }
    if(divRow!=null){
        divContainer.appendChild(divRow);
    }
}

function initCommands(){
    const commands = ['+','-','*','/',','];
    const divContainer = document.getElementById("operation");
    let divRow = null;    
    commands.forEach((v,i)=>{
        if(i%2==0){
            if(divRow!=null){
                divContainer.appendChild(divRow);
            }
            divRow = document.createElement("div");
            divRow.classList.add("centered");
        }
        divRow.appendChild(initButton(v,onClickPrintToDisplay));
    });

    const button = initButton('=',onClickPrintToDisplay);
    button.addEventListener('click',fetchResultFromServet);
    button.id=("executeOperation");
    divRow.appendChild(button);

    if(divRow!=null){
        divContainer.appendChild(divRow);
    }
}

document.addEventListener("DOMContentLoaded",(event)=>{
    event.stopPropagation();
    initKeyboard();
    initCommands();
});