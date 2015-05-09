<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">
	<div class="navbar-header">

		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Togli barra di navigazione</span> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" style="padding: 0px; width: 250px;"
			href="${createLink(controller:'admin',absolute:'true')}">
			<div style="text-align: left; padding: 0px; width: 250px; height: 50px;">
				<img
					src="${grafica?.immagineLogo?.toString()?:resource(dir:'images',file:'75.png')}"
					height="40px" style="padding-top: 5px; padding-left: 20px;" />
			</div>
		</a>

	</div>
	<header-notification></header-notification>

	<sidebar></sidebar>
</nav>