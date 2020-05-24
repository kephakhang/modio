System.register(['angular2/core', 'angular2/common', 'angular2/router', './../../model/uuid', './../../services/redirectURL', './../../services/setting'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, router_1, uuid_1, router_2, redirectURL_1, setting_1;
    var ThumnailComponent;
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
            function (uuid_1_1) {
                uuid_1 = uuid_1_1;
            },
            function (redirectURL_1_1) {
                redirectURL_1 = redirectURL_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            }],
        execute: function() {
            ThumnailComponent = (function () {
                function ThumnailComponent(re, setting, params) {
                    var _this = this;
                    this.re = re;
                    this.setting = setting;
                    this.uid = new uuid_1.Uuid('test');
                    console.log('sdfadsfasdfasdfasfsas');
                    console.log(params.get("uuid"));
                    console.log(params.get("trxid"));
                    console.log(params.get("result"));
                    this.uid.uuid = params.get("uuid");
                    console.log("uid : " + this.uid.uuid);
                    if (params.get("result") == "1") {
                        this.OriginalSuccess_link = 'https://live.blockcypher.com/btc-testnet/tx/' + params.get("trxid");
                        this.OriginalPdf_link = this.setting.ssntUrl + '/ssnt/certificate?id=' + params.get("trxid");
                        $('#OriginalSuccess').modal('show');
                    }
                    else if (params.get("result") == "9") {
                        var xhr = new XMLHttpRequest();
                        var url = this.setting.hostUrl + '/api/v2/users/content/original?uuid=' + params.get("trxid") + '&accessToken=' + localStorage.getItem("accessToken");
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === 4) {
                                if (xhr.status === 200) {
                                    var response_json = JSON.parse(xhr.response);
                                    console.log(response_json);
                                    _this.OriginalFail_link = _this.setting.hostUrl + '/#/snaps/' + response_json.uuid;
                                    _this.OriginalFail_email = response_json.email;
                                    $('#OriginalFail').modal('show');
                                }
                                else {
                                    console.log(xhr.response);
                                    alert('원본증명 결과를 로드하는데 에러가 발생했습니다.');
                                }
                            }
                        };
                        xhr.open('GET', url, true);
                        xhr.send(null);
                    }
                    else if (params.get("result") == "0") {
                        alert('원본증명 처리 도중 에러가 발생했습니다.');
                    }
                    else {
                    }
                }
                ThumnailComponent.prototype.return_uuid = function () {
                    return this.uid.uuid;
                };
                ThumnailComponent.prototype.make_shortURL = function () {
                    var _this = this;
                    console.log("uuid 받음." + this.uid.uuid);
                    var xhr = new XMLHttpRequest();
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
                    this.re.geturl(this.uid.uuid).subscribe(function (res) {
                        if (res._body.result == 1) {
                            console.log('start');
                            var url = _this.setting.hostUrl + '/api/v2/users/content/count?countCode=redirect,' + res._body.msg + '&uuid=' + _this.uid.uuid;
                            xhr.open('PUT', url, true);
                            xhr.send(null);
                            console.log('end');
                        }
                    });
                };
                ThumnailComponent = __decorate([
                    core_1.Component({
                        selector: 'thumnail',
                        templateUrl: 'src/components/thumnail/thumnail.html',
                        providers: [redirectURL_1.Redirect, setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [redirectURL_1.Redirect, setting_1.Setting, router_2.RouteParams])
                ], ThumnailComponent);
                return ThumnailComponent;
            })();
            exports_1("ThumnailComponent", ThumnailComponent);
        }
    }
});
