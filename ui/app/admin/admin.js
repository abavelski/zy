angular.module('admin', ['ngRoute'])
    .config(function($routeProvider) {
        $routeProvider
            .when('/admin', {templateUrl: 'admin/admin.tpl.html', controller : 'AdminCtrl' })
    })

    .controller('AdminCtrl', function ($scope, $http) {});