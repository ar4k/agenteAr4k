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
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg5.jpg')}">
		<div class="content-wrapper clearfix">
			<div class="col-md-10 col-xs-12 col-sm-12 pull-right">

				<section class="feature-text">
					<h2>AR4K by Rossonet</h2>
					<p class="text-justify" style="text-align: justify;">
						<a href="http://www.rossonet.org" target="_new">Rossonet</a> offre
						servizi cloud, assistenza e moduli aggiuntivi per il prodotto open
						source Ar4k. Maggiori dettagli nel nostro <a
							href="http://www.rossonet.org/negozio" target="_new">punto
							vendita online.</a>
					<p>
					<form class="form-style validate-form clearfix" autocomplete='off'
						action="${createLink(event: 'completata')}"
						name="configuraCodCommerciale" method="post">
						<div class="form-group">
							<label for="codCommerciale">Codice AR4K</label> <input
								type="text" name="codCommerciale"
								class="text-field form-control validate-field required"
								data-validation-type="string" id="codCommerciale"
								placeholder="Inserire il cod.ar4k arrivato per email">
						</div>
						<div class="form-group">
							<button name="_eventId" class="btn-success btn btn-sm btn-outline-inverse"
								value="completata"
								onClick="document.forms['configuraCodCommerciale'].submit();">Attiva
								l'interfaccia AR4K con questo codice</button>
						</div>
						<p>
							<a href="${createLink(event: 'indietro')}"
								class="link-scroll btn btn-outline-inverse btn-lg">...ricomincia
								la procedura di boot di questa interfaccia AR4K</a>
						</p>

						<p>
							<a href="${createLink(event: 'fallita')}"
								class="link-scroll btn-warning btn btn-outline-inverse btn-lg"
								style="color: red;">Richiedi assistenza</a>
						</p>
					</form>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

