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
				cols : 180,
				rows : 90,
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

		term.open(document.getElementById("terminalWin"));

		//term.write('\x1b[31mPer attivare la sessione premere un tasto\x1b[m\r\n');
		setTimeout(function() {
			subSocket.push("export TERM=xterm-256color\n")
			subSocket.push("sleep 5 && clear\n")
		}, 2000);

	});
</script>
<div id="terminalWin" class="panel panel-primary"></div>

