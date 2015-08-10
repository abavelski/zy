'use strict';

angular.module('signup.mocks', [])
    .run(function run($httpBackend) {
        $httpBackend.whenGET('/api/signup/packages').respond([
            {
                "code": "mega",
                "name": "Mega",
                "fees": [
                    "mega",
                    "signup"
                ],
                "pricePlanCode": "pp1",
                "descriptions": {
                    "monthlyPrice": "99",
                    "voice": "12 hours",
                    "data": "20 GB",
                    "other": [
                        "4G"
                    ]
                }
            },
            {
                "code": "mini",
                "name": "Mini",
                "fees": [
                    "mini",
                    "signup"
                ],
                "pricePlanCode": "pp1",
                "descriptions": {
                    "monthlyPrice": "29",
                    "voice": "2 hours",
                    "data": "2 GB",
                    "other": [
                        "4G"
                    ]
                }
            },
            {
                "code": "standard",
                "name": "Standard",
                "fees": [
                    "standard",
                    "signup"
                ],
                "pricePlanCode": "pp1",
                "descriptions": {
                    "monthlyPrice": "69",
                    "voice": "6 hours",
                    "data": "5 GB",
                    "other": [
                        "4G"
                    ]
                }
            }
        ]);

        $httpBackend.whenGET('/api/a-number/type/normal/random/10').respond([
            61660030,
            61660022,
            61660021,
            61660025,
            61660029,
            61660024,
            61660020,
            61660023,
            61660028,
            61660026
        ]);

        //$httpBackend.whenPOST('')
    });