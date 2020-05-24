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
    var CreateCommunityComponent;
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
            CreateCommunityComponent = (function () {
                function CreateCommunityComponent(dl, setting, router) {
                    this.name = 'World';
                    this.posts = new Array();
                    this.page = 0;
                    this.dl = dl;
                    this.setting = setting;
                    this.router = router;
                    this.setting.accessToken = localStorage.getItem("accessToken");
                }
                CreateCommunityComponent.prototype.onSubmit = function () {
                    this.title = document.getElementById("title").value;
                    this.rmk = document.getElementById("rmk").value;
                    this.user_id = localStorage.getItem("id");
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/board?mb_id=' + this.user_id + '&title=' + this.title + '&rmk=' + this.rmk + '&startPoint=0&endPoint=0&accessToken=d';
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                console.log(JSON.parse(xhr.response));
                                alert('등록에 성공하셨습니다.');
                                document.getElementById("title").value = '';
                                document.getElementById("rmk").value = '';
                                window.location.href = "/#/community";
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    xhr.open('POST', url, true);
                    xhr.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
                    xhr.send(null);
                };
                CreateCommunityComponent.prototype.onback = function () {
                    document.getElementById("title").value = '';
                    document.getElementById("rmk").value = '';
                    window.location.href = "/#/community";
                };
                CreateCommunityComponent = __decorate([
                    core_1.Component({
                        selector: 'createcommunity',
                        templateUrl: 'src/components/createcommunity/createcommunity.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_1.Router])
                ], CreateCommunityComponent);
                return CreateCommunityComponent;
            })();
            exports_1("CreateCommunityComponent", CreateCommunityComponent);
        }
    }
});
