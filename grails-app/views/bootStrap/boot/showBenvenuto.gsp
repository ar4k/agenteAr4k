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
					<h2>Configurazione iniziale Ar4k</h2>
					<p class="text-justify" style="text-align: justify;">La
						piattaforma Ar4k necessità di alcune informazioni per la
						configurazione iniziale. 
						<a href="${createLink(event: 'configuraProxyJvm')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Configura
							Proxy tra l'interfaccia e il vaso master</a>
					</p>
					<p>
						<a href="${createLink(event: 'configuraCodCommerciale')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Inserisci
							codice Ar4k Rossonet</a>
					</p>
					<p>
						<a href="${createLink(event: 'configuraMaster')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Configura
							l'accesso SSH al vaso master</a>
					</p>
				</section>
			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>
</body>
</html>

