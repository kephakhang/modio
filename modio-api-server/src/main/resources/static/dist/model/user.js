System.register([], function(exports_1) {
    var User;
    return {
        setters:[],
        execute: function() {
            User = (function () {
                function User(id, password, level, name, tel, birth, regtime, updtime) {
                    this.id = id;
                    this.password = password;
                    this.level = level;
                    this.name = name;
                    this.tel = tel;
                    this.birth = birth;
                    this.regtime = regtime;
                    this.updtime = updtime;
                }
                return User;
            })();
            exports_1("User", User);
        }
    }
});
