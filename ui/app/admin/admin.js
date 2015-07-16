angular.module('admin', [])

    .config(function($stateProvider) {

        $stateProvider
            
            .state('admin', {
                url: '/admin',
                templateUrl: 'admin/admin.tpl.html',
                controller: 'AdminCtrl'
            	})
                	.state('admin.billing', {
                    	url: '/billing',
                    	templateUrl: 'admin/billing.tpl.html'
                	})
                	.state('admin.cdr', {
                    	url: '/cdr',
                    	templateUrl: 'admin/cdr.tpl.html',
					    controller: 'CdrCtrl'
                	})
                	.state('admin.provisioning', {
                    	url: '/provisioning',
                    	templateUrl: 'admin/provisioning.tpl.html'
                	});
    })
    .controller('AdminCtrl', function ($scope, $location) {
    	$scope.isActive = function(str){ return $location.path().search(str)>-1; };
    })
    .controller('CdrCtrl', function ($scope) {
        $scope.usageTypes = ['VOICE', 'DATA', 'SMS', 'MMS'];
        $scope.selectedUsageType = $scope.usageTypes[0];

        $scope.trafficTypes = ['HOME', 'INT', 'ROAM_IN', 'ROAM_OUT'];
        $scope.selectedTrafficType = $scope.trafficTypes[0];

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

    });