<div class="row aggiorna-su-messaggio">
	<div style="margin-top: 5px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-gears fa-3" /> Servizi (nodi/servizi Consul)
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui servizi.">
							<i class="fa fa-question"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>servizio</strong> AR4K corrisponde ad un'unità di
						calcolo/servizio gestita dalla rete Consul.
					<p>
					<div marked="serviziHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<div class="container-fluid">
						<div
							class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-lg-offset-3 col-md-offset-2 col-sm-offset-1 form-group input-group div4-padding">
							<input placeholder="ricerca testuale" ng-model="queryRicerca"
								class="form-control" /><span class="input-group-btn">
								<button class="btn btn-default" type="button" ng-click="queryRicerca=''">
									<i class="fa fa-times"></i>
								</button>
							</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-12 col-sm-4 col-md-2 col-lg-2 div4-padding"><h4>Id</h4></div>
							<div class="col-xs-6 col-sm-4 col-md-2 col-lg-2 div4-padding"><h4>Indirizzo</h4></div>
							<div class="col-xs-6 col-sm-4 col-md-2 col-lg-2 div4-padding"><h4>Porta</h4></div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 div4-padding"><h4>Servizio</h4></div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 div4-padding"><h4>Tags</h4></div>
							<div
								class="col-xs-6 col-sm-6 col-md-4 col-lg-4 div4-padding"><h4>Stato</h4></div>
							<div
								class="col-xs-6 col-sm-6 col-md-2 col-lg-2 text-right div4-padding"><h4>Azioni</h4></div>
						</div>
						<div ng-repeat="processo in processi | filter:queryRicerca"
							ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'"
							ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
							<div class="col-xs-12 col-sm-4 col-md-2 col-lg-2 div4-padding">{{processo.processo.id}}</div>
							<div class="col-xs-6 col-sm-4 col-md-2 col-lg-2 div4-padding">{{processo.processo.address}}</div>
							<div class="col-xs-6 col-sm-4 col-md-2 col-lg-2 div4-padding">{{processo.processo.port}}</div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 div4-padding">{{processo.processo.service}}</div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 div4-padding">
							<!--  -->&nbsp;
								<p ng-repeat="tag in processo.processo.tags">{{tag}}</p>
							</div>
							<div
								class="col-xs-6 col-sm-6 col-md-4 col-lg-4 div4-padding">{{processo.stato}}</div>
							<div
								class="col-xs-6 col-sm-6 col-md-2 col-lg-2 text-right div4-padding">
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-info btn-xs" type="button"
									ng-click="" tooltip-placement="top"
									tooltip="gestisci i link e i qr per questo servizio.">
									<i class="fa fa-qrcode"></i>
								</button>
								<button style="margin: 0.1em;" class="btn btn-circle btn-xs"
									type="button" ng-click="" tooltip-placement="top"
									tooltip="copia link servizio.">
									<i class="fa fa-link"></i>
								</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>