module.exports = {
    server: {
        options: {
            keepalive: false,
            port: 8080,
            base: '<%= package.dist %>'
        }
    }
};