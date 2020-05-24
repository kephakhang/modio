System.register([], function(exports_1) {
    var Login;
    return {
        setters:[],
        execute: function() {
            Login = (function () {
                function Login(id, password) {
                    this.id = id;
                    this.password = password;
                }
                return Login;
            })();
            exports_1("Login", Login);
        }
    }
});
