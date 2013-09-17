function getCloud() {
	
    $.get('cloudservlet', function(data) {
    	document.getElementById(informationContainerElementId).innerHTML = data;
    });
    
	return getData().puffs;
}