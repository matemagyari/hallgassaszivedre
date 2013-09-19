var selectedPuff = {};
var puffs = {}

function getCloud(dataDisplayFunction) {
    $.get('cloudservlet?action=get_all', function(data) {
    	puffs = data;
    	dataDisplayFunction(data);
    });
}

function clickOnPuff(puff) {
	
	selectedPuff = puff;
	filloutForm(puff);
	/*
    return function () {
        $.get('cloudservlet', function(data) {
        	document.getElementById(informationContainerElementId).innerHTML = data;
        });
        //document.getElementById(informationContainerElementId).innerHTML = puff.content;
    }
    */
}

function savePuff() {

	var puff = getPuffFromForm();
	selectedPuff.phrase = puff.phrase;
	selectedPuff.weight = puff.weight;
	selectedPuff.date = puff.date;
	selectedPuff.content = puff.content;
	
	if (selectedPuff.id) {
	    $.post('cloudservlet?action=update', function(data) {
	    	console.log(data)
	    });		
	} else {
	    $.post('cloudservlet?action=create', function(data) {
	    	console.log(data)
	    });		
	}
}