System.register(['angular2/core', 'angular2/common', 'angular2/router', './../../services/setting', './../../model/content'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, router_1, setting_1, content_1, router_2;
    var ModifiedcontentsComponent;
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
            },
            function (content_1_1) {
                content_1 = content_1_1;
            }],
        execute: function() {
            ModifiedcontentsComponent = (function () {
                function ModifiedcontentsComponent(setting, rp) {
                    this.categories = [
                        { id: 1, name: "사람" },
                        { id: 2, name: "사물" }
                    ];
                    this.model = new content_1.Content('', '', '', 1, '', '');
                    this.setting = setting;
                    this.rp = rp;
                    this.model.mbId = localStorage.getItem('id');
                    this.model.accessToken = localStorage.getItem('accessToken');
                    if (this.model.mbId === undefined || this.model.mbId === null ||
                        this.model.accessToken === undefined || this.model.accessToken === null) {
                        alert("로그인 되지 않았거나, 세션 유효기간이 지났습니다. 재 로그인 후 사용해 주십시요 ");
                        window.location.href = "/";
                    }
                    console.log(rp.params);
                    this.model.caId = rp.params.category;
                    document.getElementById("title").setAttribute("value", rp.params.title);
                    document.getElementById("rmk").value = rp.params.rmk;
                }
                ModifiedcontentsComponent.prototype.onSubmit = function () {
                    var _this = this;
                    this.temp_id = this.rp.params.user_id;
                    if (this.rp.params.orig_status == "1") {
                        var modified_xhr = new XMLHttpRequest();
                        var modified_url = this.setting.hostUrl +
                            '/api/v2/users/content' +
                            '?uuid=' + this.rp.params.uuid +
                            '&mbId=' + this.temp_id +
                            '&caId=' + this.model.caId +
                            '&title=' + document.getElementById("title").value +
                            '&rmk=' + document.getElementById("rmk").value +
                            '&accessToken=' + localStorage.getItem('accessToken');
                        console.log(url);
                        modified_xhr.onreadystatechange = function () {
                            if (modified_xhr.readyState === 4) {
                                if (modified_xhr.status === 200) {
                                    console.log(JSON.parse(modified_xhr.response));
                                    alert('수정에 성공했습니다.');
                                    window.location.href = "/";
                                }
                                else {
                                    console.log(modified_xhr.response);
                                }
                            }
                        };
                        modified_xhr.open('PUT', modified_url, true);
                        modified_xhr.send(null);
                    }
                    else if (document.getElementById("original_check").checked == false) {
                        var modified_xhr = new XMLHttpRequest();
                        var modified_url = this.setting.hostUrl +
                            '/api/v2/users/content' +
                            '?uuid=' + this.rp.params.uuid +
                            '&mbId=' + this.temp_id +
                            '&caId=' + this.model.caId +
                            '&title=' + document.getElementById("title").value +
                            '&rmk=' + document.getElementById("rmk").value +
                            '&accessToken=' + localStorage.getItem('accessToken');
                        console.log(modified_url);
                        modified_xhr.onreadystatechange = function () {
                            if (modified_xhr.readyState === 4) {
                                if (modified_xhr.status === 200) {
                                    console.log(JSON.parse(modified_xhr.response));
                                    alert('수정에 성공했습니다.');
                                    window.location.href = "/";
                                }
                                else {
                                    console.log(modified_xhr.response);
                                }
                            }
                        };
                        modified_xhr.open('PUT', modified_url, true);
                        modified_xhr.send(null);
                    }
                    else if (document.getElementById("original_check").checked == true) {
                        var modified_xhr = new XMLHttpRequest();
                        var modified_url = this.setting.hostUrl +
                            '/api/v2/users/content' +
                            '?uuid=' + this.rp.params.uuid +
                            '&mbId=' + this.temp_id +
                            '&caId=' + this.model.caId +
                            '&title=' + document.getElementById("title").value +
                            '&rmk=' + document.getElementById("rmk").value +
                            '&accessToken=' + localStorage.getItem('accessToken');
                        console.log("modified_url : " + modified_url);
                        modified_xhr.onreadystatechange = function () {
                            if (modified_xhr.readyState === 4) {
                                if (modified_xhr.status === 200) {
                                    console.log("modified_xhr");
                                    console.log(JSON.parse(modified_xhr.response));
                                    if (_this.original_result == true) {
                                        _this.OriginalSuccess_link = 'https://live.blockcypher.com/btc-testnet/tx/' + _this.result_trxid;
                                        _this.OriginalPdf_link = _this.setting.ssntUrl + '/ssnt/certificate?id=' + _this.result_trxid;
                                        $('#OriginalSuccess').modal('show');
                                        $('#OriginalSuccess').on('hide.bs.modal', function () {
                                            alert('수정에 성공했습니다.');
                                            window.location.href = "/";
                                        });
                                    }
                                    else {
                                        var xhr = new XMLHttpRequest();
                                        var url = _this.setting.hostUrl + '/api/v2/users/content/original?uuid=' + _this.result_trxid + '&accessToken=' + localStorage.getItem("accessToken");
                                        xhr.onreadystatechange = function () {
                                            if (xhr.readyState === 4) {
                                                if (xhr.status === 200) {
                                                    var response_json = JSON.parse(xhr.response);
                                                    console.log(response_json);
                                                    _this.OriginalFail_link = _this.setting.hostUrl + '/#/snaps/' + response_json.uuid;
                                                    _this.OriginalFail_email = response_json.email;
                                                    $('#OriginalFail').modal('show');
                                                    $('#OriginalFail').on('hide.bs.modal', function () {
                                                        alert('수정에 성공했습니다.');
                                                        window.location.href = "/";
                                                    });
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
                                }
                                else {
                                    console.log(modified_xhr.response);
                                }
                            }
                        };
                        var orig_update_xhr = new XMLHttpRequest();
                        orig_update_xhr.onreadystatechange = function () {
                            if (orig_update_xhr.readyState === 4) {
                                if (orig_update_xhr.status === 200) {
                                    console.log("orig_update_xhr");
                                    console.log(JSON.parse(orig_update_xhr.response));
                                    modified_xhr.open('PUT', modified_url, true);
                                    modified_xhr.send(null);
                                }
                                else {
                                    alert(orig_update_xhr.response);
                                    return;
                                }
                            }
                        };
                        var xhr = new XMLHttpRequest();
                        var url = 'http://119.195.163.135:3390/ssnt/origRequest';
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === 4) {
                                if (xhr.status === 200) {
                                    console.log('원본증명처리 성공');
                                    console.log(JSON.parse(xhr.response));
                                    res = JSON.parse(xhr.response);
                                    console.log('result : ' + res.result);
                                    console.log('trxid : ' + res.trxid);
                                    console.log('msg : ' + res.msg);
                                    if (res.result == "1") {
                                        set = new setting_1.Setting();
                                        console.log('set.hostUrl' + set.hostUrl);
                                        var orig_update_data = 'uuid=' + _this.rp.params.uuid +
                                            '&origStatus=' + res.result +
                                            '&origKey=' + res.trxid +
                                            '&accessToken=' + localStorage.getItem("accessToken");
                                        var orig_update_url = set.hostUrl + '/api/v2/users/content/original?' + orig_update_data;
                                        orig_update_xhr.open('PUT', orig_update_url, true);
                                        orig_update_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                        console.log(orig_update_data);
                                        orig_update_xhr.send(null);
                                        _this.original_result = true;
                                        _this.result_trxid = res.trxid;
                                    }
                                    else if (res.result == "0") {
                                        alert("원본 등록 요청에 오류가 발생했습니다. 다시 시도해주세요." + res.msg);
                                    }
                                    else if (res.result == "9") {
                                        console.log('https://live.blockcypher.com/btc-testnet/tx/' + res.trxid);
                                        set = new setting_1.Setting();
                                        console.log('set.hostUrl' + set.hostUrl);
                                        var orig_update_data = 'uuid=' + _this.rp.params.uuid +
                                            '&origStatus=' + 0 +
                                            '&origKey=' + null +
                                            '&accessToken=' + localStorage.getItem("accessToken");
                                        var orig_update_url = set.hostUrl + '/api/v2/users/content/original?' + orig_update_data;
                                        orig_update_xhr.open('PUT', orig_update_url, true);
                                        orig_update_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                        console.log(orig_update_data);
                                        orig_update_xhr.send(null);
                                        _this.original_result = false;
                                        _this.result_trxid = res.trxid;
                                    }
                                }
                                else {
                                    alert(xhr.response);
                                    return;
                                }
                            }
                        };
                        var yymm_xhr = new XMLHttpRequest();
                        var yymm_url = this.setting.hostUrl + '/api/v2/content/getyyyymm/' + this.rp.params.uuid;
                        yymm_xhr.onreadystatechange = function () {
                            if (yymm_xhr.readyState === 4) {
                                if (yymm_xhr.status === 200) {
                                    console.log("yymm : " + yymm_xhr.response);
                                    xhr.open('POST', url, true);
                                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                    var par = 'sn=' + _this.rp.params.sn + '&uuid=' + _this.rp.params.uuid + _this.rp.params.extend + '&yymm=' + yymm_xhr.response;
                                    console.log("par.. " + par);
                                    xhr.send(par);
                                }
                                else {
                                    console.log("yymm error : " + yymm_xhr.response);
                                }
                            }
                        };
                        yymm_xhr.open('GET', yymm_url, true);
                        yymm_xhr.send(null);
                    }
                    else {
                    }
                    return false;
                };
                ModifiedcontentsComponent = __decorate([
                    core_1.Component({
                        selector: 'modifiedcontents',
                        templateUrl: 'src/components/modifiedcontents/modifiedcontents.html',
                        providers: [setting_1.Setting],
                        directives: [common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [setting_1.Setting, router_2.RouteParams])
                ], ModifiedcontentsComponent);
                return ModifiedcontentsComponent;
            })();
            exports_1("ModifiedcontentsComponent", ModifiedcontentsComponent);
        }
    }
});
