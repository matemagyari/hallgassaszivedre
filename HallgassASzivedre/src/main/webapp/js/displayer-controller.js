var tagsElementId = 'tags';
var informationContainerElementId = 'informationContainer';

//DI
var dataToDOMConverter = tagCanvasConverter;
var displayer = tagCanvasDisplayer;

function updatePuff() {
	var callAfterUpdate = function() {
		adminWindowOnload();
	};
	formControl.updatePuff(callAfterUpdate);
};
function createPuff() {
	var callAfterUpdate = function() {
		adminWindowOnload();
	};
	formControl.createPuff(callAfterUpdate);
};

function deletePuff() {
	var callAfterUpdate = function() {
		var callbackAfterDataArrived = function(data) {
			dataToDOMConverter.convertCloudToDOMElements(data, 'puffListContainer');
			if (data.length > 0) {
				formControl.clickOnPuff(data[0]);
			}
		};
		repository.getData(callbackAfterDataArrived);
	};
	formControl.deletePuff(callAfterUpdate);
};

function windowOnload() {

	var callbackAfterDataArrived = function(data) {
		dataToDOMConverter.convertCloudToDOMElements(data, tagsElementId);
		displayer.start(tagsElementId);
	};
	repository.getData(callbackAfterDataArrived);

};

function adminWindowOnload() {

	var keypressFunction = function() {
		var currentWidth = $('#phraseInput').val().length;
		var width = Math.max(currentWidth, 10);
		$('#phraseInput').css('width', width * 10);
	};
	$('#phraseInput').on('keypress', keypressFunction);

	var callbackAfterDataArrived = function(data) {
		dataToDOMConverter.convertCloudToDOMElements(data, 'puffListContainer');
	};
	repository.getData(callbackAfterDataArrived);

};

