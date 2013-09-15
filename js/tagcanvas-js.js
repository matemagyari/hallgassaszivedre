var tagsElementId = 'tags';
var canvasId = 'myCanvas';
var informationContainerElementId = 'informationContainer';

function windowOnload() {
    convertCloudToDOMElements(getCloud());
    startTagCanvas();
};

function startTagCanvas() {
        var options = {
            textColour: '#ff0000',
            outlineColour: '#ff00ff',
            reverse: true,
            depth: 0.8,
            maxSpeed: 0.10,
            weight: true,
            weightMode: 'both'
            //, freezeActive: true
        };
        try {
          TagCanvas.Start(canvasId,tagsElementId, options);
        } catch(e) {
          // something went wrong, hide the canvas container
          document.getElementById('myCanvasContainer').style.display = 'none';
        }    
}

function convertCloudToDOMElements(cloudPuffs) {

    var oUL = document.createElement('ul');
    var tagsDiv = document.getElementById(tagsElementId);
    tagsDiv.appendChild(oUL);

    //document.getElementById(informationContainerElementId).addEventListener("mouseover", function () { console.log('informationContainer ')}, false);

    for (var i=0; i < cloudPuffs.length; i++) {
        var anchor = creatAnchor(cloudPuffs[i]);
        appendAtoUL(tagsDiv.childNodes[1], anchor);
    }
}


function appendAtoUL(ulNode, anchor) {
    var oLI = document.createElement('li');
    oLI.appendChild(anchor);
    ulNode.insertBefore(oLI, ulNode.childNodes[0]);
};

function creatAnchor(puff) {
    var oA = document.createElement('a');
    var textNode = document.createTextNode(puff.phrase);
    oA.appendChild(textNode);  
    oA.onclick = clickOnPuff(puff);
    oA.style.fontSize = puff.weight+'pt';
    oA.href = '#';
    return oA; 
}

function clickOnPuff(puff) {
    return function () {
        document.getElementById(informationContainerElementId).innerHTML = puff.content;
    }
}