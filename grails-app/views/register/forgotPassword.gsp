<html>

<head>
<title><g:message code='spring.security.ui.forgotPassword.title' /></title>
<meta name='layout' content='atterraggio' />
</head>

<body>
	<article id="forgotPassword" class="section-wrapper clearfix"
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_rocca.jpg')}">
		<div class="content-wrapper clearfix">

			<g:form action='forgotPassword' name="forgotPasswordForm"
				autocomplete='off'>

				<g:if test='${emailSent}'>
					<div class="col-xs-12 col-sm-12 col-md-9">
						<h2>Le istruzioni per resettare la password sono state
							inviate alla vostra email.</h2>
					</div>
				</g:if>

				<g:else>


					<h1>Procedura recupero password</h1>

					<g:if test="${flash.message}">
						<div class="col-xs-12 col-sm-12 col-md-12 lead" id="errorsDiv">
							${flash.message}
						</div>
					</g:if>


					<div class="contact-details col-xs-12 col-sm-12 col-md-7">

						<p class="text-justify">Inserire l'username associato alla
							password smarrita.</p>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-5">

						<div class="form-group">

							<label for="username">Username</label>
							<g:textField name="username" id="username"
								class="text-field form-control validate-field string"
								placeholder='Username' />
						</div>
						<div class="form-group">
							<g:submitButton class="btn btn-sm btn-outline-inverse"
								name="Richiedi reset password utenza" />
						</div>
					</div>
				</g:else>

			</g:form>
		</div>
	</article>


</body>
</html>
