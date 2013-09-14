function displayCloud() {
	document.write("<p>My First JavaScript</p>");
	document.write(getData());
}

function getData() {
	var cloud = [ 
		puff('a',1,'aaa'),
		puff('b',2,'bbb') 
	];
	return cloud
}

function puff(aPhrase, aSize, content) {

	var aPuff = {
		phrase: aPhrase,
		size: aSize,
		content: content
	}
	return aPuff;
}