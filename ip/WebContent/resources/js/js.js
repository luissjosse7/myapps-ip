function loadXMLDoc() {
	alert("ingresando a load servlet");
	var xmlhttp;
//	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
//		xmlhttp = new XMLHttpRequest();
//	} else {// code for IE6, IE5
//		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
//	}
	
	var url = document.getElementById("form_cuenta:urlServlet").value;
	// var url = "http://172.31.93.170:8180/ip/MyServlet";
	alert("url: " + url);
//	xmlhttp.open("POST", url, true);
	
	xmlhttp = createCORSRequest("POST", url);
	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			alert("responseText: " + xmlhttp.responseText);
			document.getElementById("form_cuenta:ipServlet").value = xmlhttp.responseText;
			// document.getElementById("form_cuenta:ipServlet").innerText = xmlhttp.responseText;
			// document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
		} else {
			alert("not onready state change: " + xmlhttp.readyState);
		}
	}
	

	// xmlhttp.withCredentials = true;
//	xmlhttp.setRequestHeader('X-PINGOTHER', 'pingpong');
	// xmlhttp.setRequestHeader('Content-Type', 'application/xml');
	xmlhttp.setRequestHeader('Content-Type', 'text/plain');
	xmlhttp.setRequestHeader('Access-Control-Allow-Origin', "*");
//	xmlhttp.setRequestHeader('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
	// xmlhttp.setRequestHeader('Access-Control-Allow-Headers', 'Content-Type');

	// xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
	xmlhttp.setRequestHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	xmlhttp.send();
	// //document.getElementById("form_cuenta:itIp").value = myIP;
}

function createCORSRequest(method, url) {
	var xhr = new XMLHttpRequest();
	if ("withCredentials" in xhr) {
		// XHR for Chrome/Firefox/Opera/Safari.
		xhr.open(method, url, true);
	} else if (typeof XDomainRequest != "undefined") {
		// XDomainRequest for IE.
		xhr = new XDomainRequest();
		xhr.open(method, url);
	} else {
		// CORS not supported.
		xhr = null;
	}
	return xhr;
}