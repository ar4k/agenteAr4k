'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('OggettiCtrl', function($scope, $http) {
    $http.get("${createLink(controller:'admin',action:'listaVasi',absolute:'true')}")
    .success(function (response) {$scope.vasi = response.vasi;});
  
    $scope.nuovo=false;
    
    $scope.nuovovaso = function(vaso) {
        $http.post("${createLink(controller:'admin',action:'aggiungiVaso',absolute:'true')}", {vaso:vaso})
        .success(function(data, status, headers, config) {
    		    $http.get("${createLink(controller:'admin',action:'listaVasi',absolute:'true')}")
    			.success(function (response) {$scope.vasi = response.vasi;});
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
  });