'use strict';
angular.module('notifications', [])

    .factory('notifications', function($window) {
        var toastr = $window.toastr;

        toastr.options = {
            "closeButton": true,
            "positionClass": "toast-bottom-right",
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "10000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };

        return {
            success : function(body, header) {
                toastr.success(body, header);
            },
            warning : function(body, header) {
                toastr.warning(body, header);
            },
            error : function(body, header) {
                toastr.error(body, header)
            }
        };
    });