module.exports =  {
    assets: {
        files: [
            { dest: '<%= package.dist %>/', src : ['ui/app/index.html', 'ui/app/swagger.html'], flatten: true, expand: true},
            { dest: '<%= package.dist %>/assets', src : '**', expand: true, cwd: 'ui/assets/' },
            {expand: true, src: ['bower_components/bootstrap/dist/fonts/*', 'bower_components/font-awesome/fonts/*'],
                dest: '<%= package.dist %>/fonts/', flatten: true},
            {expand: true, src: ['bower_components/font-awesome/css/font-awesome.css'],
                dest: '<%= package.dist %>/css/', flatten: true}]
    }
};