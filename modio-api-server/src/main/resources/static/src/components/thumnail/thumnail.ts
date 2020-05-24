import {Component, Directive, OnChanges, OnInit, Input} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router'
import {FileUploadService} from './../../services/fileUploadService';
import {Uuid} from './../../model/uuid';
import {RouteParams} from 'angular2/router';
import {Redirect} from './../../services/redirectURL';
import {Setting} from './../../services/setting';

@Component({
    selector: 'thumnail',
    templateUrl: 'src/components/thumnail/thumnail.html',
    providers: [Redirect, Setting],
    directives: [ CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class ThumnailComponent {
    re:Redirect;
	setting;
	uid ;
	tid;
	res;
	OriginalSuccess_link;
	OriginalPdf_link;
	OriginalFail_link;
	OriginalFail_email;
	
    constructor(re:Redirect, setting:Setting, params: RouteParams){
        this.re = re;
        this.setting = setting ;
        this.uid = new Uuid('test');
         console.log('sdfadsfasdfasdfasfsas');
        console.log(params.get("uuid"));
        console.log(params.get("trxid"));
        console.log(params.get("result"));
        this.uid.uuid = params.get("uuid");
        console.log("uid : " + this.uid.uuid);
        
        //원본증명에 성공한 경우
		if(params.get("result") == "1") {
			this.OriginalSuccess_link = 'https://live.blockcypher.com/btc-testnet/tx/' + params.get("trxid");
			this.OriginalPdf_link = this.setting.ssntUrl + '/ssnt/certificate?id=' + params.get("trxid");
					
			//모달 팝업 on 	
			$('#OriginalSuccess').modal('show');
							
			//모달이 없어질 경우 발생.
			/*
			$('#OriginalFail').on('hide.bs.modal', function () {
			})
			*/
		}
		//원본증명이 이미 된 경우
		else if(params.get("result") == "9"){
			//원본증명 결과 get 요청 송신
	        var xhr = new XMLHttpRequest();
	        var url = this.setting.hostUrl + '/api/v2/users/content/original?uuid=' + params.get("trxid") + '&accessToken=' + localStorage.getItem("accessToken");
	        xhr.onreadystatechange = () => {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						var response_json = JSON.parse(xhr.response);
						console.log(response_json);
						this.OriginalFail_link = this.setting.hostUrl + '/#/snaps/' + response_json.uuid;
						this.OriginalFail_email = response_json.email;
									
						//모달 팝업 on 	
						$('#OriginalFail').modal('show');
										
						//모달이 없어질 경우 발생
						/*.
						$('#OriginalFail').on('hide.bs.modal', function () {
						})
						*/
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
		//원본 요청에 에러가 발생한 경우
		else if(params.get("result") == "0"){
			alert('원본증명 처리 도중 에러가 발생했습니다.');
		}
		//원본 요청하지 않은 경우   
		else {
		}    

    }

    return_uuid() {
        return this.uid.uuid;
    }

    make_shortURL() {
        console.log("uuid 받음." + this.uid.uuid);
         //redirect code 업데이트
        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log(JSON.parse(xhr.response));
              							  } 
                else {
                    console.log(xhr.response);
                						}
           					 }
        			};

		//CORS를 이용한 요청 수신
        this.re.geturl(this.uid.uuid).subscribe(res => {
                if(res._body.result == 1) {
                    console.log('start');
                   
                    var url = this.setting.hostUrl + '/api/v2/users/content/count?countCode=redirect,' + res._body.msg + '&uuid=' + this.uid.uuid;
                    
                    xhr.open('PUT', url, true);
                    xhr.send(null);
                    console.log('end');
			                }
			        });
    }    
}
