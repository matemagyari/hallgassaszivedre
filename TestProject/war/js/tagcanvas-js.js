var tagsElementId = 'tags';
var canvasId = 'myCanvas';
var informationContainerElementId = 'informationContainer';

function windowOnload() {
    convertCloudToDOMElements(getCloud());
    startTagCanvas();
    document.getElementById('testAnchor').onmouseover = function () { console.log('hello'); };
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
    oA.style.fontFamily = 'Gill Sans",Arial,Helvetica,sans-serif';
    oA.href = '#';
    //oA.onmouseover = function () { console.log('hello'); };
    /* 
    oA.addEventListener('mouseover', function (e) {
        e.preventDefault();
        console.log('hiii');
    }); */
    return oA; 
}

function clickOnPuff(puff) {
    return function () {
        document.getElementById(informationContainerElementId).innerHTML = puff.content;
    }
}