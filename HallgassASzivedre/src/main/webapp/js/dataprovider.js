function getCloud(dataDisplayFunction) {
    $.get('cloudservlet?action=get_all', dataDisplayFunction(data));
}