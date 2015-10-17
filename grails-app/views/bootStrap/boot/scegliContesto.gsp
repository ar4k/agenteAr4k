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
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg3.jpg')}">
		<div class="content-wrapper clearfix">
			<div class="col-sm-12 col-md-10 pull-right">

				<section class="feature-text">
					<h2>Scegli il contesto applicativo</h2>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>vaso</strong> Ar4k può ospitare più <strong>contesti</strong>.
						Un <strong>contesto</strong> associa <strong>utenti</strong>, <strong>vasi</strong>,
						<strong>connessioni</strong> e <strong>memi</strong>. Un'<strong>interfaccia</strong>
						può essere associata ad un solo <strong>contesto</strong> per
						volta. Scegliere il contesto per questa <strong>interfaccia</strong>
						web tra quelli diponibili.
					</p>

					<g:each in="${listaContesti}">
						<g:if test="${it}">
							<p>
								<a href="${createLink(event: 'scegliInterfaccia')}&contesto=${it.id}"
									class="link-scroll btn btn-danger btn-outline-inverse btn-lg">Scegli
									il contesto "${it.descrizione?:it.id}"
								</a>
							</p>
						</g:if>
					</g:each>

					<p>
						<a href="${createLink(event: 'indietro')}"
							class="link-scroll btn btn-outline-inverse btn-lg">...torna
							alla configurazione SSH del vaso master</a>
					</p>
				</section>

			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

