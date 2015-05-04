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
					<h2>Configura un accesso amministrativo</h2>
					<p class="text-justify" style="text-align: justify;">Per
						accedere ad un contesto nuovo bisogna creare un'utenza
					<p>
					<p>
						<a href="${createLink(event: 'completata')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Attiva
							Ar4k</a>
					</p>
					<p>
						<a href="${createLink(event: 'fallita')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Interrompi
							la procedura</a>
					</p>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

