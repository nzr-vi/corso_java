/**
 * 
 */

"use strict";

const display = document.getElementById('display');
let hasToClear = false;
let lastWasOperator = false;
//let lastWasNumber = false;

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
	hasToClear = true;
    const data = {
        equation: display.innerText
    };
	sendToServlet(data).then(response => {
			//console.log(response.status);
			return response.json();
		}) 
		.then(jsonObj => {
 			//console.log(jsonArray);
			const resultString = jsonObj["result"].replace('.',',');
			const result= document.createTextNode(resultString);
			const h3Node = document.createElement("h3");
			h3Node.appendChild(result);
			display.appendChild(h3Node);			
		});
}

function onClickPrintNumber(event){
    event.stopPropagation();

    if(hasToClear){
        display.innerText = '';
        hasToClear = false;
    }

    let number = event.target.innerText;

	if(lastWasOperator){
		number = ' '+number;
	}	

    display.innerText+=number;
	lastWasOperator = false;
}

function onClickPrintOperator(event){
    if(hasToClear){
        display.innerText = '';
        hasToClear = false;
    }

    event.stopPropagation();
    let operator = ' '+event.target.innerText;
    display.innerText+=operator;
	lastWasOperator ^= true;
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
        divRow.appendChild(initButton(i,onClickPrintNumber));
    }
    if(divRow!=null){
        divContainer.appendChild(divRow);
    }
}

function initCommands(){
    const commands = ['+','-','*','/'];
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
        divRow.appendChild(initButton(v,onClickPrintOperator));
    });
    if(divRow!=null){
        divContainer.appendChild(divRow);
    }
    divRow = document.createElement("div");
    divRow.classList.add("centered");
    divRow.appendChild(initButton(',',onClickPrintNumber));
    const button = initButton('=',onClickPrintOperator);
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