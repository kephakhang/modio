System.register(['angular2/core', './../../services/dribbble', './../../services/setting', 'angular2/router', './../../services/redirectURL'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, dribbble_1, setting_1, router_1, redirectURL_1, router_2;
    var PostComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (dribbble_1_1) {
                dribbble_1 = dribbble_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
                router_2 = router_1_1;
            },
            function (redirectURL_1_1) {
                redirectURL_1 = redirectURL_1_1;
            }],
        execute: function() {
            PostComponent = (function () {
                function PostComponent(re, dl, rp, setting) {
                    var _this = this;
                    this.post = new Array();
                    this.re = re;
                    this.setting = setting;
                    this.rp = rp;
                    console.log(this.rp.params.id);
                    var xhr = new XMLHttpRequest();
                    this.setting.id = localStorage.getItem("id");
                    this.setting.accessToken = localStorage.getItem("accessToken");
                    console.log('accessToken : ' + this.setting.accessToken);
                    var url_1 = this.setting.hostUrl + '/api/v2/users/content?id=' + this.rp.params.id + '&accessToken=' + this.setting.accessToken;
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                _this.sn = JSON.parse(xhr.response).sn;
                                _this.id = JSON.parse(xhr.response).id;
                                _this.title = JSON.parse(xhr.response).title;
                                _this.rmk = JSON.parse(xhr.response).rmk;
                                _this.fname = JSON.parse(xhr.response).fname;
                                _this.fsize = JSON.parse(xhr.response).fsize;
                                _this.email = JSON.parse(xhr.response).mbId;
                                _this.view_count = JSON.parse(xhr.response).viewCount;
                                _this.buy_count = JSON.parse(xhr.response).buyCount;
                                _this.share_count = JSON.parse(xhr.response).shareCount;
                                _this.down_count = JSON.parse(xhr.response).downCount;
                                _this.redirect_code = JSON.parse(xhr.response).redirectCode;
                                _this.orig_status = JSON.parse(xhr.response).orig_status;
                                _this.orig_key = JSON.parse(xhr.response).orig_key;
                                _this.category = JSON.parse(xhr.response).caId;
                                _this.extend = _this.fname.substring(_this.fname.length - 4, _this.fname.length);
                                _this.open = JSON.parse(xhr.response).open;
                                console.log(JSON.parse(xhr.response));
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    var vhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/count?countCode=1&uuid=' + this.rp.params.id;
                    vhr.onreadystatechange = function () {
                        if (vhr.readyState === 4) {
                            if (vhr.status === 200) {
                                console.log(JSON.parse(vhr.response));
                                xhr.open('GET', url_1, true);
                                xhr.send(null);
                            }
                            else {
                                console.log(vhr.response);
                            }
                        }
                    };
                    vhr.open('PUT', url, true);
                    vhr.send(null);
                }
                PostComponent.prototype.down_count_up = function () {
                    var _this = this;
                    var xhr = new XMLHttpRequest();
                    var url_1 = this.setting.hostUrl + '/api/v2/users/content/count?countCode=2&uuid=' + this.rp.params.id;
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                console.log(JSON.parse(xhr.response));
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    xhr.open('PUT', url_1, true);
                    xhr.send(null);
                    var dir_xhr = new XMLHttpRequest();
                    var dir_url = this.setting.hostUrl + '/api/v2/content/getyyyymm/' + this.rp.params.id;
                    dir_xhr.onreadystatechange = function () {
                        if (dir_xhr.readyState === 4) {
                            if (dir_xhr.status === 200) {
                                console.log(JSON.parse(dir_xhr.response));
                                _this.content_service_insertDB(3, null);
                                var link = document.createElement("a");
                                var url = _this.setting.hostUrl + '/api/v2/content/binary/' + _this.rp.params.id + '/' + _this.fname.substring(_this.fname.length - 3, _this.fname.length);
                                link.href = url;
                                link.click();
                            }
                            else {
                                console.log(dir_xhr.response);
                            }
                        }
                    };
                    dir_xhr.open('GET', dir_url, true);
                    dir_xhr.send(null);
                    console.log("down_count_up");
                };
                PostComponent.prototype.buy_count_up = function () {
                    alert("준비중입니다");
                };
                PostComponent.prototype.share_count_up = function () {
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/count?countCode=3&uuid=' + this.rp.params.id;
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                console.log(JSON.parse(xhr.response));
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    xhr.open('PUT', url, true);
                    xhr.send(null);
                    console.log("share_count_up");
                };
                PostComponent.prototype.content_service_insertDB = function (t, u) {
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/users/content/share';
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                console.log(JSON.parse(xhr.response));
                            }
                            else {
                                console.log(xhr.response);
                            }
                        }
                    };
                    xhr.open('POST', url, true);
                    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
                    var share_insert_json = new Object();
                    share_insert_json.mbId = localStorage.getItem("id");
                    share_insert_json.accessToken = localStorage.getItem("accessToken");
                    share_insert_json.caId = this.sn;
                    share_insert_json.type = t;
                    share_insert_json.url = u;
                    share_insert_json.regtime = new Date();
                    xhr.send(JSON.stringify(share_insert_json));
                    console.log("share insert db");
                };
                PostComponent.prototype.share_Email = function () {
                    var xhr = new XMLHttpRequest();
                    var url = this.setting.hostUrl + '/api/v2/content/emailshare?';
                    var data = $('#EmailshareForm').serialize();
                    url += data;
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                console.log(xhr.response);
                                alert("공유 URL 을 지정된 이메일로 발송하였습니다.");
                            }
                            else {
                                console.log(xhr.response);
                                alert("이메일 공유 중 오류가 발생하였습니다.");
                            }
                        }
                    };
                    xhr.open('POST', url, true);
                    xhr.setRequestHeader("Content-Type", "text/html; charset=UTF-8");
                    xhr.send(null);
                    console.log("email share !!!");
                };
                PostComponent.prototype.share_FaceBook = function () {
                    var _this = this;
                    var rc;
                    this.re.geturl(this.rp.params.id + "||" + localStorage.getItem("id") + "||" + "FaceBook").subscribe(function (res) {
                        if (res._body.result == 1) {
                            console.log(res._body.msg);
                            rc = res._body.msg;
                            _this.content_service_insertDB(1, "FaceBook");
                            _this.share_count_up();
                        }
                    });
                    window.setTimeout(function () {
                        window.open("https://www.facebook.com/sharer/sharer.php?u=http://3dz.kr?" + rc, "_blank", "width=" + (window.innerWidth / 2), ",height=" + (window.innerHeight / 2));
                    }, 1000);
                };
                PostComponent.prototype.share_Twitter = function () {
                    var _this = this;
                    var rc;
                    this.re.geturl(this.rp.params.id + "||" + localStorage.getItem("id") + "||" + "Twitter").subscribe(function (res) {
                        if (res._body.result == 1) {
                            console.log(res._body.msg);
                            rc = res._body.msg;
                            _this.content_service_insertDB(1, "Twitter");
                            _this.share_count_up();
                        }
                    });
                    window.setTimeout(function () {
                        window.open("https://twitter.com/intent/tweet?text=http://3dz.kr?" + rc, "MsgWindow", "width=" + window.innerWidth / 2 +
                            ",height=" + window.innerHeight / 2);
                    }, 1000);
                };
                PostComponent.prototype.embedded_tag = function () {
                    var _this = this;
                    this.re.geturl(this.rp.params.id + "||" + localStorage.getItem("id") + "||" + "Embedded_Tag").subscribe(function (res) {
                        if (res._body.result == 1) {
                            console.log(res._body.msg);
                            _this.embedded_code = '<iframe id=\"viewer\" width=\"1000\" height=\"600\"  src=\"http://3dz.kr?' + res._body.msg + '\" frameborder=\"0\" allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" onmousewheel=\"\" allowtransparency=\"true\"></iframe>';
                            _this.content_service_insertDB(2, "Embedded_Tag");
                            _this.share_count_up();
                        }
                    });
                };
                PostComponent.prototype.delete_content = function () {
                    if (localStorage.getItem("id") == 'admin@real3d.com') {
                        this.temp_id = this.email;
                    }
                    else {
                        this.temp_id = localStorage.getItem("id");
                    }
                    var delete_content_value = confirm("해당 상품을 삭제하시겠습니까? [ " + this.fname + " ]");
                    if (delete_content_value == true) {
                        var xhr = new XMLHttpRequest();
                        var url = this.setting.hostUrl + '/api/v2/users/content' + '?mb_id=' + this.temp_id + '&id=' + this.rp.params.id + '&accessToken=' + localStorage.getItem("accessToken");
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === 4) {
                                if (xhr.status === 200) {
                                    alert("삭제에 성공했습니다.");
                                    window.location.href = "/";
                                }
                                else {
                                    console.log(xhr.response);
                                }
                            }
                        };
                        xhr.open('DELETE', url, true);
                        xhr.send(null);
                    }
                    else {
                    }
                };
                PostComponent = __decorate([
                    core_1.Component({
                        selector: 'post',
                        templateUrl: 'src/components/post/post.html',
                        providers: [dribbble_1.Dribbble, setting_1.Setting, redirectURL_1.Redirect],
                        directives: [router_2.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [redirectURL_1.Redirect, dribbble_1.Dribbble, router_1.RouteParams, setting_1.Setting])
                ], PostComponent);
                return PostComponent;
            })();
            exports_1("PostComponent", PostComponent);
        }
    }
});
