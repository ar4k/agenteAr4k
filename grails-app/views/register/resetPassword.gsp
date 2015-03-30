<html>

<head>
<title><g:message code='spring.security.ui.resetPassword.title' /></title>
<meta name='layout' content='atterraggio' />
</head>

<body>

	<article id="resetPassword" class="section-wrapper clearfix"
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_rocca.jpg')}">
		<div class="content-wrapper clearfix">

			<g:form action='resetPassword' name='resetPasswordForm'
				autocomplete='off'>
				<g:hiddenField name='t' value='${token}' />

				<h1 class="section-title">Cambia password AR4K</h1>
				<g:if test="${flash.message}">
					<div class="col-xs-12 col-sm-12 col-md-12 lead" id="errorsDiv">
						${flash.message}
					</div>
				</g:if>

				<g:hasErrors bean="${command}">
					<div class="col-xs-12 col-sm-12 col-md-12" id="errorsDiv">
						<g:renderErrors bean="${command}" as="list" />
						<ul class="list-inline"></ul>
					</div>
				</g:hasErrors>

				<%-- CONTACT DETAILS --%>
				<div
					class="contact-details contact-details col-xs-12 col-sm-12 col-md-3">
					<p class="text-justify">Immetere e confermare la nuova
						password. La password deve contenere almeno otto caraterri tra cui
						un numero e un simbolo.</p>
				</div>
				<%-- END: CONTACT DETAILS --%>
				<div class="col-xs-12 col-sm-12 col-md-5">


					<div class="form-group">
						<label for="password">Password</label>
						<g:passwordField name='password' id="bassword" bean="${command}"
							value="${command.password}"
							class="text-field form-control validate-field required"
							placeholder='Password (almeno 6 caratteri, un numero e un simbolo)' />
					</div>
					<div class="form-group">
						<label for="password2">Ripeti password</label>
						<g:passwordField name='password2' id='password2' bean="${command}"
							value="${command.password2}"
							class="text-field form-control validate-field required"
							placeholder='Ripeti la password' />
					</div>
					<div class="form-group">

						<g:submitButton class="btn btn-sm btn-outline-inverse"
							name="Cambia password AR4K" />
					</div>
				</div>




			</g:form>
		</div>
	</article>



</body>
</html>
