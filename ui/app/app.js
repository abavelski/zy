'use strict';
angular.module('app', [ 'templates.app', 'templates.common', 'ui.router', 'admin', 'signup'])

    .config(function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/signup');

        $stateProvider
            .state('admin', {
                url: '/admin',
                templateUrl: 'admin/admin.tpl.html',
                controller: 'AdminCtrl'
            });
    })

    .controller('AppCtrl', ['$scope', function($scope) {}])

    .controller('HeaderCtrl', function ($scope, $location) {
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
        $location.path('/signup');
    });