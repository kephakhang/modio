System.register([], function(exports_1) {
    var Uuid;
    return {
        setters:[],
        execute: function() {
            Uuid = (function () {
                function Uuid(uuid) {
                    this.uuid = uuid;
                }
                return Uuid;
            })();
            exports_1("Uuid", Uuid);
        }
    }
});
