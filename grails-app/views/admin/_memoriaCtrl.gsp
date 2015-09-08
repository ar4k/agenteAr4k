'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('MemoriaCtrl', function($scope, $http) {
    $http.get("${createLink(controller:'admin',action:'listaStore',absolute:'true')}")
    .success(function (response) {$scope.storedati = response.storedati});
    
    $http.get("${createLink(controller:'documentazione',action:'memoria.md',absolute:'true')}")
    .success(function (response) {$scope.memoriaHelp = response;});    
  
  	$scope.chiave = '';
  	$scope.valore = '';
  
    $scope.nuovo=false;
    
    $scope.focusDocumentazione=false;
    
    $scope.salva = function(chiave,valore) {
        	var link = "${createLink(controller:'admin',action:'salvaValoreKV',absolute:'true')}";
        	$http.post(link,{chiave:chiave,valore:valore})
        		.success(function(response) {
					    $http.get("${createLink(controller:'admin',action:'listaStore',absolute:'true')}")
  						.success(function (response) {$scope.storedati = response.storedati});
  			})
  				.error(function(data, status, headers, config) {
    			// called asynchronously if an error occurs
    			// or server returns response with an error status.
  			});
  	};
  	
  	$scope.cancella = function(chiave) {
        	var link = "${createLink(controller:'admin',action:'cancellaValoreKV',absolute:'true')}";
        	$http.post(link,{chiave:chiave})
        		.success(function(response) {
					    $http.get("${createLink(controller:'admin',action:'listaStore',absolute:'true')}")
  						.success(function (response) {$scope.storedati = response.storedati});
  			})
  				.error(function(data, status, headers, config) {
    			// called asynchronously if an error occurs
    			// or server returns response with an error status.
  			});
  	};
  	
  	$scope.modifica = function(chiave,valore) {
  			$scope.chiave = chiave;
  			$scope.valore = valore;
  			$scope.nuovo=true;
  	};
  	
  	$scope.salvaContestoinKV = function() {
        	var link = "${createLink(controller:'admin',action:'salvaContestoinKV',absolute:'true')}";
        	$http.get(link)
        		.success(function(response) {
					    $http.get("${createLink(controller:'admin',action:'listaStore',absolute:'true')}")
  						.success(function (response) {$scope.storedati = response.storedati});
  			})
  				.error(function(data, status, headers, config) {
    			// called asynchronously if an error occurs
    			// or server returns response with an error status.
  			});
  	};
  	
  	$scope.aggiornaDaMessaggio = function() {
    	    $http.get("${createLink(controller:'admin',action:'listaStore',absolute:'true')}")
    		.success(function (response) {$scope.storedati = response.storedati});
  	};
  });