System.register(['angular2/core', 'angular2/platform/browser', 'angular2/http', 'angular2/router', './services/setting', './services/dribbble', './components/user/user', './components/home/home', './components/post/post', './components/upload/upload', './components/thumnail/thumnail', './components/main/main', './components/login/login', './components/error/error', './components/signup/signup', './components/mycontents/mycontents', './components/sharecontents/sharecontents', './components/sharecontentstable/sharecontentstable', './components/sharecontentstracking/sharecontentstracking', './components/shareuserstable/shareuserstable', './components/modifiedcontents/modifiedcontents', './components/searchcontents/searchcontents', './components/modifieduser/modifieduser', './components/deleteuser/deleteuser', './components/community/community', './components/communityview/communityview', './components/createcommunity/createcommunity', './components/communitymodify/communitymodify', './model/logout'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, browser_1, http_1, router_1, setting_1, dribbble_1, user_1, home_1, post_1, upload_1, thumnail_1, main_1, login_1, error_1, signup_1, mycontents_1, sharecontents_1, sharecontentstable_1, sharecontentstracking_1, shareuserstable_1, modifiedcontents_1, searchcontents_1, modifieduser_1, deleteuser_1, community_1, communityview_1, createcommunity_1, communitymodify_1, logout_1;
    var APP;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            },
            function (dribbble_1_1) {
                dribbble_1 = dribbble_1_1;
            },
            function (user_1_1) {
                user_1 = user_1_1;
            },
            function (home_1_1) {
                home_1 = home_1_1;
            },
            function (post_1_1) {
                post_1 = post_1_1;
            },
            function (upload_1_1) {
                upload_1 = upload_1_1;
            },
            function (thumnail_1_1) {
                thumnail_1 = thumnail_1_1;
            },
            function (main_1_1) {
                main_1 = main_1_1;
            },
            function (login_1_1) {
                login_1 = login_1_1;
            },
            function (error_1_1) {
                error_1 = error_1_1;
            },
            function (signup_1_1) {
                signup_1 = signup_1_1;
            },
            function (mycontents_1_1) {
                mycontents_1 = mycontents_1_1;
            },
            function (sharecontents_1_1) {
                sharecontents_1 = sharecontents_1_1;
            },
            function (sharecontentstable_1_1) {
                sharecontentstable_1 = sharecontentstable_1_1;
            },
            function (sharecontentstracking_1_1) {
                sharecontentstracking_1 = sharecontentstracking_1_1;
            },
            function (shareuserstable_1_1) {
                shareuserstable_1 = shareuserstable_1_1;
            },
            function (modifiedcontents_1_1) {
                modifiedcontents_1 = modifiedcontents_1_1;
            },
            function (searchcontents_1_1) {
                searchcontents_1 = searchcontents_1_1;
            },
            function (modifieduser_1_1) {
                modifieduser_1 = modifieduser_1_1;
            },
            function (deleteuser_1_1) {
                deleteuser_1 = deleteuser_1_1;
            },
            function (community_1_1) {
                community_1 = community_1_1;
            },
            function (communityview_1_1) {
                communityview_1 = communityview_1_1;
            },
            function (createcommunity_1_1) {
                createcommunity_1 = createcommunity_1_1;
            },
            function (communitymodify_1_1) {
                communitymodify_1 = communitymodify_1_1;
            },
            function (logout_1_1) {
                logout_1 = logout_1_1;
            }],
        execute: function() {
            APP = (function () {
                function APP(dl, setting) {
                    this.id = null;
                    this.accessToken = null;
                    this.model = new logout_1.Logout('', '');
                    this.dribbble = dl;
                    this.setting = setting;
                    this.id = localStorage.getItem('id');
                    this.accessToken = localStorage.getItem('accessToken');
                    if (this.id === undefined || this.id === null ||
                        this.accessToken === undefined || this.accessToken === null) {
                    }
                    else {
                        this.model.id = this.id;
                        this.model.accessToken = this.accessToken;
                        this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users/check", this.model)
                            .then(function (response) {
                            if (response === null || typeof response === 'undefined') {
                                return false;
                            }
                            else {
                                return false;
                            }
                        }, function (response) {
                            if (response === null || typeof response === 'undefined') {
                                return false;
                            }
                            else {
                                localStorage.removeItem('id');
                                localStorage.removeItem('accessToken');
                                window.location.href = "/";
                                return;
                            }
                        })
                            .catch(function (err) {
                            alert(err);
                            localStorage.removeItem('id');
                            localStorage.removeItem('accessToken');
                            window.location.href = "/";
                        });
                    }
                }
                APP.prototype.ngOnInit = function () {
                    jQuery("#preloader").delay(100).fadeOut("slow");
                    jQuery("#load").delay(100).fadeOut("slow");
                };
                APP.prototype.navOpen = function () {
                    if (jQuery("#nav").hasClass("nav-open"))
                        jQuery("#nav").removeClass("nav-open");
                    else
                        jQuery("#nav").addClass("nav-open");
                };
                APP.prototype.logout = function () {
                    this.id = localStorage.getItem('accessToken');
                    this.accessToken = localStorage.getItem('accessToken');
                    if (this.id === undefined || this.id === null ||
                        this.accessToken === undefined || this.accessToken === null) {
                        localStorage.removeItem('id');
                        localStorage.removeItem('accessToken');
                        window.location.href = "/";
                    }
                    else {
                        this.model.id = this.id;
                        this.model.accessToken = this.accessToken;
                        this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users/logout", this.model)
                            .then(function (response) {
                            if (response === null || typeof response === 'undefined') {
                                return false;
                            }
                            else {
                                localStorage.removeItem('accessToken');
                                localStorage.removeItem('id');
                                window.location.href = "/";
                                return;
                            }
                        }, function (response) {
                            if (response === null || typeof response === 'undefined') {
                                return false;
                            }
                            else {
                                localStorage.removeItem('accessToken');
                                localStorage.removeItem('id');
                                window.location.href = "/";
                                return;
                            }
                        })
                            .catch(function (err) {
                            alert(err);
                            localStorage.removeItem('accessToken');
                            localStorage.removeItem('id');
                            window.location.href = "/";
                        });
                    }
                    return false;
                };
                APP = __decorate([
                    router_1.RouteConfig([
                        { path: '/snaps', component: home_1.HomeComponent, as: 'Home', useAsDefault: true },
                        { path: '/upload', component: upload_1.UploadComponent, as: 'Upload' },
                        { path: '/snaps/:id', component: post_1.PostComponent, as: 'Post' },
                        { path: '/thumnail', component: thumnail_1.ThumnailComponent, as: 'Thumnail' },
                        { path: '/login', component: login_1.LoginComponent, as: 'Login' },
                        { path: '/main', component: main_1.MainComponent, as: 'Main' },
                        { path: '/error', component: error_1.ErrorComponent, as: 'Error' },
                        { path: '/signup', component: signup_1.SignupComponent, as: 'Signup' },
                        { path: '/emailauth', component: signup_1.SignupComponent, as: 'Emailauth' },
                        { path: '/mycontents', component: mycontents_1.MycontentsComponent, as: 'Mycontents' },
                        { path: '/sharecontents', component: sharecontents_1.SharecontentsComponent, as: 'Sharecontents' },
                        { path: '/sharecontentstable', component: sharecontentstable_1.SharecontentstableComponent, as: 'Sharecontentstable' },
                        { path: '/sharecontentstracking', component: sharecontentstracking_1.SharecontentstrackingComponent, as: 'Sharecontentstracking' },
                        { path: '/shareuserstable', component: shareuserstable_1.ShareuserstableComponent, as: 'Shareuserstable' },
                        { path: '/modifiedcontents', component: modifiedcontents_1.ModifiedcontentsComponent, as: 'Modifiedcontents' },
                        { path: '/searchcontents', component: searchcontents_1.SearchcontentsComponent, as: 'Searchcontents' },
                        { path: '/user', component: user_1.UserComponent, as: 'User' },
                        { path: '/modifieduser', component: modifieduser_1.ModifieduserComponent, as: 'Modifieduser' },
                        { path: '/deleteuser', component: deleteuser_1.DeleteuserComponent, as: 'Deleteuser' },
                        { path: '/community', component: community_1.CommunityComponent, as: 'Community' },
                        { path: '/communityview', component: communityview_1.CommunityviewComponent, as: 'Communityview' },
                        { path: '/createcommunity', component: createcommunity_1.CreateCommunityComponent, as: 'CreateCommunity' },
                        { path: '/communitymodify', component: communitymodify_1.CommunityModifyComponent, as: 'CommunityModify' }
                    ]),
                    core_1.Component({
                        selector: 'app',
                        templateUrl: 'src/components/app/app.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting],
                        directives: [router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [dribbble_1.Dribbble, setting_1.Setting])
                ], APP);
                return APP;
            })();
            exports_1("APP", APP);
            browser_1.bootstrap(APP, [http_1.JSONP_PROVIDERS, router_1.ROUTER_PROVIDERS, core_1.provide(router_1.LocationStrategy, { useClass: router_1.HashLocationStrategy }),
                core_1.provide(router_1.APP_BASE_HREF, { useValue: '/' })]);
        }
    }
});
