
var formControl = {

	selectedPuff : {},

	filloutForm : function(puff) {
		$('#phraseInput').val(puff.phrase);
		$('#contentInput').val(puff.content);
		$('#weightInput').val(puff.weight);
		$('#dateInput').val(puff.date);

		$('#informationContainer').html(puff.content);
	},

	getPuffFromForm : function() {
		var phrase = document.getElementById('phraseInput').value;
		var weight = document.getElementById('weightInput').value;
		var date = document.getElementById('dateInput').value;
		var content = document.getElementById('contentInput').value;

		return {
			phrase : phrase,
			weight : weight,
			date : date,
			content : content
		};
	},

	clickOnPuff : function(puff) {

		selectedPuff = puff;
		this.filloutForm(puff);
	},

	savePuff : function() {

		var puff = this.getPuffFromForm();
		selectedPuff.phrase = puff.phrase;
		selectedPuff.weight = puff.weight;
		selectedPuff.date = puff.date;
		selectedPuff.content = puff.content;

		if (selectedPuff.id) {
			repository.updatePuff(puff);
		} else {
			repository.createPuff(puff);
		}
	}
};

function savePuff() {
	formControl.savePuff();
}
