let proxyObj = {};
proxyObj['/foods'] = {
    target: 'http://localhost:8081',
}
proxyObj['/orders'] = {
    target: 'http://localhost:8081',
}
proxyObj['/customers'] = {
    target: 'http://localhost:8081',
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    },
    assetsDir: "assets"
}