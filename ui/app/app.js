'use strict';
angular.module('app', [ 'templates.app', 'templates.common', 'ui.router', 'notifications', 'admin', 'signup', 'customers'])

    .config(function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/signup');

        $stateProvider
            .state('admin', {
                url: '/admin',
                templateUrl: 'admin/admin.tpl.html',
                controller: 'AdminCtrl'
            });
    })

    .controller('AppCtrl', function($scope, notifications) {
        $scope.getNotification = function() {
            return notifications.get();
        };
        $scope.removeNotification = function() {
            notifications.remove();
        }
    })


    .controller('HeaderCtrl', function ($scope, $location) {
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
        $location.path('/signup');
    });