'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('RicettariCtrl', function($scope, $http) {
    $http.get("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    .success(function (response) {$scope.ricettari = response.ricettari;});
    
    $scope.nuovo=false;
    
    $scope.pannello=false;
    
    $scope.nuovoricettario = function(ricettario) {
        $http.post("${createLink(controller:'admin',action:'aggiungiRicettario',absolute:'true')}", {ricettario:ricettario})
        .success(function(data, status, headers, config) {
    		    $http.get("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    			.success(function (response) {$scope.ricettari = response.ricettari;});
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
  });