var fakeRepository = {

	dataMap : {},	
	init : function () {
		dataMap = fakeDB.getData().puffs;
	},
	getData : function(callback) {
		callback(dataMap);
	},
	createPuff : function(puff) {
		puff.id = data.length;
		dataMap.push(puff);
	},
	updatePuff : function(puff) {
		var index = 0;
		for(;index<dataMap.length && puff.id != dataMap[index].id;index++) {
		}
		console.log('index ' + index);
		dataMap[index] = puff;
	}
};

var realRepository = {
		
	getData : function(callback) {
		$.get('cloudservlet?action=get_all', function(data) {
			puffs = data;
			callback(data);
		});
	},
	createPuff : function(puff) {
		$.post('cloudservlet?action=update', function(data) {
			console.log(data);
		});		
	},
	updatePuff : function(puff) {
		$.post('cloudservlet?action=update', function(data) {
			console.log(data);
		});		
	}
};

fakeRepository.init();
var repository = fakeRepository;
