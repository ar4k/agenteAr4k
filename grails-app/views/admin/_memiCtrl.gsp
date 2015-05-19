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
      $http.get("${createLink(controller:'admin',action:'listaMemi',absolute:'true')}")
    .success(function (response) {$scope.memi = response.memi;});
    
    $scope.nuovo=false;
    
    $scope.pannello=false;
    
    $scope.memefocus = '';
    
    $scope.titolo = 'meme';
    
  	$scope.dettagli = function(idmeme) {
    	$scope.titolo = idmeme;
    	$scope.memefocus = $filter('filter')($scope.memi, {idMeme: idmeme}, true);
    	if ($scope.memefocus.length) {
    		$scope.pannello = true;
    	}
      };
    
  });