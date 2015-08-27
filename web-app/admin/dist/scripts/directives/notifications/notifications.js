'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('sbAdminApp')
	.directive('notifications',function(){
		return {
        templateUrl:'admin/app/scripts/directives/notifications/notifications.html',
        restrict: 'E',
        replace: true,
    	}
	});


