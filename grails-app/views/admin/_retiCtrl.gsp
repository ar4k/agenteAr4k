'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('RetiCtrl', function($scope, $http) {
    $http.get("${createLink(controller:'admin',action:'listaDataCenters',absolute:'true')}")
    .success(function (response) {$scope.datacenters = response.datacenters});
    
    $http.get("${createLink(controller:'documentazione',action:'rete.md',absolute:'true')}")
    .success(function (response) {$scope.retiHelp = response;});  
    
    $scope.focusDocumentazione=false;
    
    $scope.nuovo=false;
    
    $scope.pannello=false;
    
    $scope.titolo = 'nodo';
    
    $scope.focus = '';
    
    $scope.statoNodo = 'Problema nella gestione dello stato';
    
    $scope.dettagli = function(idNodo,datacenter) {
  			$scope.titolo = idNodo;
        	var link = "${createLink(controller:'admin',action:'nodo',absolute:'true')}?identificativo="+idNodo+'&datacenter='+datacenter;
        	$scope.pannello = true;
        	$http.get(link)
        		.success(function(response) {
					$scope.focus = response;
  			})
  				.error(function(data, status, headers, config) {
    			// called asynchronously if an error occurs
    			// or server returns response with an error status.
  			});
  	};
  });