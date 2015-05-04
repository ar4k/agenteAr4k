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
					<h2>Scegli il contesto applicativo</h2>
					<p class="text-justify" style="text-align: justify;">...
					<p>
					<p>
						<a href="${createLink(event: 'indietro')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Torna alla
							configurazione SSH del vaso master</a>
					</p>
					<p>
						<a href="${createLink(event: 'scegliInterfaccia')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Scegli
							l'interfaccia applicativa</a>
					</p>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

