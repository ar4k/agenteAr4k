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
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg2.jpg')}">
		<div class="content-wrapper clearfix">

			<div class="col-md-10 col-xs-12 col-sm-12 pull-right">
				<h2>Configurazione iniziale Ar4k</h2>
				<p class="text-justify" style="text-align: justify;">La
					piattaforma non è configurata. Il sistema Ar4k è composto dalla
					console (interfaccia) che genera le pagine web che state leggendo
					in questo momento e da uno o più macchine a cui la console accede.
					L'accesso avviene via ssh con autenticazione tramite chiave
					privata.</p>

			</div>
			<g:if test="${verifica==true}">
				<!-- Se il server raggiunge internet -->
				<div class="col-md-10 col-xs-12 col-sm-12 pull-right">
					<p class="text-justify" style="text-align: justify;">
						E' possibile inserire un codice di configurazione automatica
						reperibile da <a href="http://www.rossonet.org/negozio"
							target="_new">Rossonet</a>
					</p>
					<p>
						<a href="${createLink(event: 'configuraCodCommerciale')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Inserisci
							codice Ar4k Rossonet</a>
					</p>
					<p class="text-justify" style="text-align: justify;">
						oppure si può procedere alla configurazione inserendo manualmente
						i parametri. Per iniziare bisogna configurare l'accesso SSH ad un
						utente, non necessariamente "root", di una macchina Unix/Linux. Un
						accesso SSH ad un macchina, nella terminologia di Ar4k è
						denominato <strong>vaso</strong>. Il vaso primario a cui sono
						connesse le <strong>interfacce</strong> di un <strong>contesto</strong>
						è denominato <strong>vaso master</strong>. Maggiore documentazione
						è disponibile nella <a
							href="https://github.com/rossonet/agenteAr4k" target="_new">pagina
							del progetto su GitHub</a>.
					</p>
					<p>
						<a href="${createLink(event: 'configuraMaster')}"
							class="link-scroll btn btn-outline-inverse btn-lg">Configura
							l'accesso SSH al vaso master</a>
					</p>
					<!-- .col-sm-10 -->
				</div>
			</g:if>
			<g:else>
				<!-- Se il server non è connesso direttamente a internet -->
				<div class="col-md-10 col-xs-12 col-sm-12 pull-right">
					<p class="text-justify" style="text-align: justify;">Il sistema
						Ar4k non è in grado di accedere direttamente a Internet. Il
						problema potrebbe essere generato dalla configurazione di rete
						delle macchina che sta generando questa pagina web: il calcolatore
						potrebbe essere scollegata dalla rete, i server dns potrebbero non
						funzionare (se necessario si può utilizzare 8.8.8.8 di Google o il
						193.43.2.1 del Cineca) o la rete che ospita la macchina potrebbe
						avvere un accesso a Internet controllato per motivi di sicurezza.
						E' possibile configurare un proxy (Socks4,Socks5 o web) per
						accedere a Internet.</p>
				</div>
				<div class="col-md-10 col-xs-12 col-sm-12 pull-right">
					<form action='#' class="form-style validate-form clearfix"
						method='POST' id="proxyJvmForm" name="proxyJvmForm"
						autocomplete='off' role="form">
						<div class="form-group">
							<label for="proxyJvm">Indirizzo e utente</label> <input
								type="text" name="proxyJvm"
								class="text-field form-control validate-field required"
								data-validation-type="string" id="proxyJvm"
								placeholder="<http/socks5>://<utente>@<macchina>:<porta>"
								name="proxyJvm">
						</div>
						<div class="form-group">
							<label for="passwordJvm">Password</label> <input type="password"
								name="passwordJvm"
								class="text-field form-control validate-field required"
								data-validation-type="required" id="passwordJvm"
								placeholder="Password" name="passwordJvm">
						</div>
						<div class="form-group">
							<a href="${createLink(event: 'configuraProxyJvm')}"
								class="link-scroll btn btn-outline-inverse btn-lg">Configura
								il proxy</a>
						</div>
					</form>
				</div>

			</g:else>
		</div>
		<!-- .content-wrapper -->
	</article>
</body>
</html>

