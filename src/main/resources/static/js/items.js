/*
Get all the items from the api and display them in a dynamically created table
*/

import {addItemToCart} from './cookies.js';

var itemList, itemListNumberOfFields;
var i, j;
var newRow, newCell, cellValue, newText, cartButton;

const Http = new XMLHttpRequest();
const url='/api/v1/items';
Http.open("GET", url);
Http.send();

function getId(element){
	console.log(element);
	let button = element;
	let td = button.parentNode;
	let row = td.parentNode;
	let body = row.parentNode;
	let table = body.parentNode;

	let itemJson = {
		id: `${row.cells[0].innerHTML}`,
		name: `${row.cells[1].innerHTML}`,
		quantity: 1,
		price: 0
	}
	console.log(itemJson);
	addItemToCart(itemJson);
}

function whichFieldOfItem(index){
	let field;
	switch (index) {
		case 0:
			field = "id";
			break;
		case 1:
			field = "name";
		  break;
		case 2:
			field = "price";
		  break;
	}
	return field;
}

let tableWritten = false;
Http.onreadystatechange = function(){
	if (this.readyState == 4 && this.status == 200){
		console.log('SONO QUI');
		itemList = JSON.parse(Http.responseText);
		itemListNumberOfFields = Object.keys(itemList[0]).length;

		console.log(`There are ${itemList.length} items and each has ${itemListNumberOfFields} fields`);
		console.log(`First element is ${JSON.stringify(itemList[0])}`);
		console.log(`Full list is\n ${JSON.stringify(itemList)}`);

		let itemsTable = document.getElementById("itemsTableBody");

		/* First method using innerHTML
		let allTableRows = '';
		for (i = 0; i < itemList.length; i++){
			allTableRows += `<tr>	<td>${itemList[i].id}</td> <td>${itemList[i].name}</td> <td>\$${itemList[i].price}</td> <td><button onclick="getId(this)">Add to cart</button></td>	</tr>`;
		}
		itemsTable.innerHTML = allTableRows;
		*/

		// Second method using DOM manipulation, which reportedly is faster than innerHTML
		for (i = 0; i < itemList.length; i++){
			newRow = itemsTable.insertRow(-1);	// append new row to the table
			//console.log(`outer iteration ${i}`);

			for (j = 0; j < itemListNumberOfFields; j++){
				//console.log(`inner iteration ${j}`);
				newCell = newRow.insertCell(j);	// create new cell
				cellValue = itemList[i][`${whichFieldOfItem(j)}`]; // create the value of the cell
				//console.log(`cellValue is ${cellValue}`);

				newText = document.createTextNode(cellValue);	// create the actual text node
				newCell.appendChild(newText);	// add the text node to the cell
			}

			newCell = newRow.insertCell(j);
			cartButton = document.createElement('button');
			cartButton.innerHTML = 'Add to cart';
			cartButton.onclick = function(){
				getId(this);
			}
			newCell.appendChild(cartButton);
		}
		
		//tableWritten = true;
	}
}

console.log(retrieveCookieByName('prova'));
