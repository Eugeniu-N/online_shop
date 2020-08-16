function retrieveCookieByName(cookieName){
	let retrievedCookie = ("; "+document.cookie).split(`; ${cookieName}=`).pop().split(";").shift();
	console.log(`Retrieved shopping list: ${retrievedCookie}`);
	return retrievedCookie;
}

//Creates a new empty shopping list cookie
function createShoppingListCookie(){
	let shoppingList = [];
	let shoppingListAsString = JSON.stringify(shoppingList);
	console.log(shoppingListAsString);

	document.cookie = 'shoppingList=' +shoppingListAsString;
	return shoppingListAsString;
}


function addItemToCart(itemJson){
	let i = 0;
	let indexInCart = -1;
	let retrievedCartString = retrieveCookieByName("shoppingList");
	if (retrievedCartString == ''){
		retrievedCartString = createShoppingListCookie();
	}

	let parsedCart = JSON.parse(retrievedCartString);
	console.log(`Parsed cart is ${parsedCart}`);

	while (indexInCart == -1 && i < parsedCart.length){
		if (parsedCart[i]['id'] == itemJson['id']){
			indexInCart = i;
		}
		i++;
	}
	console.log(indexInCart);

	if (indexInCart == -1){
		parsedCart.push(itemJson);
	}
	else {
		parsedCart[indexInCart]['quantity']++;
	}

	document.cookie = 'shoppingList=' +JSON.stringify(parsedCart);
}


export {retrieveCookieByName, addItemToCart};