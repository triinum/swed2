 

function changeH1(message) {
    document.querySelectorAll("h1").forEach(h1 => { h1.innerText = message; });
}

changeH1("A web extension changed the heading in your page!");
