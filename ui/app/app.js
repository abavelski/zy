'use strict';
angular.module('app', [ 'templates.app', 'templates.common', 'ui.router', 'notifications', 'admin', 'signup', 'customers'])

    .config(function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/signup');
    })

    .controller('AppCtrl', function($scope, notifications) {

    })


    .controller('HeaderCtrl', function ($scope, $location) {
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
        $location.path('/signup');

        $scope.searchByPhone=function() {
            $location.path('/customers/'+$scope.phoneNumber+'/subscription');
        }
    });