System.register(['angular2/core', './../../services/setting'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, setting_1;
    var EmailauthComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            }],
        execute: function() {
            EmailauthComponent = (function () {
                function EmailauthComponent(setting) {
                    this.setting = setting;
                    this.id = this.setting.getCookie("id");
                    this.message = this.setting.getCookie("message");
                    this.accessToken = this.setting.getCookie("accessToken");
                    localStorage.setItem('accessToken', this.accessToken);
                }
                EmailauthComponent = __decorate([
                    core_1.Component({
                        selector: 'emailauth',
                        templateUrl: 'src/components/emailauth/emailauth.html',
                        providers: [setting_1.Setting],
                    }), 
                    __metadata('design:paramtypes', [setting_1.Setting])
                ], EmailauthComponent);
                return EmailauthComponent;
            })();
            exports_1("EmailauthComponent", EmailauthComponent);
        }
    }
});
