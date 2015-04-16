<head>
<meta name='layout' content='atterraggio' />
<title>Create or Link Account</title>

</head>

<body>

	<article id="askToLink" class="section-wrapper clearfix"
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_rocca.jpg')}">
		<div class="content-wrapper clearfix">


			<g:if test='${flash.error}'>
				<div class="col-xs-12 col-sm-12 col-md-9">
					<h2>
						${flash.error}
					</h2>
				</div>
			</g:if>

			<h1 class="section-title">
				<g:message code="springSecurity.oauth.registration.link.not.exists"
					default="No user was found with this account."
					args="[session.springSecurityOAuthToken.providerName]" />
			</h1>

			<g:hasErrors bean="${createAccountCommand}">
				<div class="col-xs-12 col-sm-12 col-md-9" id="errorsDiv">

					<g:renderErrors bean="${createAccountCommand}" as="list" />

				</div>
			</g:hasErrors>

			<g:hasErrors bean="${linkAccountCommand}">
				<div class="col-xs-12 col-sm-12 col-md-9" id="errorsDiv">

					<g:renderErrors bean="${linkAccountCommand}" as="list" />

				</div>
			</g:hasErrors>

			<div class="col-xs-12 col-sm-12 col-md-12">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#createAccount" data-toggle="tab">Crea
							un nuovo utente AR4K</a></li>
					<li><a href="#linkAccount" data-toggle="tab">Collegati a
							un utente AR4K esistente</a></li>
				</ul>

				<div class="tab-content">

					<div class="tab-pane fade in active col-md-12" id="createAccount">
						<div class="contact-details col-xs-12 col-sm-12 col-md-7">
							<p class="text-justify" style="text-align: justify;">Scegliere il proprio nominativo sul
								sistema e un codice segreto. Il codice segreto servirà per
								aggiungere altri fonti di autenticazione alla vostra
								configurazione AR4K. Potete, per esempio, farvi riconoscere dal
								sistema accedendo sia da Facebook che Google. Per farlo, al
								primo accesso, con Facebook in questo esempio, sceglierete il
								nome utente e il codice segreto per creare un nuovo profilo su
								AR4K. Finita la configurazione dell'utente associato a Facebook,
								uscite dal sistema effettuando un logout; accedete con Google e,
								invece di creare un nuovo profilo AR4K, vi collegate all'account
								precedentemente creato utilizzando il nome utente e il codice
								segreto inseriti precedentemente.</p>
							<p class="text-justify">La chiave di sicurezza deve contenere
								almeno otto caratteri tra cui un numero e un simbolo tra i seguenti: ! @ # $ % ^ &</p>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-5">
							<g:form action="createAccount" method="post" autocomplete="off"
								style="padding-top:1em;">

								<!--  
							<h2>
								<g:message
									code="springSecurity.oauth.registration.create.legend"
									default="Create a new account" />
							</h2>
-->

								<div class="form-group">
									<label for="username">Username</label>
									<g:textField name='username' bean="${createAccountCommand}"
										placeholder="Username (unico su AR4K)" id='username'
										class="text-field form-control validate-field string" />

								</div>

								<div class="form-group">
									<label for="password1">Chiave sicurezza</label>
									<g:passwordField name='password1' bean="${createAccountCommand}"
										placeholder="Chiave sicurezza (almeno 6 caratteri, un numero e un simbolo)"
										id='password1'
										class="text-field form-control validate-field required" />

								</div>


								<div class="form-group">
									<label for="password2">Ripeti chiave</label>
									<g:passwordField name='password2' bean="${createAccountCommand}"
										placeholder="Ripeti la chiave di sicurezza" id='password2'
										class="text-field form-control validate-field required" />

								</div>
								<div class="form-group">
									<g:submitButton class="btn btn-sm btn-outline-inverse"
										name="Crea utente AR4K" />
								</div>
							</g:form>
						</div>

					</div>


					<div class="tab-pane fade col-md-12" id="linkAccount">
						<div class="contact-details col-xs-12 col-sm-12 col-md-7">
							<p class="text-justify" style="text-align: justify;">E' la prima volta che entrate su
								AR4K. Se avete già un utenza potete collegarvi ad essa
								utilizzando il nome utente e il codice segreto (o password).
								Altrimenti createne una selezionando il pannello "Crea nuova
								utenza AR4K".</p>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-5">
							<g:form action="linkAccount" method="post" autocomplete="off"
								style="padding-top:1em;">

								<div class="form-group">
									<label for="username">Username</label>
									<g:textField name='username' id='username' bean="${linkAccountCommand}"
										placeholder="Username"
										class="text-field form-control validate-field string" />
								</div>
								<div class="form-group">
									<label for="password">Password</label>
									<g:passwordField name='password' id='password' bean="${linkAccountCommand}"
										placeholder="Chiave sicurezza"
										class="text-field form-control validate-field required" />
								</div>
								<div class="form-group">
									<g:submitButton class="btn btn-sm btn-outline-inverse"
										name="Collega all'utente AR4K esistente" />
								</div>

							</g:form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</article>
</body>
</html>

<%-- 		<article id="entra" class="section-wrapper clearfix"
					data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_rocca.jpg')}">
					<div class="content-wrapper clearfix">

						<h1 class="section-title">
							<g:message code='spring.security.ui.login.signin' />
						</h1>
						<!--  
				<p class="text-error">
					<g:message code="springSecurity.denied.message" />
				</p>
				-->

						<!-- CONTACT DETAILS -->
						<div class="contact-details col-sm-5 col-md-3">
							<p>Utilizzando il nostro portale internet acconsentite a
								farci rilevare determinati dati. Da parte nostra ci impegniamo a
								prelevare e conservare soltanto le informazioni strettamente
								necessarie e a proteggere sempre la vostra privacy.</p>
						</div>
						<!-- END: CONTACT DETAILS -->

						<!-- CONTACT FORM -->
						<div class="col-sm-7 col-md-9">
							<!-- IMPORTANT: change the email address at the top of the assets/php/mail.php file to the email address that you want this form to send to -->

							<form action='${postUrl}'
								class="form-style validate-form clearfix" method='POST'
								id="loginForm" name="loginForm" autocomplete='off' role="form">
								<!-- form left col -->
								<div class="col-md-6">
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
												code='spring.security.ui.login.rememberme' /></label>
									</div>
									<div class="form-group">
										<img
											src="${resource(dir: 'atterraggio', file: 'images/theme_images/loader-form.GIF')}"
											class="form-loader">
										<s2ui:submitButton class="btn btn-sm btn-outline-inverse"
											elementId='loginButton' form='loginForm'
											messageCode='spring.security.ui.login.login' />

									</div>
									<div class="form-group">
										<s2ui:linkButton class="btn" elementId='register'
											controller='register'
											messageCode='spring.security.ui.login.register' />
									</div>
									<div class="form-group">
										<g:link controller='register' class="btn"
											action='forgotPassword'>
											<g:message code='spring.security.ui.login.forgotPassword' />
										</g:link>
									</div>

								</div>
								<!-- end: form left col -->
								<div class="col-md-3">
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
								<div class="col-md-3">
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
							</form>
						</div>
						<!-- end: CONTACT FORM -->
					</div>
					<!-- .content-wrapper -->
				</article> --%>
