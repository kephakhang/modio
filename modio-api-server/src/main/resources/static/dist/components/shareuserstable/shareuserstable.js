System.register(['angular2/core', 'angular2/router', './../../services/dribbble', './../../services/setting'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, dribbble_1, setting_1, router_2;
    var ShareuserstableComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
                router_2 = router_1_1;
            },
            function (dribbble_1_1) {
                dribbble_1 = dribbble_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            }],
        execute: function() {
            ShareuserstableComponent = (function () {
                function ShareuserstableComponent(dl, setting, params) {
                    var _this = this;
                    this.name = 'World';
                    this.posts = new Array();
                    this.page = 0;
                    this.setting = setting;
                    this.id = params;
                    this.name = localStorage.getItem("id");
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl +
                        '/api/v2/users/content/list?startPoint=0&endPoint=10&caId=' + '0' +
                        '&filter=' + '0' +
                        '&option=' + '5' +
                        '&optionParam=' + this.id.params.id;
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
                ShareuserstableComponent.prototype.loadMore = function () {
                    var _this = this;
                    this.page++;
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl +
                        '/api/v2/users/content/list?startPoint=' + this.page * 10 + '&endPoint=10&caId=' + '0' +
                        '&filter=' + '0' +
                        '&option=' + '5' +
                        '&optionParam=' + this.id.params.id;
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
                ShareuserstableComponent.prototype.ngOnInit = function () {
                };
                ShareuserstableComponent.prototype.ngOnChanges = function (changes) {
                };
                ShareuserstableComponent = __decorate([
                    core_1.Component({
                        selector: 'shareuserstable',
                        templateUrl: 'src/components/shareuserstable/shareuserstable.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_2.RouteParams])
                ], ShareuserstableComponent);
                return ShareuserstableComponent;
            })();
            exports_1("ShareuserstableComponent", ShareuserstableComponent);
        }
    }
});
