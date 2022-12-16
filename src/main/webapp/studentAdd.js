
function main() {
	var url = "http://localhost:8080/FullStack_Exercise/addStudent";

	var form = document.forms["formStudent"];
	var requestParameters =
		"id=" + form["id"].value +
		"&firstname=" + form["firstname"].value +
		"&lastname=" + form["lastname"].value +
		"&streetaddress=" + form["streetaddress"].value +
		"&postcode=" + form["postcode"].value +
		"&postoffice=" + form["postoffice"].value;

	var requestOptions = {

		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: requestParameters
	};


	fetch(url, requestOptions)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw "HTTP status code is " + response.status;
			}
		})
		.then(status => processResponseStatus(status))
		.catch(errorText =>alert("postDatatoServer failed: " + errorText));
}


function processResponseStatus(status) {
	if (status.errorCode === 0) {
		alert("Student data added!.");
		location.replace("movies.html")
		
	}

	else if (status.errorCode === 1) {
		alert("Cannot add the student. The id is already in use!");
	}

	else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
}

function goMain() {
	location.replace("movies.html")
}

main();