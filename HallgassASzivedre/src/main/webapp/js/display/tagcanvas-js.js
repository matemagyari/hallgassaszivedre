var tagCanvasDisplayer = {
	start : function(divId) {
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
              TagCanvas.Start('myCanvas',divId, options);
            } catch(e) {
              // something went wrong, hide the canvas container
              document.getElementById('myCanvasContainer').style.display = 'none';
            }  		
	}	
};

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

