<!doctype html>
<title>Terminale</title>

<style>
html {
	background: #555;
}

h1 {
	margin-bottom: 20px;
	font: 20px/1.5 sans-serif;
}
</style>
<script src="jquery-1.11.2.js"></script>
<script src="jquery.atmosphere.js"></script>
<script src="term.js"></script>


<script>
	$(function() {
		"use strict";

		var socket = $.atmosphere;
		var transport = 'websocket';
		var term = new Terminal();
		
		var request = {
			url : 'http://localhost:8080/AgenteAr4k/wsa/def/${mappa}',
			contentType : "application/json",
			trackMessageLength : true,
			logLevel : 'debug',
			shared : true,
			transport : transport,
			fallbackTransport : 'long-polling'
		};

		var subSocket = socket.subscribe(request);

		request.onOpen = function(response) {
			term = new Terminal({
				cols : 100,
				rows : 40,
				useStyle : true,
				screenKeys : true,
				cursorBlink : false
			});
		};

		socket.onMessage = function(response) {
			var message = response.responseBody;
			console.log(response);
			term.write(message);
		};

		request.onClose = function() {
			term.destroy();
		};

		term.on('data', function(data) {
			subSocket.push(data);
		});

		term.on('title', function(title) {
			document.title = title;
		});

		term.open(document.body);

		term.write('\x1b[31mWelcome to Rossonet!\x1b[m\r\n');
	});
</script>
