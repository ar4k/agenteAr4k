'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('MemiCtrl', function($scope,$http,$filter) {
      $http.post("${createLink(controller:'admin',action:'listaMemi',absolute:'true')}")
    .success(function (response) {$scope.memi = response.memi;});
    
    $scope.nuovo=false;
    
    $scope.pannello=false;
    
    $scope.memefocus = '';
    
    $scope.titolo = 'meme';
    
    $scope.focus = '';
    
    $scope.eseguimetodo = function(idmetodo) {
    	$http.post("${createLink(controller:'admin',action:'eseguiMetodo',absolute:'true')}", {idmetodo:idmetodo,dati:''})
        .success(function(response) {
    		    $http.get("${createLink(controller:'admin',action:'listaMemi',absolute:'true')}")
    			.success(function(response) {$scope.memi = response.memi;});
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
    
  	$scope.dettagli = function(idmeme) {
    	$scope.memefocus = $filter('filter')($scope.memi, {idMeme: idmeme}, true);
    	if ($scope.memefocus.length) {
  			$scope.titolo = $scope.memefocus[0].etichetta;
        	$scope.focus = "${createLink(controller:'admin',action:'maschera',absolute:'true')}?idMeme="+idmeme;
        	$scope.pannello = true;
        	}
  		};
  });