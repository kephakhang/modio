System.register(['angular2/core', 'angular2/common', 'angular2/router', './../../services/dribbble', './../../services/setting', './../../model/user'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, router_1, dribbble_1, setting_1, user_1;
    var DeleteuserComponent;
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
            function (user_1_1) {
                user_1 = user_1_1;
            }],
        execute: function() {
            DeleteuserComponent = (function () {
                function DeleteuserComponent(dl, setting, router) {
                    this.id = localStorage.getItem('id');
                    this.model = new user_1.User('', '', 1, '');
                    this.password1 = '';
                    this.password2 = '';
                    this.bWrest = false;
                    this.submitted = false;
                    this.active = true;
                    this.dribbble = dl;
                    this.setting = setting;
                    this.router = router;
                }
                DeleteuserComponent.prototype.ngAfterViewChecked = function () {
                    if (this.bWrest == false) {
                        wrestInitialized();
                        this.bWrest = true;
                    }
                };
                Object.defineProperty(DeleteuserComponent.prototype, "diagnostic", {
                    get: function () { return JSON.stringify(this.model); },
                    enumerable: true,
                    configurable: true
                });
                DeleteuserComponent.prototype.onSubmit = function () {
                    if (this.password1 != this.password2) {
                        wrestAlert("입력한 암호가 서로 일치하지 않습니다.");
                        return false;
                    }
                    var signupBtn = document.getElementById("signupBtn");
                    signupBtn.disabled = true;
                    this.model.password = Sha256.hash(this.password1);
                    this.model.id = localStorage.getItem("id");
                    console.log(this.model);
                    this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users/login", this.model)
                        .then(function (response) {
                        if (response === null || typeof response === 'undefined') {
                            signupBtn.disabled = false;
                            return false;
                        }
                        else {
                            var $active = $('.wizard .nav-tabs li.active');
                            $active.next().removeClass('disabled');
                            $($active).next().find('a[data-toggle="tab"]').click();
                            window.scrollTo(0, 0);
                            console.log('비밀번호 인증 성공');
                            var set = new setting_1.Setting();
                            var xhr = new XMLHttpRequest();
                            var url = set.hostUrl + "/api/v2/users?id=" + localStorage.getItem("id") + "&accessToken=" + localStorage.getItem("accessToken");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4) {
                                    if (xhr.status === 200) {
                                        window.location.href = "/";
                                    }
                                    else {
                                        console.log(JSON.parse(xhr.response));
                                    }
                                }
                            };
                            xhr.open("DELETE", url, true);
                            xhr.send(null);
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
                DeleteuserComponent = __decorate([
                    core_1.Component({
                        selector: 'deleteuser',
                        templateUrl: 'src/components/deleteuser/deleteuser.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_1.Router])
                ], DeleteuserComponent);
                return DeleteuserComponent;
            })();
            exports_1("DeleteuserComponent", DeleteuserComponent);
        }
    }
});
