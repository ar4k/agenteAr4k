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
					<h2>Configura vaso master</h2>
					<p class="text-justify" style="text-align: justify;">Configura
						l'accesso SSH al nodo master.
					<p>
					<p>
						<a href="${createLink(event: 'indietro')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Torna alla
							schermata iniziale</a>
					</p>
					<p>
						<a href="${createLink(event: 'configuraProxyJvm')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Configura
							il proxy per accedere al vaso master</a>
					</p>
					<p>
						<a href="${createLink(event: 'configuraCodCommerciale')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Utilizza
							un codice di attivazione Ar4k</a>
					</p>
					<p>
						<a href="${createLink(event: 'configuraProxyMaster')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Configura
							il proxy tra il vaso master e Internet</a>
					</p>
					<p>
						<a href="${createLink(event: 'scegliContesto')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Scegli il
							contesto a cui associare la maschera</a>
					</p>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

