<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<title><g:layoutTitle default="AR4K Augmented Reality For Key" /></title>
<base href="${g.createLink(absolute:true,uri:'/')}" />
<meta name="author" content="Rossonet s.c.a r.l (Imola) - Italy">
<meta name="description"
	content="Template applicativo per App in Grails AngularJS Twitter BootStrap con supporto Kettle SSH Spring Auth">
<meta name="viewport" content="width=device-width">

<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'brain.png')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'brain.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'brain.png')}">


<!-- In sviluppo non usiamo i minificati -->
<g:if env="development">
	<link rel="stylesheet"
		href="admin/bower_components/bootstrap/dist/css/bootstrap.css" />

	<link rel="stylesheet" href="admin/app/styles/main.css">
	<link rel="stylesheet" href="admin/app/styles/sb-admin-2.css">
	<link rel="stylesheet" href="admin/app/styles/timeline.css">
	<link rel="stylesheet"
		href="admin/bower_components/metisMenu/dist/metisMenu.css">
	<link rel="stylesheet"
		href="admin/bower_components/angular-loading-bar/build/loading-bar.css">
	<link rel="stylesheet"
		href="admin/bower_components/font-awesome/css/font-awesome.css"
		type="text/css">

	<script src="admin/bower_components/jquery/dist/jquery.js"></script>
	<script src="admin/bower_components/angular/angular.js"></script>
	<script src="admin/bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script
		src="admin/bower_components/angular-ui-router/release/angular-ui-router.js"></script>
	<script src="admin/bower_components/json3/lib/json3.js"></script>
	<script src="admin/bower_components/oclazyload/dist/ocLazyLoad.js"></script>
	<script
		src="admin/bower_components/angular-loading-bar/build/loading-bar.js"></script>
	<script
		src="admin/bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
	<script src="admin/bower_components/metisMenu/dist/metisMenu.js"></script>
</g:if>
<g:if env="production">
	<link rel="stylesheet"
		href="admin/bower_components/bootstrap/dist/css/bootstrap.min.css" />

	<link rel="stylesheet" href="admin/app/styles/main.css">
	<link rel="stylesheet" href="admin/app/styles/sb-admin-2.css">
	<link rel="stylesheet" href="admin/app/styles/timeline.css">
	<link rel="stylesheet"
		href="admin/bower_components/metisMenu/dist/metisMenu.min.css">
	<link rel="stylesheet"
		href="admin/bower_components/angular-loading-bar/build/loading-bar.min.css">
	<link rel="stylesheet"
		href="admin/bower_components/font-awesome/css/font-awesome.min.css"
		type="text/css">

	<script src="admin/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="admin/bower_components/angular/angular.min.js"></script>
	<script src="admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="admin/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
	<script src="admin/bower_components/json3/lib/json3.min.js"></script>
	<script src="admin/bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
	<script
		src="admin/bower_components/angular-loading-bar/build/loading-bar.min.js"></script>
	<script
		src="admin/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
	<script src="admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
</g:if>



<!-- Contenuti pagina header-->
<g:layoutHead />
<!-- Fine contenuti pagina header-->

<script>
       (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
       (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
       m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
       })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
       ga('create', '${grailsApplication.config.google.analytics}');
       ga('send', 'pageview');
    </script>
<!-- Custom CSS -->

<!-- Custom Fonts -->

<!-- Morris Charts CSS -->
<!-- <link href="styles/morrisjs/morris.css" rel="stylesheet"> -->


</head>

<body>

	<!-- Contenuti pagina-->
	<g:layoutBody />
	<!-- Fine contenuti pagina-->

	<!-- begin olark code -->
	<script data-cfasync="false" type='text/javascript'>
		/*<![CDATA[*/window.olark
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
		olark.identify('${grailsApplication.config.olark.key}');
		olark.configure('system.is_single_page_application', true);
		<sec:ifLoggedIn>
		olark('api.visitor.updateFullName', {fullName: '<sec:loggedInUserInfo field="username"/>'});
		</sec:ifLoggedIn>
		//olark('api.visitor.updateEmailAddress', {emailAddress: ''});
		//olark('api.visitor.updateCustomFields', {facebookAccount: ''});
		//olark.configure('box.corner_position', 'TR');
		/*]]>*/
		
		<g:if test="${messaggioOlark != null}">
		olark('api.chat.sendMessageToVisitor', {body: "${messaggioOlark.toString()}"});
		</g:if>

		<g:if test="${messaggioOlark == null}">
		olark('api.box.hide');
		</g:if>
	</script>
	<noscript>
		<a
			href="https://www.olark.com/site/${grailsApplication.config.olark.key}/contact"
			title="Contact us" target="_blank">Questions? Feedback?</a> powered
		by <a href="http://www.olark.com?welcome"
			title="Olark live chat software">Olark live chat software</a>
	</noscript>
	<!-- end olark code -->

</body>

</html>
