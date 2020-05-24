System.register(['angular2/core', 'angular2/common', 'angular2/router', './../../services/dribbble', './../../services/setting'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, router_1, dribbble_1, setting_1;
    var UserComponent;
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
            }],
        execute: function() {
            UserComponent = (function () {
                function UserComponent(dl, setting, router) {
                    this.id = localStorage.getItem('id');
                    this.model = new Array();
                    this.password1 = '';
                    this.password2 = '';
                    this.bWrest = false;
                    this.submitted = false;
                    this.active = true;
                    this.dribbble = dl;
                    this.setting = setting;
                    this.router = router;
                }
                UserComponent.prototype.ngAfterViewChecked = function () {
                    if (this.bWrest == false) {
                        wrestInitialized();
                        this.bWrest = true;
                    }
                };
                UserComponent.prototype.ngOnInit = function () {
                    this.setting.id = localStorage.getItem('id');
                    this.setting.accessToken = localStorage.getItem('accessToken');
                    var url = this.setting.hostUrl + "/api/v2/users?id=" + this.setting.id + "&accessToken=" + this.setting.accessToken;
                    var xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                this.model = JSON.parse(xhr.response);
                                signupBtn.disabled = false;
                            }
                            else {
                                var res = JSON.parse(xhr.response);
                                wrestAlert(res.message);
                                signupBtn.disabled = false;
                                return;
                            }
                        }
                    };
                    xhr.open("GET", url, true);
                    xhr.send(null);
                };
                UserComponent.prototype.selectFile = function ($event) {
                    var inputValue = $event.target;
                    this.file = inputValue.files[0];
                    console.debug("Input File name: " + this.file.name + " type:" + this.file.size + " size:" + this.file.size);
                };
                UserComponent.prototype.onSubmit = function () {
                    if (this.password1 != this.password2) {
                        wrestAlert("입력한 암호가 서로 일치하지 않습니다.");
                        return false;
                    }
                    var signupBtn = document.getElementById("signupBtn");
                    signupBtn.disabled = true;
                    localStorage.setItem('id', this.model.id);
                    this.model.password = Sha256.hash(this.password1);
                    this.dribbble.putRequest(this.setting.hostUrl + "/api/v2/users", this.model)
                        .then(function (response) {
                        if (response === null || typeof response === 'undefined') {
                            signupBtn.disabled = false;
                            return false;
                        }
                        else {
                            return;
                        }
                    }, function (response) {
                        if (response === null || typeof response === 'undefined') {
                            signupBtn.disabled = false;
                            return false;
                        }
                        else {
                            wrestAlert(response.message);
                            signupBtn.disabled = false;
                            return;
                        }
                    })
                        .catch(function (err) {
                        wrestAlert(err);
                        signupBtn.disabled = false;
                    });
                    return false;
                };
                UserComponent = __decorate([
                    core_1.Component({
                        selector: 'user',
                        templateUrl: 'src/components/user/user.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_1.Router])
                ], UserComponent);
                return UserComponent;
            })();
            exports_1("UserComponent", UserComponent);
        }
    }
});
