function getDataFromServer(URL, printFunc) {


	fetch(URL)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw "HTTP status code is " + response.status;
			}
		})
		.then(studentList => printFunc(studentList))
		.catch(errorText => alert("getDataFromServer failed: " + errorText))
}

function postDataToServer(URL, requestParameters, processAddResponse) {
	
	var requestOptions = {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: requestParameters
	};
	
	fetch (URL, requestOptions)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw "HTTP status code is " + response.status;
			}
		})
		.then(status => processAddResponse(status))
		.catch(errorText => alert("Fetch failed: " + errorText));
}






