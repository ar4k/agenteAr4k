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
		<div class="col-lg-12">
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
						<div class="panel-heading text-center">HYPERVISOR DISPONIBILI - Servizi Cloud -</div>
						<div class="panel-body">
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
								lacus adipiscing, posuere lectus et, fringilla augue.</p>
						</div>
					</div>
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Datacenter</th>
										<th class="text-right">Nodi</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="datacenter in datacenters"
										ng-class-odd="'dispari'" ng-class-even="'pari'">
										<td>{{datacenter.datacenter}}</td>
										<td class="text-right"><i
											ng-repeat="nodo in datacenter.nodi">
												<button style="margin: 0.1em;"
													ng-class="nodo.stato.value[0].status.name == 'PASSING'?'btn btn-success btn-xs':'btn btn-danger btn-xs'"
													type="button"
													ng-click="$parent.dettagli(nodo.nodo.node,datacenter.datacenter)"
													tooltip-placement="top"
													tooltip="visualizza i dettagli del nodo.">
													<i class="fa fa-info-circle"></i> {{nodo.nodo.node}}
													({{nodo.nodo.address}})
												</button>
										</i></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>