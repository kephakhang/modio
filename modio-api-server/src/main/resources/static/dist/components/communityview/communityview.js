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
    var core_1, common_1, router_1, dribbble_1, setting_1, router_2;
    var CommunityviewComponent;
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
            function (dribbble_1_1) {
                dribbble_1 = dribbble_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            }],
        execute: function() {
            CommunityviewComponent = (function () {
                function CommunityviewComponent(dl, setting, rp) {
                    var _this = this;
                    this.sn = rp;
                    this.setting = setting;
                    this.setting.accessToken = localStorage.getItem("accessToken");
                    console.log('sn : ' + this.sn.params.id);
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/board?sn=' + this.sn.params.id + '&title=null&rmk=null&startPoint=0&endPoint=0&accessToken=fff';
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                _this.title = JSON.parse(xhr.response)._title;
                                _this.mbid = JSON.parse(xhr.response)._mbId;
                                _this.rmk = JSON.parse(xhr.response)._rmk;
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
                CommunityviewComponent.prototype.onupdate = function () {
                    window.location.href = "/#/communitymodify?id=" + this.sn.params.id;
                };
                CommunityviewComponent.prototype.ondelete = function () {
                    var _this = this;
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/board?sn=' + this.sn.params.id + '&title=null&rmk=null&startPoint=0&endPoint=0&accessToken=fff';
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                _this.title = JSON.parse(xhr.response)._title;
                                _this.mbid = JSON.parse(xhr.response)._mbId;
                                _this.rmk = JSON.parse(xhr.response)._rmk;
                                console.log(JSON.parse(xhr.response));
                                alert('삭제를 완료하였습니다.');
                                window.location.href = "/#/community";
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    xhr.open('DELETE', url, true);
                    xhr.send(null);
                };
                CommunityviewComponent = __decorate([
                    core_1.Component({
                        selector: 'communityview',
                        templateUrl: 'src/components/communityview/communityview.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_2.RouteParams])
                ], CommunityviewComponent);
                return CommunityviewComponent;
            })();
            exports_1("CommunityviewComponent", CommunityviewComponent);
        }
    }
});
