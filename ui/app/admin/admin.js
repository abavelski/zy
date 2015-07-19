angular.module('admin', ['notifications'])

    .config(function($stateProvider) {

        $stateProvider
            
            .state('admin', {
                url: '/admin',
                templateUrl: 'admin/admin.tpl.html',
                controller: 'AdminCtrl'
            	})
                	.state('admin.numberrange', {
                    	url: '/numberrange',
                    	templateUrl: 'admin/numberrange.tpl.html',
                        controller: 'NumberRangeCtrl'
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
    .controller('AdminCtrl', function ($scope, $location, notifications) {

        $scope.notifications = notifications;

    	$scope.isActive = function(str){ return $location.path().search(str)>-1; };
    })
    .controller('NumberRangeCtrl', function ($scope, $http, notifications) {
        $scope.numberRange = {};
        $scope.submitRange = function() {
            console.log('Range:', $scope.numberRange.firstNumber, $scope.numberRange.lastNumber);
            $http.post('/api/number-range', $scope.numberRange)
                .success(function(){
                    notifications.set('A-Numbers created');
                    $scope.numberRange = {};
                })
                .error(function(){
                    notifications.set('A-Numbers NOT created');
                });
        };
    })
    .controller('CdrCtrl', function ($scope, $http) {
        $scope.dt = new Date();
        $scope.tm = new Date();
        $scope.usageTypes = ['VOICE', 'DATA', 'SMS', 'MMS'];
        $scope.selectedUsageType = $scope.usageTypes[0];

        $scope.trafficTypes = ['HOME', 'INT', 'ROAM_IN', 'ROAM_OUT'];
        $scope.selectedTrafficType = $scope.trafficTypes[0];

        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        var pad = function (number) {
            if (number < 10) {
                return '0' + number;
            }
            return number;
        };

        var myDate = function(dt, tm) {
            return dt.getUTCFullYear() +
                '-' + pad(dt.getUTCMonth() + 1) +
                '-' + pad(dt.getUTCDate()) +
                'T' + pad(tm.getUTCHours()) +
                ':' + pad(tm.getUTCMinutes()) +
                ':' + pad(tm.getUTCSeconds());
        };

        $scope.submitCdr = function() {
            var cdr = {
                usageType : $scope.selectedUsageType,
                trafficType : $scope.selectedTrafficType,
                destination : $scope.bNumber,
                phoneNumber : $scope.aNumber,
                chargeDate : myDate($scope.dt, $scope.tm),
                amount : $scope.amount
            };
            console.log(cdr);
            $http.post('/api/billing-records/', cdr)
                .success(function(){
                    console.log('OK');
                })
                .error(function(){
                    console.log('error');
                });
        };

    });