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
  });