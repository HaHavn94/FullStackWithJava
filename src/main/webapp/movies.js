
function main() {

	getDataFromServer("/FullStack_Exercise/students", printStudent);

}

function printStudent(studentList) {

	var divOutput = document.getElementById("myTable");

	

	for (var student of studentList) {
		var row = "<tr><td>" + student.id + "</td>" +
			"<td>" + student.lastname + "</td>" +
			"<td>" + student.firstname +
			"</td><td>" + student.streetaddress +
			"</td><td>" + student.postcode +
			"</td><td>" + student.postoffice +
			"</td><td>" + createDeleteLinks(student.id) + 
			"</td></tr>";

		divOutput.innerHTML += row + "<br>"
	}
}

function addStudent() {
	location.replace("studentAdd.html");
}


function createDeleteLinks(studentId) {
	
	return  "<span class='link' onclick='deleteStudent(" + studentId + ");'>  Delete </span>";
	
		
}

function deleteStudent(studentId) {
	
	var url = "http://localhost:8080/FullStack_Exercise/deleteStudent";
	
	var parameterData =
		"id="+studentId;
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