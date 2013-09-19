function getCloud(dataDisplayFunction) {
    $.get('cloudservlet?action=get_all', function(data) {
    	dataDisplayFunction(data);
    });
}