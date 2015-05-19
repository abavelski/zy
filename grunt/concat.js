module.exports = {
    app:{
        options: {
            banner: '/*! <%= package.title || package.name %>-v<%= package.version %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
        },
        src:['ui/**/*.js', '<%= package.dist %>/templates/**/*.js'],
        dest:'<%= package.dist %>/app.js'
    },
    angular: {
        src:['bower_components/angular/angular.js', 'bower_components/angular-route/angular-route.js'],
        dest: '<%= package.dist %>/angular.js'
    },
    angularUi: {
        src:['bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
            'bower_components/angular-bootstrap/ui-bootstrap.js'],
        dest: '<%= package.dist %>/angular-ui.js'
    },
    jquery: {
        src:['bower_components/jquery/dist/jquery.js'],
        dest: '<%= package.dist %>/jquery.js'
    },
    css: {
        src: ['bower_components/bootswatch/sandstone/bootstrap.css', 'ui/css/app.css'],
        dest: '<%= package.dist %>/app.css'

    }
};