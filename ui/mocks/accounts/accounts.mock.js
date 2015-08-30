'use strict';

angular.module('account.mocks', [])
    .run(function run($httpBackend) {
        $httpBackend.whenGET('/api/accounts').respond([
            {
                "subscription": {
                    "id": 7,
                    "userId": 7,
                    "startDate": 1440936698,
                    "pricePlanCode": "pp1",
                    "status": "ACTIVE"
                },
                "service": {
                    "id": 7,
                    "subscriptionId": 7,
                    "phoneNumber": 61660000,
                    "status": "ACTIVE"
                },
                "user": {
                    "id": 7,
                    "firstName": "Alexei",
                    "lastName": "Bavelski",
                    "address": "Roarsvej 23 ST TV",
                    "city": "Frederiksberg",
                    "zip": "2000"
                }
            }]);

        $httpBackend.whenGET('/api/accounts/61660000').respond(
            {
                "subscription": {
                    "id": 7,
                    "userId": 7,
                    "startDate": 1440936698,
                    "pricePlanCode": "pp1",
                    "status": "ACTIVE"
                },
                "service": {
                    "id": 7,
                    "subscriptionId": 7,
                    "phoneNumber": 61660000,
                    "status": "ACTIVE"
                },
                "user": {
                    "id": 7,
                    "firstName": "Alexei",
                    "lastName": "Bavelski",
                    "address": "Roarsvej 23 ST TV",
                    "city": "Frederiksberg",
                    "zip": "2000"
                }
            });


    });