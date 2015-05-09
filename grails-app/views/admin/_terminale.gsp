<script src="${resource(dir:'admin',file:'jquery-1.11.2.js')}"></script>
<script src="${resource(dir:'admin',file:'jquery.atmosphere.js')}"></script>
<script src="${resource(dir:'admin',file:'term.js')}"></script>
<script>
	$(function() {
		"use strict";

		var socket = $.atmosphere;
		var transport = 'websocket';
		var term = new Terminal();
		
		var request = {
			url : "<g:createLink controller='wsa' action='def' absolute='true'/>",
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
				cols : 40,
				rows : 24,
				useStyle : true,
				screenKeys : true,
				convertEol : true,
				screenKeys : true
			});
		};

		socket.onMessage = function(response) {
			var message = response.responseBody;
			term.write(message);
		};

		request.onClose = function() {
			term.destroy();
		};

		term.on('data', function(data) {
			subSocket.push(data);
		});

		term.open(document.getElementById("terminal"));

		term.write('\x1b[31m${testoIntro}\x1b[m\r\n');
		setTimeout(function() {
			subSocket.push("${comandoAvvio}\n")
		}, 2000);

	});
</script>

<div class="row">
	<div class="col-lg-12">
		<div class="col-lg-3">
			<div class="panel panel-primary">
				<div class="text-right panel-heading">
					vaso ${mappa}
				</div>
				<div class="text-right panel-body">
					<p>
						${descrizione}
					</p>
				</div>
				<div class="panel-footer"></div>
			</div>
		</div>
		<div class="col-lg-9">
			<div id="terminal" class="panel panel-primary"></div>
		</div>
	</div>
</div>
