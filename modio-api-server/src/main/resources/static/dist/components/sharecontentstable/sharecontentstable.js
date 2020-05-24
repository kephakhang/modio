System.register(['angular2/core', 'angular2/common', 'angular2/router', './../../services/setting'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, router_1, router_2, setting_1;
    var SharecontentstableComponent;
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
                router_2 = router_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            }],
        execute: function() {
            SharecontentstableComponent = (function () {
                function SharecontentstableComponent(setting, params) {
                    var _this = this;
                    this.posts = new Array();
                    this.page = 0;
                    this.setting = setting;
                    this.url = params;
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl +
                        '/api/v2/users/content/list?startPoint=0&endPoint=10&caId=' + '0' +
                        '&filter=' + '0' +
                        '&option=' + '4' +
                        '&optionParam=' + this.url.params.sn;
                    console.log(url);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                _this.posts = JSON.parse(xhr.response);
                                console.log(JSON.parse(xhr.response));
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    xhr.open('GET', url, true);
                    xhr.send(null);
                }
                SharecontentstableComponent.prototype.return_uuid = function () {
                    return this.url.params.id;
                };
                SharecontentstableComponent.prototype.return_orig_status = function () {
                    return this.url.params.orig_status;
                };
                SharecontentstableComponent.prototype.loadMore = function () {
                    var _this = this;
                    this.page++;
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl +
                        '/api/v2/users/content/list?startPoint=' + this.page * 10 + '&endPoint=10&caId=' + '0' +
                        '&filter=' + '0' +
                        '&option=' + '4' +
                        '&optionParam=' + this.url.params.sn;
                    console.log(url);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                JSON.parse(xhr.response).forEach(function (post) {
                                    _this.posts.push(post);
                                });
                                console.log(JSON.parse(xhr.response) + _this.page * 18);
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    xhr.open('GET', url, true);
                    xhr.send(null);
                };
                SharecontentstableComponent = __decorate([
                    core_1.Component({
                        selector: 'sharecontentstable',
                        templateUrl: 'src/components/sharecontentstable/sharecontentstable.html',
                        providers: [setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [setting_1.Setting, router_2.RouteParams])
                ], SharecontentstableComponent);
                return SharecontentstableComponent;
            })();
            exports_1("SharecontentstableComponent", SharecontentstableComponent);
        }
    }
});
