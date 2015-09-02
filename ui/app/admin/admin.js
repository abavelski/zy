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
                	.state('admin.fees', {
                    	url: '/fees',
                    	templateUrl: 'admin/fees.tpl.html',
                        controller: 'FeesCtrl'
                	});
    })
    .controller('AdminCtrl', function ($scope, $location) {
    	$scope.isActive = function(str){ return $location.path().search(str)>-1; };
    })
    .controller('FeesCtrl', function ($scope, $http, notifications) {
        $scope.chargeAllFees = function(){
            $http.post('/api/fees/charge')
                .success(function(){
                    notifications.success('All fees charged.');
                })
                .error(function(){
                    notifications.error('Error charging the fees.');
                });

        }
    })
    .controller('NumberRangeCtrl', function ($scope, $http, notifications) {
        $scope.numberRange = {};
        $scope.submitRange = function() {
            $http.post('/api/number-range', $scope.numberRange)
                .success(function(){
                    notifications.success('A-Numbers created');
                    $scope.numberRange = {};
                })
                .error(function(){
                    notifications.error('A-Numbers NOT created');
                });
        };
    })
    .controller('CdrCtrl', function ($scope, $http, notifications) {
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

        var myDate = function(dt, tm) {
            dt.setUTCHours(tm.getUTCHours());
            dt.setUTCMinutes(tm.getUTCMinutes());
            dt.setUTCSeconds(tm.getUTCSeconds());

            return dt.getTime()/1000;
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
            $http.post('/api/billing-records/', cdr)
                .success(function(){
                    notifications.success('Rated sucessfully');
                })
                .error(function(){
                    notifications.error('Error!!!');
                });
        };

    });
