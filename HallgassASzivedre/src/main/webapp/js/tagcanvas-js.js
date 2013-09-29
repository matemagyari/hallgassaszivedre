var tagsElementId = 'tags';
var informationContainerElementId = 'informationContainer';

function windowOnload() {
	
	var callbackAfterDataArrived = function(data) {
		tagCanvasConverter.convertCloudToDOMElements(data, tagsElementId);
	    startTagCanvas();
	};
	repository.getData(callbackAfterDataArrived);

};

function adminWindowOnload() {
	
	var keypressFunction = function () { 
		var currentWidth = $('#phraseInput').val().length;
		var width =	Math.max(currentWidth,10);
		$('#phraseInput').css('width', width * 10); 
	};
	$('#phraseInput').on('keypress', keypressFunction);
	
	var callbackAfterDataArrived = function(data) {
		tagCanvasConverter.convertCloudToDOMElements(data, 'puffListContainer');
	};
	repository.getData(callbackAfterDataArrived);

};

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
			console.log('delete puff after', data);
			tagCanvasConverter.convertCloudToDOMElements(data, 'puffListContainer');
			if (data.length > 0) { formControl.clickOnPuff(data[0]); }
		};
		repository.getData(callbackAfterDataArrived);
	};
	formControl.deletePuff(callAfterUpdate);
};

function startTagCanvas() {
        var options = {
            textColour: '#000000',
            outlineColour: '#000000',
            reverse: true,
            depth: 0.8,
            maxSpeed: 0.10,
            weight: true,
            weightMode: 'size',
            freezeActive: true
        };
        try {
          TagCanvas.Start('myCanvas',tagsElementId, options);
        } catch(e) {
          // something went wrong, hide the canvas container
          document.getElementById('myCanvasContainer').style.display = 'none';
        }    
}

var tagCanvasConverter = {
		
		convertCloudToDOMElements : function(cloudPuffs, divId) {

		    var oUL = document.createElement('ul');
		    var tagsDiv = document.getElementById(divId);
		    while( tagsDiv.hasChildNodes() ){
		    	tagsDiv.removeChild(tagsDiv.lastChild);
		    }
		    tagsDiv.appendChild(oUL);

		    for (var i=0; i < cloudPuffs.length; i++) {
		        var anchor = this.creatAnchor(cloudPuffs[i]);
		        this.appendAtoUL(tagsDiv.childNodes[0], anchor);
		    }
		},
		appendAtoUL : function(ulNode, anchor) {
		    var oLI = document.createElement('li');
		    oLI.appendChild(anchor);
		    ulNode.insertBefore(oLI, ulNode.childNodes[0]);
		},

		creatAnchor : function(puff) {
		    var oA = document.createElement('a');
		    var textNode = document.createTextNode(puff.phrase);
		    oA.appendChild(textNode);  
		    oA.onclick = function () { formControl.clickOnPuff(puff); };
		    oA.style.fontSize = puff.weight+'pt';
		    oA.style.fontFamily = 'Gill Sans",Arial,Helvetica,sans-serif';
		    oA.href = '#';
		    return oA; 
		}	
};



