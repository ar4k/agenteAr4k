<!DOCTYPE html>
<html lang="en">
<head>

<meta name="layout" content="atterraggio" />

<title><g:message code='spring.security.ui.login.title' /></title>


<style type="text/css">
.s2ui_hidden_button {
	visibility: hidden;
}
</style>

</head>

<body>

	<!-- #main-content -->
	<script>
		$(document).ready(function() {
			scroll_to_top();
		});
	</script>

	<article id="bootstrap" class="section-wrapper clearfix"
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg6.jpg')}">
		<div class="content-wrapper clearfix">
			<div class="col-sm-10 col-md-9 pull-right">

				<section class="feature-text">
					<h2>Configura un accesso amministrativo per Ar4k</h2>
					<p class="text-justify" style="text-align: justify;">Il
						contesto scelto non ha nessun accesso configurato. Per completare
						la procedura è necessario configurare un'utenza che svolgerà la
						funzione di amministratore per questo contesto.
					<p>
					<form class="form-style validate-form clearfix" autocomplete='off'
						action="${createLink(event: 'completata')}" name="configuraUtenteDemo"
						method="post">
						<div class="form-group">
							<label for="utenteDemo">Nome utente desiderato</label> <input
								type="text" name="utenteDemo"
								class="text-field form-control validate-field"
								data-validation-type="string" id="utenteDemo"
								placeholder="ess. rossonet">
						</div>
						<div class="form-group">
							<label for="passwordDemo1">Password</label> <input
								type="password" name="passwordDemo1"
								class="text-field form-control validate-field"
								data-validation-type="string" id="passwordDemo1"
								placeholder="Password">
						</div>
						<div class="form-group">
							<label for="passwordDemo2">Password (controllo)</label> <input
								type="password" name="passwordDemo2"
								class="text-field form-control validate-field"
								data-validation-type="string" id="passwordDemo2"
								placeholder="Password ripetuta">
						</div>
						<div class="form-group">
							<button name="_eventId"
								class="btn btn-success btn-sm btn-outline-inverse"
								value="completata"
								onClick="document.forms['configuraUtenteDemo'].submit();">Avvia
								interfaccia Ar4k con i parametri scelti</button>
						</div>
					</form>
					<p>
						<a href="${createLink(event: 'fallita')}"
							class="link-scroll btn-primary btn btn-outline-inverse btn-lg">Interrompi
							la procedura di configurazione</a>
					</p>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

