'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description # MainCtrl Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller('OggettoCtrl',function($scope, $rootScope, $http, $location) {

			var load = function() {
				console.log('chiamata http...');
				$http.get($rootScope.appUrl + '${createLink(controller:'admin',action: 'listaoggetti') }').success(
						function(data, status, headers, config) {
							$scope.oggetti = data;
							angular.copy($scope.oggetti, $scope.copy);
						});
			}
			
			load();
		});
