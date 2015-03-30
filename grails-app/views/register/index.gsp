<html>

<head>
<meta name='layout' content='atterraggio' />
<title><g:message code='spring.security.ui.register.title' /></title>
</head>

<body>

	<article id="register" class="section-wrapper clearfix"
		data-custom-background-img="${resource(dir: 'atterraggio', file: 'images/other_images/bg_rocca.jpg')}">
		<div class="content-wrapper clearfix">

			<g:form controller='register' action='register' name='registerForm'
				autocomplete="off" style="padding-top:1em;">


				<g:if test='${emailSent}'>
					<div class="col-xs-12 col-sm-12 col-md-9">
						<h2>
							<g:message
								code='A breve riceverete una email con la procedura di attivazione' />
						</h2>
					</div>
				</g:if>
				<g:else>

					<h1 class="section-title">
						<g:message code="Registra un nuovo profilo su AR4K" />
					</h1>

					<g:if test="${flash.message}">
						<div class="col-xs-12 col-sm-12 col-md-12 lead" id="errorsDiv">
							${flash.message}
						</div>
					</g:if>

					<g:hasErrors bean="${command}">
						<div class="col-xs-12 col-sm-12 col-md-12" id="errorsDiv">
							<g:renderErrors bean="${command}" as="list" />
						</div>
					</g:hasErrors>

					<div class="contact-details col-xs-12 col-sm-12 col-md-7">

						<p class="text-justify">Immetere i dati richiesti. La password
							deve contenere almeno otto caratteri tra cui un numero e un
							simbolo tra i seguenti: ! @ # $ % ^ &</p>

						<p style="font-size: 9px;" class="text-justify">PRIVACY POLICY
							for www.ar4k.eu: At Ar4k we are committed to safeguarding and
							preserving the privacy of our visitors. This Privacy Policy
							explains what happens to any personal data that you provide to
							us, or that we collect from you whilst you visit our site. We do
							update this Policy from time to time so please do review this
							Policy regularly. Information We Collect In running and
							maintaining our website we may collect and process the following
							data about you: 1. Information about your use of our site
							including details of your visits such as pages viewed and the
							resources that you access. Such information includes traffic
							data, location data and other communication data. 2. Information
							provided voluntarily by you. For example, when you register for
							information or make a purchase. 3. Information that you provide
							when you communicate with us by any means. Use of Cookies.
							Cookies provide information regarding the computer used by a
							visitor. We may use cookies where appropriate to gather
							information about your computer fin order to assist us in
							improving our website. We may gather information about your
							general internet use by using the cookie. Where used, these
							cookies are downloaded to your computer and stored on the
							computer’s hard drive. Such information will not identify you
							personally. It is statistical data. This statistical data does
							not identify any personal details whatsoever You can adjust the
							settings on your computer to decline any cookies if you wish.
							This can easily be done by activating the reject cookies setting
							on your computer. Our advertisers may also use cookies, over
							which we have no control. Such cookies (if used) would be
							downloaded once you click on advertisements on our website. Use
							of Your Information We use the information that we collect from
							you to provide our services to you. In addition to this we may
							use the information for one or more of the following purposes: 1.
							To provide information to you that you request from us relating
							to our products or services. 2. To provide information to you
							relating to other products that may be of interest to you. Such
							additional information will only be provided where you have
							consented to receive such information. 3. To inform you of any
							changes to our website, services or goods and products. If you
							have previously purchased goods or services from us we may
							provide to you details of similar goods or services, or other
							goods and services, that you may be interested in. Where your
							consent has been provided in advance we may allow selected third
							parties to use your data to enable them to provide you with
							information regarding unrelated goods and services which we
							believe may interest you. Where such consent has been provided it
							can be withdrawn by you at any time. Storing Your Personal Data
							In operating our website it may become necessary to transfer data
							that we collect from you to locations outside of the European
							Union for processing and storing. By providing your personal data
							to us, you agree to this transfer, storing or processing. We do
							our upmost to ensure that all reasonable steps are taken to make
							sure that your data is treated stored securely. Unfortunately the
							sending of information via the internet is not totally secure and
							on occasion such information can be intercepted. We cannot
							guarantee the security of data that you choose to send us
							electronically, Sending such information is entirely at your own
							risk. Disclosing Your Information We will not disclose your
							personal information to any other party other than in accordance
							with this Privacy Policy and in the circumstances detailed below:
							i. In the event that we sell any or all of our business to the
							buyer. ii. Where we are legally required by law to disclose your
							personal information. iii. To further fraud protection and reduce
							the risk of fraud. Third Party Links On occasion we include links
							to third parties on this website. Where we provide a link it does
							not mean that we endorse or approve that site’s policy towards
							visitor privacy. You should review their privacy policy before
							sending them any personal data. Access to Information In
							accordance with the Data Protection Act 1998 you have the right
							to access any information that we hold relating to you. Please
							note that we reserve the right to charge a fee of £10 to cover
							costs incurred by us in providing you with the information.
							Contacting Us Please do not hesitate to contact us regarding any
							matter relating to this Privacy.</p>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-5">

						<div class="form-group">

							<label for="username">Username</label>
							<g:textField name='username' id='username' bean="${command}"
								value="${command.username}"
								class="text-field form-control validate-field string"
								placeholder='Username (unico su AR4K)' />

						</div>
						<div class="form-group">
							<label for="email">Email</label>
							<g:textField name='email' id='email' bean="${command}"
								value="${command.email}"
								class="text-field form-control validate-field email"
								placeholder='Indirizzo email da confermare' />
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<g:passwordField name='password' id="bassword" bean="${command}"
								value="${command.password}"
								class="text-field form-control validate-field required"
								placeholder='Password (almeno 6 caratteri, un numero e un simbolo)' />
						</div>
						<div class="form-group">
							<label for="password2">Ripeti password</label>
							<g:passwordField name='password2' id='password2'
								bean="${command}" value="${command.password2}"
								class="text-field form-control validate-field required"
								placeholder='Ripeti la password' />
						</div>
						<div class="form-group">

							<g:submitButton class="btn btn-sm btn-outline-inverse"
								name="Crea nuovo utente AR4K" />
						</div>
					</div>

				</g:else>

			</g:form>

		</div>
	</article>

</body>
</html>

<%-- <html>

<head>
	<meta name='layout' content='register'/>
	<title><g:message code='spring.security.ui.register.title'/></title>
</head>

<body>

<p/>

<s2ui:form width='650' height='300' elementId='loginFormContainer'
           titleCode='spring.security.ui.register.description' center='true'>

<g:form action='register' name='registerForm'>

	<g:if test='${emailSent}'>
	<br/>
	<g:message code='spring.security.ui.register.sent'/>
	</g:if>
	<g:else>

	<br/>

	<table>
	<tbody>

		<s2ui:textFieldRow name='username' labelCode='user.username.label' bean="${command}"
                         size='40' labelCodeDefault='Username' value="${command.username}"/>

		<s2ui:textFieldRow name='email' bean="${command}" value="${command.email}"
		                   size='40' labelCode='user.email.label' labelCodeDefault='E-mail'/>

		<s2ui:passwordFieldRow name='password' labelCode='user.password.label' bean="${command}"
                             size='40' labelCodeDefault='Password' value="${command.password}"/>

		<s2ui:passwordFieldRow name='password2' labelCode='user.password2.label' bean="${command}"
                             size='40' labelCodeDefault='Password (again)' value="${command.password2}"/>

	</tbody>
	</table>

	<s2ui:submitButton elementId='create' form='registerForm' messageCode='spring.security.ui.register.submit'/>

	</g:else>

</g:form>

</s2ui:form>

<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

</body>
</html>
 --%>
