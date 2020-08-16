/*
get the shopping list from cookie
get the prices via http request
create the table
*/

import {retrieveCookieByName} from './cookies.js';

let i, j, current, total = 0;

let shoppingListString = retrieveCookieByName('shoppingList');
console.log(shoppingListString);

let parsedShoppingList = JSON.parse(shoppingListString);
console.log(parsedShoppingList);

if (parsedShoppingList.length > 0){
	let idList = [];
	function compareById(a, b) {
		if (a['id'] < b['id']){
		return -1;
		}
		if (a['id'] > b['id']){
		return 1;
		}
		return 0;
	}
	for (i = 0; i < parsedShoppingList.length; i++){
		idList.push(parsedShoppingList[i]['id']);
	}
	idList.sort(compareById);
	console.log(idList);

	const Http = new XMLHttpRequest();
	const url='/api/v1/items/ids';
	Http.open("POST", url);
	Http.setRequestHeader('Content-Type', 'application/json');
	Http.send(JSON.stringify(idList));

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
			case 3:
				field = "quantity"
				break;
			default:
				field = 'error';
				break;  
		}
		return field;
	}

	Http.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			let itemList = JSON.parse(Http.responseText);
			let itemListNumberOfFields = Object.keys(itemList[0]).length +2; //Display all item fields +1 for quantity +1 for total

			console.log(`Full list is\n ${JSON.stringify(itemList)}`);

			for (i = 0; i < parsedShoppingList.length; i++){
				parsedShoppingList[i]['price'] = itemList[i]['price'];
			}

			let itemsTable = document.getElementById("itemsTableBody");

			let newRow;
			for (i = 0; i < itemList.length; i++){
				newRow = itemsTable.insertRow(-1);	// append new row to the table
				//console.log(`outer iteration ${i}`);

				for (j = 0; j < itemListNumberOfFields; j++){
					//console.log(`inner iteration ${j}`);
					let newCell = newRow.insertCell(j);	// create new cell
					//let cellValue = itemList[i][`${whichFieldOfItem(j)}`]; // create the value of the cell
					let cellValue = getCellValue(parsedShoppingList, itemList, i, j);
					//console.log(`cellValue is ${cellValue}`);

					let newText = document.createTextNode(cellValue);	// create the actual text node
					newCell.appendChild(newText);	// add the text node to the cell
				}
			}

			newRow = itemsTable.insertRow(-1);
			let newCell = newRow.insertCell(-1);
			newCell.setAttribute("colspan", itemListNumberOfFields);
			newCell.setAttribute("align", 'right');
			let cellValue = `Subtotal: $${total}`;
			let newText = document.createTextNode(cellValue);
			newCell.appendChild(newText);

			
		}
	}

	function getCellValue(shoppingList, itemList, listIndex, fieldIndex){
		let rtnValue;
		switch (fieldIndex){
			case 0:
				rtnValue = itemList[i]['id'];
				break;
			case 1:
				rtnValue = itemList[i]['name'];
				break;
			case 2:
				rtnValue = itemList[i]['price'];
				break;
			case 3:
				rtnValue = shoppingList[i]['quantity'];
				break;
			case 4:
				rtnValue = itemList[i]['price']*shoppingList[i]['quantity'];
				total = total + rtnValue;
				break;
			default:
				rtnValue = 'error';
				break;
		}
		return rtnValue;
	}
}