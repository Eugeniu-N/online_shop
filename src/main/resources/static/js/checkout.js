/*
Add a button at the end of the cart table that when clicked will get all the items currently in the cart and send a request
to the server's checkout api
*/

import {retrieveCookieByName} from './cookies.js';

function checkout(){
	
	let shoppingListString = retrieveCookieByName('shoppingList');
	console.log(shoppingListString);

	const Http = new XMLHttpRequest();
	const url='/api/v1/items/checkout';
	Http.open("POST", url);
	Http.setRequestHeader('Content-Type', 'application/json');
	Http.send(shoppingListString);

	Http.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			document.cookie = 'shoppingList=[]'; 
			let delay = 5000;
			
			let successDiv = document.getElementById("success");
			successDiv.appendChild(document.createTextNode(Http.responseText));
			successDiv.appendChild(document.createElement('br'));
			successDiv.appendChild(document.createTextNode('The page will reload shortly'));

			setTimeout(function() {window.location.reload(false);}, delay);
		}
	}
}

document.getElementById("buynowbutton").addEventListener("click", checkout);
