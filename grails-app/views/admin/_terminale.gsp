<script src="admin/jquery-1.11.2.js"></script>
<script src="admin/jquery.atmosphere.js"></script>
<script src="admin/term.js"></script>
<script>
	$(function() {
		"use strict";

		var socket = $.atmosphere;
		var transport = 'websocket';
		var term = new Terminal();
		
		var request = {
			url : "<g:createLink controller='wsa' action='def' absolute='true'/>/${mappa}",
			contentType : "application/json",
			trackMessageLength : true,
			logLevel : 'error',
			shared : true,
			transport : transport,
			fallbackTransport : 'long-polling'
		};

		var subSocket = socket.subscribe(request);

		request.onOpen = function(response) {
			term = new Terminal({
				cols : 80,
				rows : 40,
				useStyle : true,
				screenKeys : true,
				convertEol : true,
				screenKeys : true
			});
		};

		socket.onMessage = function(response) {
			var message = response.responseBody;
			//console.log(response);
			term.write(message);
		};

		request.onClose = function() {
			term.destroy();
		};

		term.on('data', function(data) {
			subSocket.push(data);
		});

		term.open(document.getElementById("terminal-${mappa}"));

		term.write('\x1b[31msessione in caricamento...\x1b[m\r\n');
		setTimeout(function() {
			subSocket.push("${comandoAvvio}\n")
		}, 2000);

	});
</script>
<div id="terminal-${mappa}" class="panel panel-primary"></div>

