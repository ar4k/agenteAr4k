<div class="row">
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
							<i class="fa fa-comment"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Una <strong>rete</strong> AR4K corrisponde ad un datacenter
						Consul.
					<p>
					<div marked="retiHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<button class="btn btn-outline btn-primary" ng-hide="nuovo"
						type="button" ng-click="" tooltip-placement="bottom"
						tooltip="visualizza i nodi sulla mappa geografica.">MAPPA
						GEOGRAFICA NODI</button>
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
													class="btn btn-success btn-xs" type="button"
													ng-click="$parent.dettagli(meme.idMeme)"
													tooltip-placement="top"
													tooltip="visualizza i dettagli del nodo.">
													<i class="fa fa-info-circle"></i> {{nodo.address}}
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