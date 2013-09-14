function windowOnload() {
        var options = {
            textColour: '#ff0000',
            outlineColour: '#ff00ff',
            reverse: true,
            depth: 0.8,
            maxSpeed: 0.05,
            weight: true,
            freezeActive: true
        };
        try {
          appendLItoUL(document.getElementById('tags').childNodes[1])
          TagCanvas.Start('myCanvas','tags', options);
        } catch(e) {
          // something went wrong, hide the canvas container
          document.getElementById('myCanvasContainer').style.display = 'none';
        }
};