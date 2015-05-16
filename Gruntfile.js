module.exports = function (grunt) {

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-html2js');
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.registerTask('default', ['build']);
    grunt.registerTask('build', ['clean','html2js','concat','copy:assets']);


    grunt.initConfig({
        distdir: 'src/main/resources/static',
        pkg: grunt.file.readJSON('package.json'),
        banner:
            '/*! <%= pkg.title || pkg.name %> - v<%= pkg.version %> - <%= grunt.template.today("yyyy-mm-dd") %> */\n',
        src: {
            js: ['ui/**/*.js', '<%= distdir %>/templates/**/*.js'],
            tpl: {
                app: ['ui/app/**/*.tpl.html'],
                common: ['ui/common/**/*.tpl.html']
            }
        },
        clean: ['<%= distdir %>/*'],
        copy: {
            assets: {
                files: [
                    { dest: '<%= distdir %>/', src : ['ui/app/index.html', 'ui/app/swagger.html'], flatten: true, expand: true},
                    { dest: '<%= distdir %>/assets', src : '**', expand: true, cwd: 'ui/assets/' },
                    {expand: true, src: ['bower_components/bootstrap/dist/fonts/*'],
                        dest: '<%= distdir %>/fonts/', flatten: true}]
            }
        },
        html2js: {
            app: {
                options: {
                    base: 'ui/app'
                },
                src: ['<%= src.tpl.app %>'],
                dest: '<%= distdir %>/templates/app.js',
                module: 'templates.app'
            },
            common: {
                options: {
                    base: 'ui/common'
                },
                src: ['<%= src.tpl.common %>'],
                dest: '<%= distdir %>/templates/common.js',
                module: 'templates.common'
            }
        },
        concat:{
            app:{
                options: {
                    banner: "<%= banner %>"
                },
                src:['<%= src.js %>'],
                dest:'<%= distdir %>/app.js'
            },
            angular: {
                src:['bower_components/angular/angular.js', 'bower_components/angular-route/angular-route.js'],
                dest: '<%= distdir %>/angular.js'
            },
            angularUi: {
                src:['bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
                    'bower_components/angular-bootstrap/ui-bootstrap.js'],
                dest: '<%= distdir %>/angular-ui.js'
            },
            jquery: {
                src:['bower_components/jquery/dist/jquery.js'],
                dest: '<%= distdir %>/jquery.js'
            },
            css: {
                src: ['bower_components/bootswatch/sandstone/bootstrap.css', 'ui/css/app.css'],
                dest: '<%= distdir %>/app.css'

            }

        },
        watch: {
            files: ['ui/**/*.js', 'ui/**/*.html' , 'ui/css/*.css'],
            tasks: ['build']
        }
    });

};
