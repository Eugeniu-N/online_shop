function square(number){
    return number*number;
}

function printResponse(text){
    console.log(text);
}

let theUrl = "https://jsonplaceholder.typicode.com/posts/1";
theUrl = "google.com"

function httpGetAsync(url, callback)
{
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", url, true); // true for asynchronous 
    xmlHttp.send(null);
}

//httpGetAsync(theUrl, printResponse);


const Http = new XMLHttpRequest();
const url='/api/v1/items';
Http.open("GET", url);
Http.send();

Http.onreadystatechange = (e) => {
  window.alert(Http.responseText)
}