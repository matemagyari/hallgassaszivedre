


var formControl = {

	selectedPuff : {},

	filloutForm : function(puff) {
		$('#phraseInput').css('width', puff.phrase.length * 7);
		$('#phraseInput').val(puff.phrase);
		$('#weightInput').val(puff.weight);
		$('#dateInput').val(puff.date);
		tinymce.editors[0].setContent(puff.content); 
	},

	getPuffFromForm : function() {
		var phrase = document.getElementById('phraseInput').value;
		var weight = document.getElementById('weightInput').value;
		var date = document.getElementById('dateInput').value;
		var content = tinymce.editors[0].getContent(); 

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

	updatePuff : function(callAfterUpdate) {

		var puff = this.getPuffFromForm();
		puff.id = selectedPuff.id;
		this.copyFormToSelectedPuff(puff);
		repository.updatePuff(puff, callAfterUpdate);
	},

	createPuff : function(callAfterUpdate) {

		this.copyFormToSelectedPuff(this.getPuffFromForm());
		
		var extendedCallAfterUpdate = function(id) {
			selectedPuff.id = id;
			callAfterUpdate();
		};

		repository.createPuff(selectedPuff, extendedCallAfterUpdate);
	},
	
	copyFormToSelectedPuff : function(puff) {
		selectedPuff.phrase = puff.phrase;
		selectedPuff.weight = puff.weight;
		selectedPuff.date = puff.date;
		selectedPuff.content = puff.content;		
	}
};
