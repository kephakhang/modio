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
    var SearchcontentsComponent;
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
            SearchcontentsComponent = (function () {
                function SearchcontentsComponent(dl, setting, category, filter) {
                    var _this = this;
                    this.name = 'World';
                    this.posts = new Array();
                    this.page = 0;
                    this.setting = setting;
                    this.dl = dl;
                    this.category = category;
                    this.filter = filter;
                    this.search_text = this.filter.params.search_text;
                    console.log("검색어 : " + this.search_text);
                    if (this.category.params.category != undefined) {
                        document.getElementById("category").innerHTML = this.category.params.category;
                    }
                    else {
                        document.getElementById("category").innerHTML = '카테고리 ▼';
                    }
                    if (this.filter.params.filter != undefined) {
                        document.getElementById("filter").innerHTML = this.filter.params.filter;
                    }
                    else {
                        document.getElementById("filter").innerHTML = '필 터 링 ▼';
                    }
                    console.log('main page..');
                    console.log(this.category);
                    var xhr = new XMLHttpRequest();
                    if (this.category.params.category == '전체 ▼') {
                        this.caId = '0';
                    }
                    else if (this.category.params.category == '사람 ▼') {
                        this.caId = '1';
                    }
                    else if (this.category.params.category == '사물 ▼') {
                        this.caId = '2';
                    }
                    else {
                        this.caId = '0';
                    }
                    if (this.filter.params.filter == '조회수 ▼') {
                        this.filter_r = '1';
                    }
                    else if (this.filter.params.filter == '다운수 ▼') {
                        this.filter_r = '2';
                    }
                    else if (this.filter.params.filter == '공유수 ▼') {
                        this.filter_r = '3';
                    }
                    else if (this.filter.params.filter == '구매수 ▼') {
                        this.filter_r = '4';
                    }
                    else {
                        this.filter_r = '0';
                    }
                    console.log('caId' + this.caId);
                    console.log('filter_r' + this.filter_r);
                    var url = this.setting.hostUrl +
                        '/api/v2/users/content/list?startPoint=0&endPoint=18&caId=' + this.caId +
                        '&filter=' + this.filter_r +
                        '&option=' + '7' +
                        '&optionParam=' + this.search_text;
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
                SearchcontentsComponent.prototype.routerCanDeactivate = function (next, prev) {
                    window.scrollTo(0, 0);
                    return true;
                };
                SearchcontentsComponent.prototype.gotoPost = function (id) {
                    alert(id);
                };
                SearchcontentsComponent.prototype.loadMore = function () {
                    var _this = this;
                    this.page++;
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/list?startPoint=' + this.page * 18 + '&endPoint=18&caId=' + this.caId +
                        '&filter=' + this.filter_r +
                        '&option=' + '7' +
                        '&optionParam=' + this.search_text;
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
                SearchcontentsComponent.prototype.ngOnInit = function () {
                };
                SearchcontentsComponent.prototype.ngOnChanges = function (changes) {
                };
                SearchcontentsComponent = __decorate([
                    core_1.Component({
                        selector: 'main',
                        templateUrl: 'src/components/searchcontents/searchcontents.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting, router_2.RouteParams, router_2.RouteParams])
                ], SearchcontentsComponent);
                return SearchcontentsComponent;
            })();
            exports_1("SearchcontentsComponent", SearchcontentsComponent);
        }
    }
});
