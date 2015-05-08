<div style="padding-top: 8px;">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading"
					ng-include="'${createLink(controller:'admin',action:'terminale',absolute:true)}'"></div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>etichetta</th>
									<th>descrizione</th>
									<th>uname</th>
									<th>utente</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="vaso in vasi">
									<td>{{vaso.etichetta}}</td>
									<td>{{vaso.stringa}}</td>
									<td>{{vaso.uname}}</td>
									<td ng-class="{'success': vaso.sudo,'danger': !vaso.sudo}">{{vaso.utente}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
