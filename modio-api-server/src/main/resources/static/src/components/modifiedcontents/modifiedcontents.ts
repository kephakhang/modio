import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router';
import {Setting} from './../../services/setting';
import {Content} from './../../model/content';
import {Uuid} from './../../model/uuid';
import {RouteParams} from 'angular2/router';

@Component({
	selector: 'modifiedcontents',
	templateUrl: 'src/components/modifiedcontents/modifiedcontents.html',
	providers: [Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class ModifiedcontentsComponent implements OnInit, OnChanges {
	categories = [
	/*
		 { id: 1 , name:"사람"},
		 { id: 2 , name:"게임"},
		 { id: 3 , name:"사물"},
		 { id: 4 , name:"캐릭터"},
		 { id: 5 , name:"동물"},
		 { id: 6 , name:"자동차"},
		 { id: 7 , name:"문화유산"},
		 { id: 8 , name:"집/건물"}
	*/
		{ id: 1 , name:"사람"},
		{ id: 2 , name:"사물"}
	];

    model =  new Content('', '', '', 1, '', '');		
	setting ;
	rp;
	OriginalSuccess_link;
	OriginalPdf_link;
	OriginalFail_link;
	OriginalFail_email;
	original_result;
	result_trxid;
	temp_id; // 2016년 12월 14일 추가 admin 계정 수정 시, 원본 제작자가 아닌 admin계정으로 등록되는 문제로 생성.
	
	constructor(setting:Setting, rp:RouteParams) {
		this.setting = setting ;
		this.rp = rp;
		this.model.mbId = localStorage.getItem('id') ;
		this.model.accessToken = localStorage.getItem('accessToken') ;
		
		if( this.model.mbId === undefined || this.model.mbId ===  null ||
			this.model.accessToken === undefined || this.model.accessToken === null ) {
			
			alert("로그인 되지 않았거나, 세션 유효기간이 지났습니다. 재 로그인 후 사용해 주십시요 ") ;
			window.location.href = "/" ;
		}
		
		console.log(rp.params);
		
		this.model.caId = rp.params.category;
		document.getElementById("title").setAttribute("value", rp.params.title);
		document.getElementById("rmk").value = rp.params.rmk;
		
		//원본증명이 되어있으면 체크 할 필요가 없으므로, 0일 경우만 체크 박스의 값을 표시한다.
//		if(rp.params.orig_status == "0")
//			document.getElementById("original_check").checked = false;
		
	}

	onSubmit() { 
		this.temp_id = this.rp.params.user_id;
		
		
		// localStorage.getItem("id") 부분 변경. (2016년 12월 14일 장재민)
		//원본증명이 이미 된 콘텐츠의 경우 원본 증명 로직을 수행 할 필요가 없음.
		if(this.rp.params.orig_status == "1") {
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
	
			modified_xhr.onreadystatechange = () => {
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
		
		// localStorage.getItem("id") 부분 변경. (2016년 12월 14일 장재민)
		//원본 증명 체크를 하지 않은 경우는 원본 증명 로직을 수행할 필요가 없으므로, 그냥 수정 요청을 한다.
		else if(document.getElementById("original_check").checked == false){
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
	
			modified_xhr.onreadystatechange = () => {
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
		// localStorage.getItem("id") 부분 변경. (2016년 12월 14일 장재민)
		//원본 증명 시작 및 모달 시작
		else if (document.getElementById("original_check").checked == true) {
			//데이터 수정 요청
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
	
			modified_xhr.onreadystatechange = () => {
				if (modified_xhr.readyState === 4) {
					if (modified_xhr.status === 200) {
						console.log("modified_xhr");
						console.log(JSON.parse(modified_xhr.response));
						
						if(this.original_result == true) {
							//모달에 적힐 메시지 셋업
							this.OriginalSuccess_link = 'https://live.blockcypher.com/btc-testnet/tx/' + this.result_trxid;	
							this.OriginalPdf_link = this.setting.ssntUrl + '/ssnt/certificate?id=' + this.result_trxid;				
							
							//모달 팝업 on 	
							$('#OriginalSuccess').modal('show');
								
							//모달이 없어질 경우 발생.
							$('#OriginalSuccess').on('hide.bs.modal', function () {
								alert('수정에 성공했습니다.');
								window.location.href = "/";
							})
						}
						else {
							var xhr = new XMLHttpRequest();
					        var url = this.setting.hostUrl + '/api/v2/users/content/original?uuid=' + this.result_trxid + '&accessToken=' + localStorage.getItem("accessToken");
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
										$('#OriginalFail').on('hide.bs.modal', function () {
											alert('수정에 성공했습니다.');
											window.location.href = "/";
										})
										
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
		
			//원본증명 결과 업로드 
		    var orig_update_xhr = new XMLHttpRequest();
		    orig_update_xhr.onreadystatechange = () => {
				if (orig_update_xhr.readyState === 4) {
					if (orig_update_xhr.status === 200) {
						console.log("orig_update_xhr");
						console.log(JSON.parse(orig_update_xhr.response));
						
						//업로드에 성공하면 데이터 수정 요청
						modified_xhr.open('PUT', modified_url, true);
						modified_xhr.send(null);	
					}
					else {
						alert(orig_update_xhr.response);
						return;
					}
				}
			};
		
			//원본 증명 처리 시작.
			var xhr = new XMLHttpRequest();
			var url = 'http://119.195.163.135:3390/ssnt/origRequest';
			xhr.onreadystatechange = () => {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
					  	console.log('원본증명처리 성공');
					    console.log(JSON.parse(xhr.response));
					    res = JSON.parse(xhr.response);
						console.log('result : ' + res.result);
						console.log('trxid : ' + res.trxid);
						console.log('msg : ' + res.msg);
						                    
						//return 데이터 처리
						//정상이므로 DB에 데이터 upload
						if(res.result == "1") {
							//xhr.response.trxid; 업로드 데이터
						    set = new Setting();
						    console.log('set.hostUrl' + set.hostUrl);
												
						    var orig_update_data =  'uuid=' + this.rp.params.uuid + 
										       		'&origStatus=' + res.result + 
										       		'&origKey=' + res.trxid + 
										       		'&accessToken=' + localStorage.getItem("accessToken");
							var orig_update_url = set.hostUrl + '/api/v2/users/content/original?' + orig_update_data;
										       						
						    orig_update_xhr.open('PUT', orig_update_url, true);
						    orig_update_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
						    console.log(orig_update_data);
						    orig_update_xhr.send(null);
						    
						    this.original_result = true;
						    this.result_trxid = res.trxid;
							
						}
						//요청이 오류라고 알림.
						else if(res.result == "0") {
							alert("원본 등록 요청에 오류가 발생했습니다. 다시 시도해주세요." + res.msg);
						}
						//이미 원본증명된 파일이라고 알리고 진행처리.
						else if(res.result == "9") {
							console.log('https://live.blockcypher.com/btc-testnet/tx/' + res.trxid);
							//xhr.response.trxid; 업로드 데이터
						    set = new Setting();
						    console.log('set.hostUrl' + set.hostUrl);
												
						    var orig_update_data =  'uuid=' + this.rp.params.uuid + 
										       		'&origStatus=' + 0 + 
										       		'&origKey=' + null + 
										       		'&accessToken=' + localStorage.getItem("accessToken");
							var orig_update_url = set.hostUrl + '/api/v2/users/content/original?' + orig_update_data;
										       						
						    orig_update_xhr.open('PUT', orig_update_url, true);
						    orig_update_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
						    console.log(orig_update_data);
						    orig_update_xhr.send(null);
							
							this.original_result = false;
							this.result_trxid = res.trxid;
						}      
					}
					else {
						alert(xhr.response);
						return;
					}
				}
			};
			
			//uuid 콘텐츠가 속한 디렉토리(yyyymm) 데이터를 얻어오는 API
			var yymm_xhr = new XMLHttpRequest();
			var yymm_url = this.setting.hostUrl + '/api/v2/content/getyyyymm/' + this.rp.params.uuid;
			yymm_xhr.onreadystatechange = () => {
				if (yymm_xhr.readyState === 4) {
					if (yymm_xhr.status === 200) {
						console.log("yymm : " + yymm_xhr.response);
					
						//디렉토리 정보를 얻어오면 원본증명 요청을 날린다
						xhr.open('POST', url, true);
						xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
						var par = 'sn=' + this.rp.params.sn + '&uuid=' + this.rp.params.uuid + this.rp.params.extend + '&yymm=' + yymm_xhr.response;
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
		
		
		return false ;
	}	
}
