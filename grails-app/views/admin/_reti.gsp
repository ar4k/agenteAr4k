<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-sitemap fa-3" /> Reti gestite (datacenters Consul)
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Una <strong>rete</strong> AR4K corrisponde ad un datacenter Consul.
					<p>
				</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Datacenter</th>
										<th class="text-right">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="datacenter in datacenters" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>{{datacenter}}</td>
										<td class="text-right">
										</td>
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