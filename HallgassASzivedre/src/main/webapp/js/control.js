function filloutForm(puff) {
	document.getElementById('phraseInput').value = puff.phrase;
	document.getElementById('weightInput').value = puff.weight;
	document.getElementById('dateInput').value = puff.date;
	document.getElementById('contentInput').value = puff.content;	
}

function getPuffFromForm() {
	var phrase = document.getElementById('phraseInput').value;
	var weight = document.getElementById('weightInput').value;
	var date = document.getElementById('dateInput').value;
	var content = document.getElementById('contentInput').value;
	
	return {
			phrase : phrase,
			weight : weight,
			date : date,
			content : content
	}	
}