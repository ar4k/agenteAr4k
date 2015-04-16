'use strict';
/**
 * @ngdoc overview
 * @name sbAdminApp
 * @description
 * # sbAdminApp
 *
 * Main module of the application.
 */
angular
  .module('sbAdminApp', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'angular-loading-bar',
  ])
  .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {
    
    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/dashboard/rossonet');

    $stateProvider
      .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'admin/app/views/dashboard/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'sbAdminApp',
                    files:[
                    'admin/app/scripts/directives/header/header.js',
                    'admin/app/scripts/directives/header/header-notification/header-notification.js',
                    'admin/app/scripts/directives/sidebar/sidebar.js',
                    'admin/app/scripts/directives/sidebar/sidebar-search/sidebar-search.js'
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["admin/bower_components/angular-toggle-switch/angular-toggle-switch.min.js",
                          "admin/bower_components/angular-toggle-switch/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['admin/bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['admin/bower_components/angular-cookies/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['admin/bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['admin/bower_components/angular-sanitize/angular-sanitize.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['admin/bower_components/angular-touch/angular-touch.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home',
        controller: 'MainCtrl',
        templateUrl:'admin/app/views/dashboard/home.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'sbAdminApp',
              files:[
              'admin/app/scripts/controllers/main.js',
              'admin/app/scripts/directives/timeline/timeline.js',
              'admin/app/scripts/directives/notifications/notifications.js',
              'admin/app/scripts/directives/chat/chat.js',
              'admin/app/scripts/directives/dashboard/stats/stats.js'
              ]
            })
          }
        }
      })
      .state('dashboard.rossonet',{
        url:'/rossonet',
        controller: 'MainCtrl',
        templateUrl:'admin/rossonet',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'sbAdminApp',
              files:[
              'admin/app/scripts/controllers/main.js',
              'admin/app/scripts/directives/timeline/timeline.js',
              'admin/app/scripts/directives/notifications/notifications.js',
              'admin/app/scripts/directives/chat/chat.js',
              'admin/app/scripts/directives/dashboard/stats/stats.js'
              ]
            })
          }
        }
      })
      .state('dashboard.form',{
        templateUrl:'admin/app/views/form.html',
        url:'/form'
    })
      .state('dashboard.blank',{
        templateUrl:'admin/app/views/pages/blank.html',
        url:'/blank'
    })
      .state('login',{
        templateUrl:'admin/app/views/pages/login.html',
        url:'/login'
    })
      .state('dashboard.chart',{
        templateUrl:'admin/app/views/chart.html',
        url:'/chart',
        controller:'ChartCtrl',
        resolve: {
          loadMyFile:function($ocLazyLoad) {
            return $ocLazyLoad.load(
                'admin/bower_components/Chart.js/Chart.min.js'
            ),
            $ocLazyLoad.load({
              name:'chart.js',
              files:[
                'admin/bower_components/angular-chart.js/dist/angular-chart.min.js',
                'admin/bower_components/angular-chart.js/dist/angular-chart.css'
              ]
            }),
            $ocLazyLoad.load({
                name:'sbAdminApp',
                files:['admin/app/scripts/controllers/chartContoller.js']
            })
          }
        }
    })
      .state('dashboard.table',{
        templateUrl:'admin/app/views/table.html',
        url:'/table'
    })
      .state('dashboard.panels-wells',{
          templateUrl:'admin/app/views/ui-elements/panels-wells.html',
          url:'/panels-wells'
      })
      .state('dashboard.buttons',{
        templateUrl:'admin/app/views/ui-elements/buttons.html',
        url:'/buttons'
    })
      .state('dashboard.notifications',{
        templateUrl:'admin/app/views/ui-elements/notifications.html',
        url:'/notifications'
    })
      .state('dashboard.typography',{
       templateUrl:'admin/app/views/ui-elements/typography.html',
       url:'/typography'
   })
      .state('dashboard.icons',{
       templateUrl:'admin/app/views/ui-elements/icons.html',
       url:'/icons'
   })
      .state('dashboard.grid',{
       templateUrl:'admin/app/views/ui-elements/grid.html',
       url:'/grid'
   })
  }]);

    
