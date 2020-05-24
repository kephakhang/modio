System.register(['angular2/core', 'angular2/common', 'angular2/router', './../../services/dribbble', './../../services/setting', './../../model/login'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, router_1, dribbble_1, setting_1, login_1;
    var LoginComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (dribbble_1_1) {
                dribbble_1 = dribbble_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            },
            function (login_1_1) {
                login_1 = login_1_1;
            }],
        execute: function() {
            LoginComponent = (function () {
                function LoginComponent(dl, setting, router) {
                    this.id = null;
                    this.submitted = false;
                    this.active = true;
                    this.bWrest = false;
                    this.dribbble = dl;
                    this.setting = setting;
                    this.router = router;
                }
                Object.defineProperty(LoginComponent.prototype, "diagnostic", {
                    get: function () { return JSON.stringify(this.model); },
                    enumerable: true,
                    configurable: true
                });
                LoginComponent.prototype.ngOnInit = function () {
                    this.id = localStorage.getItem('id');
                    if (this.id === undefined || this.id == null)
                        this.model = new login_1.Login('', '');
                    else
                        this.model = new login_1.Login(this.id, '');
                };
                LoginComponent.prototype.ngAfterViewChecked = function () {
                    if (this.bWrest == false) {
                        wrestInitialized();
                        this.bWrest = true;
                    }
                };
                LoginComponent.prototype.onSubmit = function () {
                    var loginBtn = document.getElementById("login_btn");
                    loginBtn.disabled = true;
                    localStorage.setItem('id', this.model.id);
                    this.model.password = Sha256.hash(this.password);
                    this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users/login", this.model)
                        .then(function (response) {
                        if (response === null || typeof response === 'undefined') {
                            loginBtn.disabled = false;
                            return false;
                        }
                        else {
                            localStorage.setItem('accessToken', response.message);
                            localStorage.setItem('id', response.id);
                            localStorage.setItem('level', response.level);
                            loginBtn.disabled = false;
                            window.location.href = "/";
                            return;
                        }
                    }, function (response) {
                        if (response === null || typeof response === 'undefined') {
                            loginBtn.disabled = false;
                            return false;
                        }
                        else {
                            wrestAlert(response.message);
                            loginBtn.disabled = false;
                            return;
                        }
                    })
                        .catch(function (err) {
                        alert(err);
                        loginBtn.disabled = false;
                    });
                    return false;
                };
                LoginComponent = __decorate([
                    core_1.Component({
                        selector: 'login',
                        templateUrl: 'src/components/login/login.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_1.Router])
                ], LoginComponent);
                return LoginComponent;
            })();
            exports_1("LoginComponent", LoginComponent);
        }
    }
});
