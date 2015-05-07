<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="AR4K Augmented Reality For Key">
<meta name="author" content="Rossonet s.c.a r.l (Imola) - Italy">
<title>AR4K Augmented Reality For Key - ACCESSO NEGATO</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'brain.png')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'brain.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'brain.png')}">

<!-- Bootstrap core CSS -->

<link
	href="${resource(dir: 'atterraggio', file: 'bootstrap/css/bootstrap.min.css')}"
	rel="stylesheet">

<!-- Bootstrap theme -->
<link
	href="${resource(dir: 'atterraggio', file: 'bootstrap/css/bootstrap-theme.min.css')}"
	rel="stylesheet">
<!-- owl carousel css -->
<link
	href="${resource(dir: 'atterraggio', file: 'js/owl-carousel/owl.carousel.css')}"
	rel="stylesheet">
<link
	href="${resource(dir: 'atterraggio', file: 'js/owl-carousel/owl.theme.css')}"
	rel="stylesheet">
<link
	href="${resource(dir: 'atterraggio', file: 'js/owl-carousel/owl.transitions.css')}"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${resource(dir: 'atterraggio', file: 'css/styles.css')}"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->


<g:javascript library="jquery" />
<r:require module="jquery-ui" />
<link rel="stylesheet" href="${resource(dir: 'atterraggio', file: 'font-awesome/css/font-awesome.min.css')}" type="text/css">
<r:layoutResources />

<style>
.s2ui_hidden_button {
	visibility: hidden;
}
</style>

</head>

<body>
	<div class="background-image-overlay"></div>

	<div id="outer-background-container"
		data-default-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg5.jpg')}"
		style="background-image: url(${resource(dir: 'atterraggio', file: 'images/other_images/bg5.jpg')});"></div>
	<!-- end: #outer-background-container -->

	<!-- Outer Container -->
	<div id="outer-container">

		<!-- Left Sidebar -->
		<section id="left-sidebar">

			<div class="logo">
				<g:link controller='admin' absolute='true'>
					<img
						<%-- 		src="${resource(dir: 'atterraggio', file: 'images/other_images/logo.png')}" --%>
						 		src="${resource(dir: 'images', file: '75.png')}"
						alt="AR4K (Augmented Reality For Key)">
				</g:link>
			</div>
			<!-- .logo -->

			<!-- Menu Icon for smaller viewports -->
			<div id="mobile-menu-icon" class="visible-xs"
				onClick="toggle_main_menu();">
				<span class="fa fa-th fa-2x"></span>
			</div>

			<ul id="main-menu">
				<li id="menu-item-chisiamo" class="menu-item scroll"><a
					href="#chisiamo">Chi siamo</a></li>
				<li id="menu-item-tecnologia" class="menu-item scroll"><a
					href="#tecnologia">Tecnologia</a></li>
				<li id="menu-item-processi" class="menu-item scroll"><a
					href="#processi">Processi</a></li>
				<li id="menu-item-marketing" class="menu-item scroll"><a
					href="#marketing">Marketing</a></li>
				<sec:ifNotLoggedIn>
					<li id="menu-item-entra" class="menu-item scroll"><a
						href="#entra">Entra</a></li>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>

					<li id="menu-item-esci" class="menu-item scroll"><form 
							name="submitForm" method="POST" class="text-center" style="padding-top:1em;"
							action="${createLink(controller: 'logout')}">
							<a href="javascript:document.submitForm.submit()">Non sono<br/><sec:loggedInUserInfo
									field="username" /></a>
						</form></li>

				</sec:ifLoggedIn>
			</ul>
			<!-- #main-menu -->

		</section>
		<!-- #left-sidebar -->
		<!-- end: Left Sidebar -->

		<section id="main-content" class="clearfix">


			<article id="negato" class="section-wrapper clearfix"
				data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg5.jpg')}">
				<div class="content-wrapper clearfix">
					<div class="col-sm-10 col-md-9 pull-right">

						<section class="feature-text">
							<h1>Informazione di sicurezza</h1>
							<p>
								<g:message code="springSecurity.denied.message" />
							</p>
							<p>
								<a href="${resource(dir: '/')}"
									class="link-scroll btn btn-outline-inverse btn-lg">Torna
									alla pagina principale</a>
							</p>
						</section>

					</div>
					<!-- .col-sm-10 -->
				</div>
				<!-- .content-wrapper -->
			</article>


			<!-- Comune -->
			<sec:ifNotLoggedIn>
				<article id="entra" class="section-wrapper clearfix"
					data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_rocca.jpg')}">
					<div class="content-wrapper clearfix">

						<h1 class="section-title">Autenticazione in AR4K</h1>
						<!--  
				<p class="text-error">
					<g:message code="springSecurity.denied.message" />
				</p>
				-->

						<!-- CONTACT DETAILS -->
						<div
							class="contact-details contact-details col-xs-12 col-sm-12 col-md-3">
							<p class="text-justify" style="text-align: justify;">Utilizzando il nostro portale
								internet acconsentite a farci rilevare determinati dati. Da
								parte nostra ci impegniamo a prelevare e conservare soltanto le
								informazioni strettamente necessarie e a proteggere sempre la
								vostra privacy.</p>
						</div>
						<!-- END: CONTACT DETAILS -->

						<!-- CONTACT FORM -->
						<div class="col-xs-12 col-sm-12 col-md-9">
							<!-- IMPORTANT: change the email address at the top of the assets/php/mail.php file to the email address that you want this form to send to -->

							<form action='${postUrl}'
								class="form-style validate-form clearfix" method='POST'
								id="loginForm" name="loginForm" autocomplete='off' role="form">
								<!-- form left col -->
								<div class="col-md-6 col-xs-12 col-sm-12">
									<div class="form-group">
										<input type="text" name="j_username"
											class="text-field form-control validate-field required"
											data-validation-type="string" id="username"
											placeholder="<g:message
									code='spring.security.ui.login.username' />"
											name="name">
									</div>
									<div class="form-group">
										<input type="password" name="j_password"
											class="text-field form-control validate-field required"
											data-validation-type="required" id="password"
											placeholder="<g:message
									code='spring.security.ui.login.password' />"
											name="email">
									</div>
									<div class="form-group">
										<label><input type="checkbox"
											name="${rememberMeParameter}" id="remember_me"
											checked="checked" /> <g:message
												code='Ricordami su questo computer' /></label>
									</div>
									<div class="form-group">
										<img
											src="${resource(dir: 'atterraggio', file: 'images/theme_images/loader-form.GIF')}"
											class="form-loader">
										<s2ui:submitButton class="btn btn-sm btn-outline-inverse"
											elementId='loginButton' form='loginForm' messageCode='Entra' />

									</div>
									<div class="form-group">
										<s2ui:linkButton class="btn" elementId='register'
											controller='register' messageCode='Registrati su AR4K' />
									</div>
									<div class="form-group">
										<g:link controller='register' class="btn"
											action='forgotPassword'>
											<g:message code='Password dimenticata ?' />
										</g:link>
									</div>

								</div>
								<div class="col-md-6 col-xs-12 col-sm-10">
									<!-- end: form left col -->
									<div class="col-md-6 col-xs-6 col-sm-6">
										<div class="form-group">
											<oauth:connect provider="facebook" id="facebook-connect-link">
												<img
													data-img-src="${resource(dir: 'images', file: 'facebook.png')}"
													class="lazy full-width" alt="Facebook">
											</oauth:connect>

										</div>
										<div class="form-group">
											<oauth:connect provider="google" id="google-connect-link">
												<img
													data-img-src="${resource(dir: 'images', file: 'googleplus.png')}"
													class="lazy full-width" alt="Google+">
											</oauth:connect>

										</div>
									</div>
									<div class="col-md-6 col-xs-6 col-sm-6">
										<div class="form-group">
											<oauth:connect provider="twitter" id="twitter-connect-link">
												<img
													data-img-src="${resource(dir: 'images', file: 'twitter.png')}"
													class="lazy full-width" alt="Twitter">
											</oauth:connect>

										</div>
										<div class="form-group">
											<oauth:connect provider="linkedin" id="linkedin-connect-link">
												<img
													data-img-src="${resource(dir: 'images', file: 'linkedin.png')}"
													class="lazy full-width" alt="LinkedIn">
											</oauth:connect>

										</div>
									</div>
								</div>
							</form>
						</div>
						<!-- end: CONTACT FORM -->
					</div>
					<!-- .content-wrapper -->
				</article>
			</sec:ifNotLoggedIn>



			<article id="chisiamo" class="section-wrapper clearfix"
				data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_colli.jpg')}">
				<div class="content-wrapper clearfix">
					<div class="col-sm-10 col-md-9 pull-right">

						<section class="feature-text">
							<h1>AR4K</h1>
							<h2>Augmented Reality for Key</h2>
							<p class="text-justify" style="text-align: justify;">La rivoluzione sociale di internet ha
								profondamente cambiato le abitudini delle persone, i modi di
								acquisto e i processi aziendali. Di fatto si è creata
								un'interazione sempre più dinamica e intensa tra il mondo reale
								e il mondo digitale. Le nostre soluzioni rappresentano per i
								clienti il grande vantaggio di un rapporto ancora più facile,
								personalizzato, intuitivo, semplice e immediato con il mondo
								digitale. Questa nuova forma di interazione geo spaziale, che
								utilizza tutte le potenzialità espresse e non espresse dei
								dispositivi mobili, incrementerà la produttività e soddisferà le
								sempre più esigenti richieste del mercato. Nella nostra visione
								saranno protagonisti non solo i dispositivi mobili, ma anche
								tutti i social network permettendo ai clienti di arrivare a più
								persone in maniera molto più rapida. In questo modo le forme di
								comunicazione già utlilizzate vengono di fatto riviste e
								reingegnerizzate per raggiungere più agevolmente e rapidamente
								fondamentali obiettivi: rendere sempre più dinamico e
								soddisfacente il rapporto tra l'azienda e i propri clienti,
								massimizzare le vendite e ottimizzare la produttività dei
								meccanismi interni.
							<p>
								</secti
						on>
					</div>
				</div>
			</article>
			<!-- .section-wrapper -->

			<article id="tecnologia" class="section-wrapper clearfix"
				data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_tech.jpg')}">
				<div class="content-wrapper clearfix">
					<div class="col-sm-10 col-md-9 pull-right">

						<section class="feature-text">
							<h1>AR4K</h1>
							<h2>tecnologia e metodi</h2>
							<p class="text-justify" style="text-align: justify;">Per realizzare i nostri progetti
								abbiamo scelto gli strumenti più validi, prediligendo le
								migliori tecnologie attualmente disponibili. Per scelta
								costruiamo le nostre soluzioni utilizzando esclusivamente
								software open source, perché in questo modo possiamo gestire il
								codice sorgente a qualsiasi livello di dettaglio. La licenza
								open source garantisce la piena libertà di progettazione
								infrastrutturale senza vincolarla ai meccanismi contabili legati
								al copyright. In funzione dei requisiti di usabilità condivisi
								con i clienti, la personalizzazione grafica finale dei nostri
								prodotti è affidata a una rete di partner certificati che
								condividono le nostre metodologie di sviluppo. Le nostre
								soluzioni garantiscono interoperabilità attraverso l'utilizzo di
								formati aperti (xml, html, soap, png) standardizzati (OSI, ISO)
								e non proprietari. L'utilizzo di tecnologie open source
								consolidate sul mercato, e di standard aperti garantisce
								un'ampia documentazione delle singole componenti funzionali del
								nostro software.
							<p>
						</section>
					</div>
				</div>
			</article>
			<!-- .section-wrapper -->

			<article id="processi" class="section-wrapper clearfix"
				data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_liutaio.jpg')}">
				<div class="content-wrapper clearfix">
					<div class="col-sm-10 col-md-9 pull-right">

						<section class="feature-text">
							<h1>AR4K</h1>
							<h2>gestione processi aziendali</h2>
							<p class="text-justify" style="text-align: justify;">AR4K è un nuovo orientamento nei
								processi aziendali che si basa sulla centralità del collegamento
								tra la realtà fisica aziendale e quella digitale.
								Indipendentemente dal settore in cui opera l'azienda, grazie a
								AR4K il mondo è a portata di click. Ogni problema è facilmente
								gestito e risolto, ogni quesito trova risposte veloci e agevoli,
								a cominciare dalla gestione dei consumi energetici, dal
								controllo sulla sicurezza interna e sui beni aziendali, fino
								agli interventi di manutenzione di ogni infrastruttura interna o
								esterna all'azienda. AR4K rende dinamico l'acquisto o la vendita
								di un bene, l'erogazione o l'internalizzazione di un servizio.
								Grazie a questa soluzione gestire tutte le informazioni e i
								prodotti sarà veloce e intuitivo e non richiederà nessun tipo di
								implementazione della struttura informatica dell'azienda,
								basterà aggiungere e incorporare nell'architettura teconologica
								aziendale funzionalità ancora non presenti.
							<p>
						</section>
					</div>
				</div>
			</article>
			<!-- .section-wrapper -->

			<article id="marketing" class="section-wrapper clearfix"
				data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_bologna.jpg')}">
				<div class="content-wrapper clearfix">
					<div class="col-sm-10 col-md-9 pull-right">

						<section class="feature-text">
							<h1>AR4K</h1>
							<h2>Marketing one to one</h2>
							<p class="text-justify" style="text-align: justify;">AR4K si basa sull'idea che nel
								rapporto tra l'azienda e i suoi clienti ci sia un link, un nuovo
								tipo diconnessione che permette all'azienda di conoscere in modo
								più approfondito i desideri, le necessità, le preferenze
								specifiche e le modalità di acquisto dei propri clienti finali.
								Noi creiamo efficacemente un link tra i prodotti aziendali e la
								realtà quotidiana del cliente, permettendo di meglio
								identificare il profilo di chi acquista, con quali motivazioni,
								con quale tempistica e in quali luoghi, garantendo sempre una
								risposta veloce, automatizzata ed efficiente. Nello stesso tempo
								saranno disponibili anche ulteriori elementi di conoscenza dei
								meccanismi interni dell'azienda, come ad esempio la gestione del
								magazzino, la logistica e prodotti più venduti, facilitando così
								eventuali campagne di marketing. Appare quindi come naturale una
								prospettiva che vede la fidelizzazione del cliente avvenire
								attraverso gli strumenti mobili di uso più comune come gli
								smartphone, ipad o altri dispositivi. Grazie a AR4K l'azienda
								dialogherà con il cliente attraverso l'utilizzo di interfacce
								uniche e fortemente personalizzabili, che racconteranno del
								rapporto esclusivo e di fiducia che cliente potrà creare con
								l'azienda. La nostra grafica è arrichita di colori, grafici e
								disegni che renderanno intuitivo, divertente e immediato
								l'acquisto di beni aziendali.
							<p>
						</section>
					</div>
				</div>
			</article>
			<!-- .section-wrapper -->






		</section>


		<!-- Fine Comunedi -->

		<!-- Footer -->
		<section id="footer">

			<!-- Go to Top -->
			<div id="go-to-top" onclick="scroll_to_top();">
				<span class="fa fa-chevron-circle-up fa-3x"></span>
			</div>

			<ul class="social-icons">
				<li><oauth:connect provider="facebook"
						id="facebook-connect-link">
						<img
							src="${resource(dir: 'atterraggio', file: 'images/social_icons/facebook.png')}"
							alt="Facebook">
					</oauth:connect></li>
				<li><oauth:connect provider="google" id="google-connect-link">
						<img
							src="${resource(dir: 'atterraggio', file: 'images/social_icons/googleplus.png')}"
							alt="Google+">
					</oauth:connect></li>
				<li><oauth:connect provider="linkedin"
						id="linkedin-connect-link">
						<img
							src="${resource(dir: 'atterraggio', file: 'images/social_icons/linkedin.png')}"
							alt="LinkedIn">
					</oauth:connect></li>
				<li><oauth:connect provider="twitter" id="twitter-connect-link">
						<img
							src="${resource(dir: 'atterraggio', file: 'images/social_icons/twitter.png')}"
							alt="Twitter">
					</oauth:connect></li>
			</ul>

			<!-- copyright text -->
			<div class="footer-text-line">
				<a href="http://www.gnu.org/licenses/agpl-3.0.html"
					target="_licenza">&copy;</a> 2014 <a href="http://www.rossonet.com"
					target="_rossonet">Rossonet s.c.a r.l.</a>
			</div>
		</section>
		<!-- end: Footer -->

	</div>
	<!-- #outer-container -->
	<!-- end: Outer Container -->

	<!-- Modal -->
	<!-- DO NOT MOVE, EDIT OR REMOVE - this is needed in order for popup content to be populated in it -->
	<div class="modal fade" id="common-modal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<div class="modal-body"></div>
				<!-- .modal-body -->
			</div>
			<!-- .modal-content -->
		</div>
		<!-- .modal-dialog -->
	</div>
	<!-- .modal -->


	<script>
		<s2ui:initCheckboxes/>
	</script>

	<!-- Javascripts
    ================================================== -->

	<!-- Jquery and Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script
		src="${resource(dir: 'atterraggio', file: 'bootstrap/js/bootstrap.min.js')}"></script>

	<!-- Easing - for transitions and effects -->
	<script
		src="${resource(dir: 'atterraggio', file: 'js/jquery.easing.1.3.js')}"></script>

	<!-- background image strech script -->
	<script
		src="${resource(dir: 'atterraggio', file: 'js/jquery.backstretch.min.js')}"></script>
	<!-- background image fix for IE 9 or less
       - use same background as set above to <body> -->
	<!--[if lt IE 9]>
    <script type="text/javascript">
    $(document).ready(function(){
      jQuery("#outer-background-container").backstretch("${resource(dir: 'atterraggio', file: 'images/other_images/bg5.jpg')}");
    });
    </script> 
    <![endif]-->

	<!-- detect mobile browsers -->
	<script
		src="${resource(dir: 'atterraggio', file: 'js/detectmobilebrowser.js')}"></script>

	<!-- owl carousel js -->
	<script
		src="${resource(dir: 'atterraggio', file: 'js/owl-carousel/owl.carousel.min.js')}"></script>

	<!-- Custom functions for this theme -->
	<script
		src="${resource(dir: 'atterraggio', file: 'js/functions.min.js')}"></script>
	<script
		src="${resource(dir: 'atterraggio', file: 'js/initialise-functions.js')}"></script>

	<!-- IE9 form fields placeholder fix -->
	<!--[if lt IE 9]>
    <script>contact_form_IE9_placeholder_fix();</script>
    <![endif]-->

	<!-- begin olark code -->
	<script data-cfasync="false" type='text/javascript'>
		/*<![CDATA[*/
		window.olark
				|| (function(c) {
					var f = window, d = document, l = f.location.protocol == "https:" ? "https:"
							: "http:", z = c.name, r = "load";
					var nt = function() {
						f[z] = function() {
							(a.s = a.s || []).push(arguments)
						};
						var a = f[z]._ = {}, q = c.methods.length;
						while (q--) {
							(function(n) {
								f[z][n] = function() {
									f[z]("call", n, arguments)
								}
							})(c.methods[q])
						}
						a.l = c.loader;
						a.i = nt;
						a.p = {
							0 : +new Date
						};
						a.P = function(u) {
							a.p[u] = new Date - a.p[0]
						};
						function s() {
							a.P(r);
							f[z](r)
						}
						f.addEventListener ? f.addEventListener(r, s, false)
								: f.attachEvent("on" + r, s);
						var ld = function() {
							function p(hd) {
								hd = "head";
								return [ "<",hd,"></",hd,"><", i,
										' onl' + 'oad="var d=', g,
										";d.getElementsByTagName('head')[0].",
										j, "(d.", h, "('script')).", k, "='",
										l, "//", a.l, "'", '"', "></",i,">" ]
										.join("")
							}
							var i = "body", m = d[i];
							if (!m) {
								return setTimeout(ld, 100)
							}
							a.P(1);
							var j = "appendChild", h = "createElement", k = "src", n = d[h]
									("div"), v = n[j](d[h](z)), b = d[h]
									("iframe"), g = "document", e = "domain", o;
							n.style.display = "none";
							m.insertBefore(n, m.firstChild).id = z;
							b.frameBorder = "0";
							b.id = z + "-loader";
							if (/MSIE[ ]+6/.test(navigator.userAgent)) {
								b.src = "javascript:false"
							}
							b.allowTransparency = "true";
							v[j](b);
							try {
								b.contentWindow[g].open()
							} catch (w) {
								c[e] = d[e];
								o = "javascript:var d=" + g
										+ ".open();d.domain='" + d.domain
										+ "';";
								b[k] = o + "void(0);"
							}
							try {
								var t = b.contentWindow[g];
								t.write(p());
								t.close()
							} catch (x) {
								b[k] = o
										+ 'd.write("'
										+ p().replace(/"/g,
												String.fromCharCode(92) + '"')
										+ '");d.close();'
							}
							a.P(2)
						};
						ld()
					};
					nt()
				})({
					loader : "static.olark.com/jsclient/loader0.js",
					name : "olark",
					methods : [ "configure", "extend", "declare", "identify" ]
				});
		/* custom configuration goes here (www.olark.com/documentation) */
		olark.identify('${grailsApplication.config.olark.key}');/*]]>*/
	</script>
	<noscript>
		<a href="https://www.olark.com/site/${grailsApplication.config.olark.key}/contact"
			title="Contact us" target="_blank">Questions? Feedback?</a> powered
		by <a href="http://www.olark.com?welcome"
			title="Olark live chat software">Olark live chat software</a>
	</noscript>
	<!-- end olark code -->

</body>
</html>
<%--
			<article id="negato" class="section-wrapper clearfix"
				data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg5.jpg')}">
				<div class="content-wrapper clearfix">
					<div class="col-sm-10 col-md-9 pull-right">

						<section class="feature-text">
							<h1>Informazione di sicurezza</h1>
							<p>
								<g:message code="springSecurity.denied.message" />
							</p>
							<p>
								<a href="${resource(dir: '/')}"
									class="link-scroll btn btn-outline-inverse btn-lg">Torna
									alla pagina principale</a>
							</p>
						</section>

					</div>
					<!-- .col-sm-10 -->
				</div>
				<!-- .content-wrapper -->
			</article> --%>
