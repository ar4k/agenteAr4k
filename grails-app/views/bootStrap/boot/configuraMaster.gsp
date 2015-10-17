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
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg1.jpg')}">
		<div class="content-wrapper clearfix">
			<div class="col-md-10 col-xs-12 col-sm-12 pull-right">
				<h2>Configura l'accesso SSH al vaso master</h2>
				<p class="text-justify" style="text-align: justify;">
					Per accedere al vaso master sono necessari alcuni parametri. Con un
					codice di attivazione tutti i parametri tecnici sono
					pre-configurati. E' possibile acquisire un codice di configurazione
					dal <a href="https://registrazione.ar4k.eu" target="_new">portale
						del progetto Ar4k</a>.
				</p>
				<p>
					<a href="${createLink(event: 'configuraCodCommerciale')}"
						class="link-scroll btn-success btn btn-outline-inverse btn-lg">Utilizza
						un codice attivazione Ar4k</a>
				</p>
				<p class="text-justify" style="text-align: justify;">
					Per connettersi al vaso master sono necessari l'indirizzo e la
					porta TCP a cui connettersi via ssh, il nome utente con cui
					accedere al calcolatore e la chiave privata per permettere
					l'autenticazione. L'autenticazione con password non è permessa per
					motivi di sicurezza. Maggiori informazioni sulla configurazione
					dell'accesso con chiave RSA sono disponibili <a
						href="https://wiki.archlinux.org/index.php/SSH_Keys_%28Italiano%29"
						target="_new">nel wiki archlinux in Italiano.</a> La procedura
					rapida per configurare un nuovo accesso SSH su una macchina Linux
					(RedHat/Fedora/CentOS) esistente è disponibile in <a
						href="https://youtu.be/FohP0BmYhB8" target="_new">questo video</a>.
				</p>
			</div>
			<div class="col-md-10 col-xs-12 col-sm-12 pull-right">
				<form class="form-style validate-form clearfix" autocomplete='off'
					action="${createLink(event: 'verificaMaster')}" name="vasoMaster"
					method="post">
					<div class="form-group">
						<label for="indirizzoMaster">Indirizzo macchina (ip o
							nome)</label> <input type="text" name="indirizzoMaster"
							class="text-field form-control validate-field required"
							data-validation-type="string" id="indirizzoMaster"
							placeholder="ess. 127.0.0.1">
					</div>
					<div class="form-group">
						<label for="portaMaster">Porta accesso SSH</label> <input
							type="text" name="portaMaster"
							class="text-field form-control validate-field required"
							data-validation-type="required" id="portaMaster"
							placeholder="ess. 22">
					</div>
					<div class="form-group">
						<label for="utenteMaster">Utente accesso SSH</label> <input
							type="text" name="utenteMaster"
							class="text-field form-control validate-field required"
							data-validation-type="required" id="utenteMaster"
							placeholder="ess. root">
					</div>
					<div class="form-group">
						<label for="chiaveMaster">Chiave privata per autenticare</label>
						<textarea name="chiaveMaster"
							class="text-area form-control validate-field required"
							data-validation-type="required" id="chiaveMaster"
							placeholder='copiare la chiave privata ssh comprese le linee di intestazione e chiusura "-----BEGIN RSA PRIVATE KEY-----" e "-----END RSA PRIVATE KEY-----"'></textarea>
					</div>
					<div class="form-group">
						<button name="_eventId"
							class="btn btn-danger btn-sm btn-outline-inverse"
							value="verificaMaster"
							onClick="document.forms['vasoMaster'].submit();">Connetti
							il vaso master all'interfaccia</button>
					</div>
				</form>
			</div>
			<div class="col-md-10 col-xs-12 col-sm-12 pull-right">
				<p class="text-justify" style="text-align: justify;">Il sistema
					ha verificato la raggiungibilità di Internet da questa interfaccia
					provando una connessione http ad un indirizzo pubblico. La prova ha
					dato esito positivo e la configurazione del proxy per permettere
					l'accesso all'esterno non dovrebbe essere necessaria. Tuttavia, in
					particolari scenari di rete è necessario configurare l'accesso
					all'esterno tramite proxy.
				<p>
				<p>
					<a href="${createLink(event: 'configuraProxyJvm')}"
						class="link-scroll btn btn-outline-inverse btn-lg">Configura
						un proxy per accedere al vaso master</a>
				</p>
				<p>
					<a href="${createLink(event: 'indietro')}"
						class="link-scroll btn btn-outline-inverse btn-lg">...torna
						alla schermata di benvenuto</a>
				</p>
			</div>
			<!-- .col-sm-10 -->
		</div>
		<!-- .content-wrapper -->
	</article>

</body>
</html>

