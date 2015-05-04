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
			<div class="col-sm-10 col-md-9 pull-right">

				<section class="feature-text">
					<h2>Inserisci codice di configurazione Ar4k</h2>
					<p class="text-justify" style="text-align: justify;">
						<a href="http://www.rossonet.org" target="_new">Rossonet</a> offre
						servizi cloud e assistenza sul prodotto Ar4k. Per registrare
						l'interfaccia <a href="http://www.rossonet.org/negozio" target="_new">acquistare un codice valido</a>
					<p>
					<p>
						<a href="${createLink(event: 'completata')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Conferma</a>
					</p>
					<p>
						<a href="${createLink(event: 'fallita')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Annulla</a>
					</p>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

