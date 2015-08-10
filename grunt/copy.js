module.exports = function (grunt) {
    return  {
        assets: {
            files: [
                { dest: '<%= package.dist %>/', src : ['ui/app/index.html'], flatten: true, expand: true},
                { dest: '<%= package.dist %>/assets', src : '**', expand: true, cwd: 'ui/assets/' },
                {expand: true, src: ['node_modules/bootstrap/dist/fonts/*', 'node_modules/font-awesome/fonts/*'],
                    dest: '<%= package.dist %>/fonts/', flatten: true},
                {expand: true, src: ['node_modules/font-awesome/css/font-awesome.css'],
                    dest: '<%= package.dist %>/css/', flatten: true}]
        },
        mockApp: {
            files: [{ dest: '<%= package.dist %>/', src : ['ui/app/index.html'], flatten: true, expand: true}],
            options: {
                process: function(content) {
                    return grunt.template.process(content, {
                        data: {appModule:'app.mocks'}
                    });
                }
            }
        },
        app: {
            files: [{ dest: '<%= package.dist %>/', src : ['ui/app/index.html'], flatten: true, expand: true}],
            options: {
                process: function(content) {
                    return grunt.template.process(content, {
                        data: {appModule:'app'}
                    });
                }
            }
        }

    };
};

