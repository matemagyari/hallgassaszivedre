var fakeRepository = {

	getData : function(callback) {
		callback(fakeDB.puffs);
	},
	createPuff : function(puff, callAfterUpdate) {
		puff.id = fakeDB.puffs.length + 1;
		fakeDB.puffs.push(puff);
		callAfterUpdate(puff.id);
	},
	deletePuff : function(puffId, callAfterUpdate) {
		var index = 0;
		var tempArray = [];
		for(;index < fakeDB.puffs.length;index++) {
			if (fakeDB.puffs[index].id != puffId) {
				tempArray.push(fakeDB.puffs[index]);
			}
		}
		fakeDB.puffs = tempArray;
		/*
		for (; index < fakeDB.puffs.length && puffId != fakeDB.puffs[index].id; index++) {
		}
		
		fakeDB.puffs = fakeDB.puffs.slice(index);
		*/
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
		var successCallback = function(newPuffId) {
			console.log('create success ',newPuffId);
			callAfterUpdate(newPuffId.id);
		};
		$.ajax({
			type : 'POST',
			url : 'cloudservlet?action=create',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data : JSON.stringify(puff),
			success : successCallback,
			failure : function(data) {
				console.log('failure',data);
			},
			error : function(request, status, error) {
				console.log("something went wrong \nrequest.responseText : " + request.responseText + "\nstatus: "+status+"\nerror:"+error);
			}
		});
	},
	updatePuff : function(puff, callAfterUpdate) {
		var successCallback = function(responseData) {
			callAfterUpdate();
		};
		$.ajax({
			type : 'POST',
			url : 'cloudservlet?action=update',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data : JSON.stringify(puff),
			success : successCallback,
			failure : function(data) {
				console.log('failure',data);
			},
			error : function(request, status, error) {
				console.log("something went wrong \nrequest.responseText : " + request.responseText + "\nstatus: "+status+"\nerror:"+error);
			}
		});
	},
	deletePuff : function(puffId, callAfterUpdate) {
		var successCallback = function(responseData) {
			callAfterUpdate();
		};
		$.ajax({
			type : 'POST',
			url : 'cloudservlet?action=delete',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data : JSON.stringify({ id : puffId }),
			success : successCallback,
			failure : function(data) {
				console.log('failure',data);
			},
			error : function(request, status, error) {
				console.log("something went wrong \nrequest.responseText : " + request.responseText + "\nstatus: "+status+"\nerror:"+error);
			}
		});
	}
};

//var repository = fakeRepository;
var repository = realRepository;
