/**
 * 
 */
function createSession() {
	alert('Welcome!');
	//sesionStorage.setItem("Auth", document.getElementById("userName").value + "/" + document.getElementById("password").value)
}

function destroySession() {
	sesionStorage.removeItem("Auth");
}