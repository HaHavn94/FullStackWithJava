
function main() {


fetch("http://localhost:8080/FullStack_Exercise/students")
		.then(response => response.json())
		.then(studentList => printStudent(studentList));
}

function printStudent(studentList) {
	
var divOutput = document.getElementById("myTable");
	
	
	for (var student of studentList) {
		var row = "<tr><td>" + student.id + "</td>"+
		"<td>" +student.lastname +  "</td>"+
		"<td>" +student.firstname + 
		 "</td><td>" +student.streetaddress + 
		 "</td><td>" +student.postcode+ 
		 "</td><td>" +student.postoffice + 		
		"</td></tr>";
		
			divOutput.innerHTML += row + "<br>"
	}
}
	
main();