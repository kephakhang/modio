System.register(['angular2/core', 'angular2/common', 'angular2/router', './../../services/fileUploadService', './../../services/setting', './../../model/content', '../progress/progress', '../progress/bar', '../progress/progressbar'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, router_1, fileUploadService_1, setting_1, content_1, progress_1, bar_1, progressbar_1;
    var UploadComponent;
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
            function (fileUploadService_1_1) {
                fileUploadService_1 = fileUploadService_1_1;
            },
            function (setting_1_1) {
                setting_1 = setting_1_1;
            },
            function (content_1_1) {
                content_1 = content_1_1;
            },
            function (progress_1_1) {
                progress_1 = progress_1_1;
            },
            function (bar_1_1) {
                bar_1 = bar_1_1;
            },
            function (progressbar_1_1) {
                progressbar_1 = progressbar_1_1;
            }],
        execute: function() {
            UploadComponent = (function () {
                function UploadComponent(fuploader, setting) {
                    this.categories = [
                        { id: 1, name: "사람" },
                        { id: 2, name: "사물" }
                    ];
                    this.model = new content_1.Content('', '', '', 1, '', '', 'true');
                    this.submitted = false;
                    this.active = true;
                    this.bWrest = false;
                    this.contentUploader = fuploader;
                    this.setting = setting;
                    this.model.mbId = localStorage.getItem('id');
                    this.model.accessToken = localStorage.getItem('accessToken');
                    if (this.model.mbId === undefined || this.model.mbId === null ||
                        this.model.accessToken === undefined || this.model.accessToken === null) {
                        alert("로그인 되지 않았거나, 세션 유효기간이 지났습니다. 재 로그인 후 사용해 주십시요 ");
                        window.location.href = "/";
                    }
                }
                Object.defineProperty(UploadComponent.prototype, "diagnostic", {
                    get: function () { return JSON.stringify(this.model); },
                    enumerable: true,
                    configurable: true
                });
                UploadComponent.prototype.selectFile = function ($event) {
                    var inputValue = $event.target;
                    this.file = inputValue.files[0];
                    console.debug("Input File name: " + this.file.name + " type:" + this.file.size + " size:" + this.file.size);
                };
                UploadComponent.prototype.ngAfterViewChecked = function () {
                    if (this.bWrest == false) {
                        wrestInitialized();
                        this.bWrest = true;
                    }
                };
                UploadComponent.prototype.onSubmit = function () {
                    var loading_view = document.getElementById("delay");
                    loading_view.style.display = "block";
                    var converterBtn = document.getElementById("converter_btn");
                    converterBtn.disabled = true;
                    this.contentUploader.upload2(this.setting.hostUrl + "/api/v2/users/content/upload", this.file, this.model.mbId, this.model.caId, this.model.title, this.model.rmk, this.model.open, this.setting.accessToken)
                        .then(function (response) {
                        console.log(response);
                        if (response === null || typeof response === 'undefined') {
                            return false;
                        }
                        else {
                            var loading_view_2 = document.getElementById("delay");
                            loading_view_2.style.display = "none";
                            var converterBtn = document.getElementById("converter_btn");
                            converterBtn.disabled = false;
                            original_checkbox = document.getElementById("original_check");
                            console.log("check : " + original_checkbox.checked);
                            if (original_checkbox.checked == true) {
                                var orig_update_xhr = new XMLHttpRequest();
                                orig_update_xhr.onreadystatechange = function () {
                                    if (orig_update_xhr.readyState === 4) {
                                        if (orig_update_xhr.status === 200) {
                                            console.log(JSON.parse(orig_update_xhr.response));
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
                                                var strarr = response.message.split("/");
                                                set = new setting_1.Setting();
                                                console.log('set.hostUrl' + set.hostUrl);
                                                var orig_update_data = 'uuid=' + strarr[1].substring(0, strarr[1].length - 4) +
                                                    '&origStatus=' + res.result +
                                                    '&origKey=' + res.trxid +
                                                    '&accessToken=' + localStorage.getItem("accessToken");
                                                var orig_update_url = set.hostUrl + '/api/v2/users/content/original?' + orig_update_data;
                                                orig_update_xhr.open('PUT', orig_update_url, true);
                                                orig_update_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                console.log(orig_update_data);
                                                orig_update_xhr.send(null);
                                            }
                                            else if (res.result == "0") {
                                                alert("원본 등록 요청에 오류가 발생했습니다. " + res.msg);
                                            }
                                            else if (res.result == "9") {
                                                console.log('https://live.blockcypher.com/btc-testnet/tx/' + res.trxid);
                                            }
                                            setting = new setting_1.Setting();
                                            var strarr = response.message.split("/");
                                            window.location.href = setting.hostUrl + "/#/thumnail?uuid=" + strarr[1].substring(0, strarr[1].length - 4) + '&result=' + res.result + '&trxid=' + res.trxid;
                                        }
                                        else {
                                            alert(xhr.response);
                                            return;
                                        }
                                    }
                                };
                                xhr.open('POST', url, true);
                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                var strarr = response.message.split("/");
                                var par = 'sn=' + response.id + '&uuid=' + strarr[1] + '&yymm=' + strarr[0];
                                console.log("par.. " + par);
                                xhr.send(par);
                            }
                            else {
                                setting = new setting_1.Setting();
                                var strarr = response.message.split("/");
                                window.location.href = setting.hostUrl + "/#/thumnail?uuid=" + strarr[1].substring(0, strarr[1].length - 4);
                            }
                            return;
                        }
                    }, function (response) {
                        if (response === null || typeof response === 'undefined') {
                            return false;
                        }
                        else {
                            alert(response.message);
                            return;
                        }
                    })
                        .catch(function (err) {
                        alert(err);
                    });
                    return false;
                };
                UploadComponent = __decorate([
                    core_1.Component({
                        selector: 'upload',
                        templateUrl: 'src/components/upload/upload.html',
                        providers: [fileUploadService_1.FileUploadService, setting_1.Setting],
                        directives: [progress_1.Progress, bar_1.Bar, progressbar_1.Progressbar, common_1.CORE_DIRECTIVES, common_1.FORM_DIRECTIVES, router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [fileUploadService_1.FileUploadService, setting_1.Setting])
                ], UploadComponent);
                return UploadComponent;
            })();
            exports_1("UploadComponent", UploadComponent);
        }
    }
});
