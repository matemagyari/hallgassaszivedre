
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


function getCloud() {
	return getData().puffs;
}