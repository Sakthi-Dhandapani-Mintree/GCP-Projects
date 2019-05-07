function validateForm() {
	var resource = {}
	
	var tempCertificate =null;
	if (document.forms["form1"]["mid"].value != '') {
		resource["id"] = document.forms["form1"]["mid"].value
		resource["firstName"] = document.forms["form1"]["firstName"].value
		resource["middleName"] = document.forms["form1"]["middleName"].value
		resource["lastName"] = document.forms["form1"]["lastName"].value
		resource["listOfTechWorkedOn"]=validateTechnology();
		resource["certifications"] =validatecertification(); 
		resource["projects"] =validateProjects()
		resource["applicationWorkLoadTypes"] = validateAppWrkLoadType();
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			url : "http://35.226.170.41/resourcetechnologymapping"+"/addResource",
			data : JSON.stringify(resource),
			dataType : 'json',
			success : function(result) {
				if (result == "Success") {
					alert('Data Inserted Successfully')
					document.getElementById("form1").reset();
				} else {
					alert('Data already exists')
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}

		});
		event.preventDefault();
	} else {
		alert('MID is Mandatory.Please Enter to Proceed')
	}
}

function validateTechnology(){
	var tempResource=null;
	if (document.forms["form1"]["tech1"].value != '') {
		tempResource = document.forms["form1"]["tech1"].value
	}
	if (document.forms["form1"]["tech2"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["tech2"].value
		} else {
			tempResource += ":" + document.forms["form1"]["tech2"].value
		}
	}
	if (document.forms["form1"]["tech3"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["tech3"].value
		} else {
			tempResource += ":" + document.forms["form1"]["tech3"].value
		}
	}
	if (document.forms["form1"]["tech4"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["tech4"].value
		} else {
			tempResource += ":" + document.forms["form1"]["tech4"].value
		}
	}
	
	return tempResource
}


function validatecertification(){
	var tempResource=null;
	if (document.forms["form1"]["certi1"].value != '') {
		tempResource = document.forms["form1"]["certi1"].value
	}
	if (document.forms["form1"]["certi2"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["certi2"].value
		} else {
			tempResource += ":" + document.forms["form1"]["certi2"].value
		}
	}
	if (document.forms["form1"]["certi3"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["certi3"].value
		} else {
			tempResource += ":" + document.forms["form1"]["certi3"].value
		}
	}
	if (document.forms["form1"]["certi4"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["certi4"].value
		} else {
			tempResource += ":" + document.forms["form1"]["certi4"].value
		}
	}
	
	return tempResource
}


function validateProjects(){
	var tempResource=null;
	if (document.forms["form1"]["proj1"].value != '') {
		tempResource = document.forms["form1"]["proj1"].value
	}
	if (document.forms["form1"]["proj2"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["proj2"].value
		} else {
			tempResource += ":" + document.forms["form1"]["proj2"].value
		}
	}
	if (document.forms["form1"]["proj3"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["proj3"].value
		} else {
			tempResource += ":" + document.forms["form1"]["proj3"].value
		}
	}
	if (document.forms["form1"]["proj4"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["proj4"].value
		} else {
			tempResource += ":" + document.forms["form1"]["proj4"].value
		}
	}
	
	return tempResource
}



function validateAppWrkLoadType(){
	var tempResource=null;
	if (document.forms["form1"]["appType1"].value != '') {
		tempResource = document.forms["form1"]["appType1"].value
	}
	if (document.forms["form1"]["appType2"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["appType2"].value
		} else {
			tempResource += ":" + document.forms["form1"]["appType2"].value
		}
	}
	if (document.forms["form1"]["appType3"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["appType3"].value
		} else {
			tempResource += ":" + document.forms["form1"]["appType3"].value
		}
	}
	if (document.forms["form1"]["appType4"].value != '') {
		if (tempResource === null) {
			tempResource = document.forms["form1"]["appType4"].value
		} else {
			tempResource += ":" + document.forms["form1"]["appType4"].value
		}
	}
	
	return tempResource
}



function restrictAlphabets(){
	
	$('#mid').keydown(function (e){
		 if ( event.keyCode > 64 ) {
		        event.preventDefault();
		    }
	})
}


function enableSubmit(){
	document.getElementById("button1").disabled = false;
	document.getElementById("form3").reset();
}

function getDetails(){
	document.getElementById("button1").disabled = true;
	var idorName = document.forms["form2"]["midName"].value;
	$.ajax({
		type : "GET",
		url :   "http://35.225.239.101/resourcetechnologymapping"+"/getResource/"+idorName,
		 dataType: 'json',
		success : function(result) {
			if(isNullOrEmpty(result)){
				 
				document.getElementById("form3").style.display="";
				parseDetails(result);
			} 
			console.log(result);
		},
		error : function(e) {
			alert("There is no MID available.!")
			console.log("ERROR: ", e);
		}
		
	});
}


function parseDetails(result){
	 
	console.log(result);
	let technologies = result.listOfTechWorkedOn
	var technologiesArray = technologies.split(":")
	var certificateionArray= result.certifications.split(":") ;
	var projectsArray= result.projects.split(":");
	var applnWkLoadArray= result.applicationWorkLoadTypes.split(":");

	document.forms["form3"]["mid"].value = 'M'+result.id
	document.forms["form3"]["firstName"].value = result.firstName
	document.forms["form3"]["middleName"].value = result.middleName
	document.forms["form3"]["lastName"].value = result.lastName
	document.forms["form3"]["tech1"].value = technologiesArray[0] === undefined ?  '': technologiesArray[0]
	document.forms["form3"]["tech2"].value = technologiesArray[1] === undefined ?  '': technologiesArray[1]
	document.forms["form3"]["tech3"].value = technologiesArray[2] === undefined ?  '': technologiesArray[2]
	document.forms["form3"]["tech4"].value = technologiesArray[3] === undefined ?  '': technologiesArray[3]
	document.forms["form3"]["certi1"].value = certificateionArray[0] === undefined ?  '':certificateionArray[0]
	document.forms["form3"]["certi2"].value = certificateionArray[1] === undefined ?  '':certificateionArray[1]
	document.forms["form3"]["certi3"].value = certificateionArray[2] === undefined ?  '':certificateionArray[2]
	document.forms["form3"]["certi4"].value = certificateionArray[3] === undefined ?  '':certificateionArray[3]
	document.forms["form3"]["proj1"].value = projectsArray[0] === undefined ?  '':projectsArray[0]
	document.forms["form3"]["proj2"].value = projectsArray[1] === undefined ?  '':projectsArray[1]
	document.forms["form3"]["proj3"].value = projectsArray[2] === undefined ?  '':projectsArray[2]
	document.forms["form3"]["proj4"].value = projectsArray[3] === undefined ?  '':projectsArray[3]
	document.forms["form3"]["appType1"].value = applnWkLoadArray[0] === undefined ?  '':applnWkLoadArray[0]
	document.forms["form3"]["appType2"].value = applnWkLoadArray[1] === undefined ?  '':applnWkLoadArray[1]
	document.forms["form3"]["appType3"].value = applnWkLoadArray[2] === undefined ?  '':applnWkLoadArray[2]
	document.forms["form3"]["appType4"].value = applnWkLoadArray[3] === undefined ?  '':applnWkLoadArray[3]
}

function isNullOrEmpty(data){
	if(data === null || data === 'undefined' || data === ''){
		return false
	}
	return true
}
