var fakeRepository = {

	getData : function(callback) {
		callback(fakeDB.puffs);
	},
	createPuff : function(puff, callAfterUpdate) {
		puff.id = fakeDB.puffs.length+1;
		fakeDB.puffs.push(puff);
		callAfterUpdate();
	},
	updatePuff : function(puff, callAfterUpdate) {
		var index = 0;
		for (; index < fakeDB.puffs.length && puff.id != fakeDB.puffs[index].id; index++) {
		}
		fakeDB.puffs[index] = puff;
		callAfterUpdate();
	}
};

var realRepository = {

	getData : function(callback) {
		$.get('cloudservlet?action=get_all', function(jsonData) {
			var data = [];
			for ( var i = 0; i < jsonData.length; i++) {
				data.push(jQuery.parseJSON(jsonData[i]));
			}
			callback(data);
		});
	},
	createPuff : function(puff, callAfterUpdate) {
		$.ajax({
			type : "POST",
			url : "cloudservlet?action=create",
			contentType : 'application/json',
			data : JSON.stringify(puff, callAfterUpdate),
			success : function(responseData) {
					console.log('response to create', responseData);
					callAfterUpdate();
			}
		});
	},
	updatePuff : function(puff) {
		$.ajax({
			type : "POST",
			url : "cloudservlet?action=update",
			contentType : 'application/json',
			data : JSON.stringify(puff),
			success : function(responseData) {
					console.log('response to create', responseData);
					callAfterUpdate();
			}
		});
	}
};

var repository = fakeRepository;
//var repository = realRepository;
