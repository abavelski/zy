'use strict';
angular.module('notifications', [])

    .factory('notifications', function() {
        var notification;
        return {
            set : function(n) {
                notification = n;
            },
            get : function() {
                return notification;
            },
            remove : function() {
                notification = '';
            }
        };
    });