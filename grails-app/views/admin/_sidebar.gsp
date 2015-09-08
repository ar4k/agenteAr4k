<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav in" id="side-menu">
			<!--  <sidebar-search></sidebar-search> -->
			<li ui-sref-active="active"><a ui-sref="dashboard.dashrossonet"><i
					class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>

			<li ui-sref-active="active"><a ui-sref="dashboard.oggetti"><i
					class="fa fa-linux fa-fw"></i> Vasi</a></li>

			<li ui-sref-active="active"><a ui-sref="dashboard.reti"><i
					class="fa fa-sitemap fa-fw"></i> Reti</a></li>

			<li ui-sref-active="active"><a ui-sref="dashboard.memi"><i
					class="fa fa-flask fa-fw"></i> Memi</a></li>

			<li ui-sref-active="active"><a ui-sref="dashboard.processi"><i
					class="fa fa-gears fa-fw"></i> Servizi</a></li>

			<li ui-sref-active="active"><a ui-sref="dashboard.memoria"><i
					class="fa fa-database fa-fw"></i> Dati</a></li>

			<li ui-sref-active="active"><a ui-sref="dashboard.ricettari"><i
					class="fa fa-book fa-fw"></i> Ricettari</a></li>
			<g:if test="${grafica.sviluppo==true}">
				<li ui-sref-active="active"><a ui-sref="dashboard.api"><i
						class="fa fa-file-text-o fa-fw"></i> Documentazione API Ar4k</a></li>
				<g:if env="development">
					<li ui-sref-active="active"><a ui-sref="dashboard.rossonet"><i
							class="fa fa-cubes fa-fw"></i> Grails Console</a></li>
				</g:if>

				<li ui-sref-active="active"><a ui-sref="dashboard.utenti"><i
						class="fa fa-users fa-fw"></i> Configura Utenti</a></li>

				<li ui-sref-active="active"><a href="http://www.rossonet.org/archives/650" target="_guida"><i
						class="fa fa-university fa-fw"></i> Sviluppo Memi</a></li>

				<li ng-class="{active: collapseVar==1}"><a href=""
					ng-click="check(1)"><i class="fa fa-graduation-cap fa-fw"></i>
						Funzionalità Interfaccia<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level" collapse="collapseVar!=1">
						<li ui-sref-active="active"><a ui-sref="dashboard.home"><i
								class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
						<li ui-sref-active="active"><a ui-sref="dashboard.chart"><i
								class="fa fa-bar-chart-o fa-fw"></i> Charts<span></span></a></li>
						<li ui-sref-active="active"><a ui-sref="dashboard.table"><i
								class="fa fa-table fa-fw"></i> Tables</a></li>
						<li ui-sref-active="active"><a ui-sref="dashboard.form"><i
								class="fa fa-edit fa-fw"></i> Forms</a></li>
						<li ng-class="{active: multiCollapseVar==2}">{{dropDown}} <a
							href="" ng-click="multiCheck(2)"><i
								class="fa fa-wrench fa-fw"></i> UI Elements<span
								class="fa arrow"></span></a>
							<ul class="nav nav-third-level" collapse="multiCollapseVar!=2">
								<li ui-sref-active="active"><a
									ui-sref="dashboard.panels-wells">Panels and Wells</a></li>
								<li ui-sref-active="active"><a ui-sref="dashboard.buttons">Buttons</a>
								</li>
								<li ui-sref-active="active"><a
									ui-sref="dashboard.notifications">Notifications</a></li>
								<li ui-sref-active="active"><a
									ui-sref="dashboard.typography">Typography</a></li>
								<li ui-sref-active="active"><a ui-sref="dashboard.icons">
										Icons</a></li>
								<li ui-sref-active="active"><a ui-sref="dashboard.grid">Grid</a>
								</li>
							</ul> <!-- /.nav-second-level -->
						</li>
						<li ng-class="{active: multiCollapseVar==3}"><a href=""
							ng-click="multiCheck(3)"><i class="fa fa-sitemap fa-fw"></i>
								Multi-Level Dropdown<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level" collapse="multiCollapseVar!=3">
								<li><a href="">Second Level Item</a></li>
								<li><a href="">Second Level Item</a></li>
								<!-- <li ng-init="third=!third"
								ng-class="{active: multiCollapseVar==3}"><a href=""
								ng-click="multiCheck(3)">Third Level <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level" collapse="multiCollapseVar!=3">
									<li><a href="">Third Level Item</a></li>
									<li><a href="">Third Level Item</a></li>
									<li><a href="">Third Level Item</a></li>
									<li><a href="">Third Level Item</a></li>

								</ul></li>
								-->
							</ul> <!-- /.nav-second-level --></li>
						<li ng-class="{active:multiCollapseVar==4}"><a href=""
							ng-click="multiCheck(4)"><i class="fa fa-files-o fa-fw"></i>
								Sample Pages<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level" collapse="multiCollapseVar!=4">
								<li ng-class="{active: selectedMenu=='blank'}"><a
									ui-sref="dashboard.blank" ng-click="selectedMenu='blank'">Blank
										Page</a></li>
								<li><a ui-sref="login">Login Page</a></li>
							</ul> <!-- /.nav-second-level --></li>
					</ul></li>
			</g:if>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
