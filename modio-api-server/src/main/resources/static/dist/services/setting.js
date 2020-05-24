System.register(['angular2/core'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1;
    var Setting;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            Setting = (function () {
                function Setting() {
                    this.hostUrl = (window.location.protocol == "80") ?
                        (window.location.protocol + "//" + window.location.hostname) :
                        (window.location.protocol + "//" + window.location.hostname + ":" + window.location.port);
                    this.accessToken = null;
                    this.id = null;
                    this.ssntUrl = 'http://119.195.163.135:3390';
                }
                Setting.prototype.getCookie = function (name) {
                    var ca = document.cookie.split(';');
                    var caLen = ca.length;
                    var cookieName = name + "=";
                    var c;
                    for (var i = 0; i < caLen; i += 1) {
                        c = ca[i].replace(/^\s\+/g, "");
                        if (c.indexOf(cookieName) == 0) {
                            return c.substring(cookieName.length, c.length);
                        }
                    }
                    return "";
                };
                Setting.prototype.deleteCookie = function (name) {
                    this.setCookie(name, "", -1);
                };
                Setting.prototype.setCookie = function (name, value, expireDays, path) {
                    if (path === void 0) { path = ""; }
                    var d = new Date();
                    value = encodeURIComponent(value);
                    d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
                    var expires = "expires=" + d.toUTCString();
                    document.cookie = name + "=" + value + "; " + expires + (path.length > 0 ? "; path=" + path : "");
                };
                Setting = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [])
                ], Setting);
                return Setting;
            })();
            exports_1("Setting", Setting);
        }
    }
});
