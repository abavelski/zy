module.exports = {
    app: {
        options: {
            base: 'ui/app'
        },
        src: ['ui/app/**/*.tpl.html'],
        dest: '<%= package.dist %>/templates/app.js',
        module: 'templates.app'
    },
    common: {
        options: {
            base: 'ui/common'
        },
        src: ['ui/common/**/*.tpl.html'],
        dest: '<%= package.dist %>/templates/common.js',
        module: 'templates.common'
    }
};