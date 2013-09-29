


var formControl = {

	filloutForm : function(puff) {
		$('#phraseInput').css('width', puff.phrase.length * 10);
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

		selectedPuffId = puff.id;
		this.filloutForm(puff);
	},

	updatePuff : function(callAfterUpdate) {

		var puff = this.getPuffFromForm();
		puff.id = selectedPuffId;
		repository.updatePuff(puff, callAfterUpdate);
	},
	
	deletePuff : function(callAfterUpdate) {

		repository.deletePuff(selectedPuffId, callAfterUpdate);
	},

	createPuff : function(callAfterUpdate) {

		selectedPuffId = null;
		var extendedCallAfterUpdate = function(id) {
			selectedPuffId = id;
			callAfterUpdate();
		};

		repository.createPuff(this.getPuffFromForm(), extendedCallAfterUpdate);
	}
};
