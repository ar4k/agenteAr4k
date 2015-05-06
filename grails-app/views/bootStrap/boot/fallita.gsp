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
					<h2>Configurazione Annullata</h2>
					<p class="text-justify" style="text-align: justify;">
						La configurazione della piattaforma non è andata a buon fine. Se
						il browser con cui si stanno visualizzando queste pagine accede a
						Internet, tramite la chat che appare in basso a sinistra è
						possibile interagire con un operatore <a
							href="http://www.rossonet.org" target="_new">Rossonet</a>.
					<p>
					<p>
						<a href="${createLink(event: 'indietro')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Ricomincia
							la procedura di configurazione iniziale</a>
					</p>
					<p>
						<a href="${createLink(event: 'configuraCodCommerciale')}"
							class="link-scroll btn-success btn btn-outline-inverse btn-lg">Utilizza
							un codice AR4K emesso da Rossonet</a>
					</p>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

