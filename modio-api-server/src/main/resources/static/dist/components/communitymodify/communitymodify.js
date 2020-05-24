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
    var CommunityModifyComponent;
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
            CommunityModifyComponent = (function () {
                function CommunityModifyComponent(dl, setting, rp) {
                    var _this = this;
                    this.rp = rp;
                    this.setting = setting;
                    this.setting.accessToken = localStorage.getItem("accessToken");
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/board?sn=' + this.rp.params.id + '&title=null&rmk=null&startPoint=0&endPoint=0&accessToken=fff';
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                _this.title = JSON.parse(xhr.response)._title;
                                document.getElementById("title").value = _this.title;
                                _this.mbid = JSON.parse(xhr.response)._mbId;
                                _this.rmk = JSON.parse(xhr.response)._rmk;
                                document.getElementById("rmk").value = _this.rmk;
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
                CommunityModifyComponent.prototype.onupdate = function () {
                    var _this = this;
                    if (confirm("정말 수정하시겠습니까??") == true) {
                        this.title = document.getElementById("title").value;
                        this.rmk = document.getElementById("rmk").value;
                        var xhr = new XMLHttpRequest();
                        var url = this.setting.hostUrl + '/api/v2/users/content/board?sn=' + this.rp.params.id + '&title=' + this.title + '&rmk=' + this.rmk + '&startPoint=0&endPoint=0&accessToken=fff';
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === 4) {
                                if (xhr.status === 200) {
                                    console.log(JSON.parse(xhr.response));
                                    window.location.href = "/#/communityview?id=" + _this.rp.params.id;
                                }
                                else {
                                    console.log(xhr.response);
                                }
                            }
                        };
                        xhr.open('PUT', url, true);
                        xhr.send(null);
                    }
                    else {
                        return;
                    }
                };
                CommunityModifyComponent.prototype.onback = function () {
                    window.location.href = "/#/communityview?id=" + this.rp.params.id;
                };
                CommunityModifyComponent = __decorate([
                    core_1.Component({
                        selector: 'communitymodify',
                        templateUrl: 'src/components/communitymodify/communitymodify.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_2.RouteParams])
                ], CommunityModifyComponent);
                return CommunityModifyComponent;
            })();
            exports_1("CommunityModifyComponent", CommunityModifyComponent);
        }
    }
});
