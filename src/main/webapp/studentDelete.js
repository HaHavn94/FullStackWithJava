function main() {
	var url = "http://localhost:8080/FullStack_Exercise/deleteStudent";
	var input = document.getElementById("id").value ;
	var parameterData =
		"id="+input;
postDataToServer(url, parameterData, processResponseStatus1) ; 

}

function processResponseStatus1(status) {
	if (status.errorCode === 0) {
		alert("Student deleted.");
	}

	else if (status.errorCode === 1) {
		alert("Nothing deleted. The deletion of this id is already");
	}
	else {
		alert("Nothing delete. An unknown error occurred.");
	}
}

main();