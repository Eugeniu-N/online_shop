// Script used to dynamically add a navigation bar to html pages

//Create the nav bar css style as a string and insert it into the style tag
let navBarStyle = `
.topnav {
	background-color: #333;
	overflow: hidden;
}

.topnav a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 15px 30px;
	text-decoration: none;
	font-size: 20px;
}
`
document.getElementsByTagName('style')[0].insertAdjacentHTML('afterbegin', navBarStyle);


let d1 = document.getElementById('navbar');
let navBar = `
	<a href="/items.html">Available items</a>
	<a href="/cart.html">Shopping cart</a>
	<a href="/login">Login</a>
`
d1.insertAdjacentHTML('afterbegin', navBar);
