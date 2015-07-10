angular.module('admin', [])

    .config(function($stateProvider) {

        $stateProvider
            
            .state('admin', {
                url: '/admin',
                templateUrl: 'admin/admin.tpl.html',
                controller : 'AdminCtrl'
            	})
                	.state('admin.billing', {
                    	url: '/billing',
                    	templateUrl: 'admin/billing.tpl.html'
                	})
                	.state('admin.cdr', {
                    	url: '/cdr',
                    	templateUrl: 'admin/cdr.tpl.html'
                	});
    })
    .controller('AdminCtrl', function ($scope, $location) {
    	$scope.isActive = function(str){ return $location.path().search(str)>-1; };
    });