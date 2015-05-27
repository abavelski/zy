angular.module('customers', [])

    .config(function($stateProvider) {

        $stateProvider
            .state('search', {
                url: '/search',
                templateUrl: 'customers/search.tpl.html'
            })
            .state('customer', {
                url: '/customer/:anumber',
                templateUrl: 'customers/customer.tpl.html'
            })
                .state('customer.subscription', {
                    url: '/subscription',
                    templateUrl: 'customers/subscription.tpl.html'
                })
    });

