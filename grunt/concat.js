module.exports = {
    app:{
        options: {
            banner: '/*! <%= package.title || package.name %>-v<%= package.version %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
        },
        src:['ui/app/**/*.js', '<%= package.dist %>/templates/**/*.js'],
        dest:'<%= package.dist %>/app.js'
    },
    appMocks:{
        options: {
            banner: '/*! <%= package.title || package.name %>-v<%= package.version %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
        },
        src:['ui/app/**/*.js', 'ui/mocks/**/*.mock.js', '<%= package.dist %>/templates/**/*.js'],
        dest:'<%= package.dist %>/app.js'
    },
    angular: {
        src:['node_modules/angular/angular.js',
            'node_modules/angular-ui-router/release/angular-ui-router.js',
            'node_modules/angular-bootstrap/ui-bootstrap-tpls.js',
            'node_modules/toastr/toastr.js'],
        dest: '<%= package.dist %>/angular.js'
    },
    angularMocks: {
        src:['node_modules/angular/angular.js',
            'node_modules/angular-mocks/angular-mocks.js',
            'node_modules/angular-ui-router/release/angular-ui-router.js',
            'node_modules/angular-bootstrap/ui-bootstrap-tpls.js',
            'node_modules/toastr/toastr.js'],
        dest: '<%= package.dist %>/angular.js'
    },
    jquery: {
        src:['node_modules/jquery/dist/jquery.js'],
        dest: '<%= package.dist %>/jquery.js'
    },
    css: {
        src: ['node_modules/bootswatch/sandstone/bootstrap.css',
            'node_modules/toastr/build/toastr.css',
            'ui/css/app.css'],
        dest: '<%= package.dist %>/app.css'
    }
};