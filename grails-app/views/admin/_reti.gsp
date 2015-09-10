<div aria-hidden="false" aria-labelledby="Meme" role="dialog"
	tabindex="-1" id="reteModal" class="modal fade in" ng-show="pannello"
	style="display: block; padding-right: 13px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal"
					ng-click="pannello=false" class="close" type="button">×</button>
				<h4 id="reteModalLabel" class="modal-title">{{titolo}}</h4>
			</div>
			<div class="modal-body">
				<p>Indirizzo IP: {{focus.nodo.value.node.address}}</p>
				<div ng-repeat="servizio in focus.nodo.value.services"
					class="well well-sm">
					<div>
						<i class="fa fa-gears fa-fw"></i>({{servizio.id}})
						{{servizio.service}} porta: {{servizio.port}} tags:
						<p ng-repeat="tag in servizio.tags"">{{tag}}</p>
					</div>
				</div>
				<div class="panel panel-red text-center">
					<p ng-repeat="stato in focus.stato.value">
						{{stato.checkId}} - <b>{{stato.name}} => {{stato.status.name}}</b>
						{{stato.output}}
					</p>
				</div>
				<div class="panel text-right">
					<button class="btn btn-circle btn-success btn-xs" type="button"
						ng-click="scansiona(focus.nodo.value.node.address)"
						tooltip-placement="bottom"
						tooltip="scansiona il nodo per trovare servizi conosciuti">
						<i class="fa fa-steam"></i>
					</button>

					<button class="btn btn-circle btn-warning btn-xs" type="button"
						ng-click="scansionarete(focus.nodo.value.node.address)"
						tooltip-placement="bottom"
						tooltip="scansiona una rete da questo nodo per identificare i servizi">
						<i class="fa fa-share-alt"></i>
					</button>

					<button class="btn btn-circle btn-danger btn-xs" type="button"
						ng-click="eseguisunodo(focus.nodo.value.node.address)"
						tooltip-placement="bottom"
						tooltip="esegui un comando su questo nodo">
						<i class="fa fa-wrench"></i>
					</button>
				</div>
			</div>
			<div class="modal-footer">
				<button data-dismiss="modal" ng-click="pannello=false"
					class="btn btn-default" type="button">Chiudi</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div class="row aggiorna-su-messaggio">
	<div style="margin-top: 5px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-sitemap fa-3" /> Reti gestite (datacenters
						Consul)
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sulle reti.">
							<i class="fa fa-question"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Una <strong>rete</strong> AR4K corrisponde ad un datacenter
						Consul.
					<p>
					<div marked="retiHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<div class="panel panel-green">
						<div class="panel-heading text-center">HYPERVISOR
							DISPONIBILI - Servizi Cloud -</div>
						<div class="panel-body">
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
								lacus adipiscing, posuere lectus et, fringilla augue.</p>
						</div>
					</div>
					<div class="container-fluid">

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 div4-padding"><h4>Datacenter</h4></div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-right div4-padding"><h4>Nodi</h4></div>
						</div>

						<div ng-repeat="datacenter in datacenters"
							ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'" ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 div4-padding">{{datacenter.datacenter}}</div>
							<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-right div4-padding">
								<i ng-repeat="nodo in datacenter.nodi">
									<button style="margin: 0.1em;"
										ng-class="nodo.stato.value[0].status.name == 'PASSING'?'btn btn-success btn-xs':'btn btn-danger btn-xs'"
										type="button"
										ng-click="$parent.dettagli(nodo.nodo.node,datacenter.datacenter)"
										tooltip-placement="top"
										tooltip="visualizza i dettagli del nodo.">
										<i class="fa fa-info-circle"></i> {{nodo.nodo.node}}
										({{nodo.nodo.address}})
									</button>
								</i>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
