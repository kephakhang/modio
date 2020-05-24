System.register([], function(exports_1) {
    var Logout;
    return {
        setters:[],
        execute: function() {
            Logout = (function () {
                function Logout(id, accessToken) {
                    this.id = id;
                    this.accessToken = accessToken;
                }
                return Logout;
            })();
            exports_1("Logout", Logout);
        }
    }
});
