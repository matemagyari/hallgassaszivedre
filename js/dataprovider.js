function displayCloud() {
	document.write("<p>My First JavaScript</p>");
	document.write(getData());
}

function getData2() {
	var cloud = [ 
		puff('a',1,'aaa'),
		puff('b',2,'bbb') 
	];
	return cloud
}

function convertData(data) {
	alert('convertData');
	console.log('convertData', data);
}

function readLocalJSON(jsonPath) {
	//console.log('readLocalJSON ' + jsonPath);
	//$.getJSON(jsonPath, convertData);
	//var data = jQuery.parseJSON(json);
}

function puff(aPhrase, aSize, aContent, aDate) {

	var aPuff = {
		phrase: aPhrase,
		size: aSize,
		content: aContent,
		date: aDate
	}
	return aPuff;
}

function popUp() {
	var data = getData();
	alert(data.puffs[0].phrase);
}