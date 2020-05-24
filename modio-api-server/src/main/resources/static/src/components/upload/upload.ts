import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router';
import {FileUploadService} from './../../services/fileUploadService';
import {Setting} from './../../services/setting';
import {Content} from './../../model/content';
import {Uuid} from './../../model/uuid';
import {Progress} from '../progress/progress';
import {Bar} from '../progress/bar';
import {Progressbar} from '../progress/progressbar';


@Component({
	selector: 'upload',
	templateUrl: 'src/components/upload/upload.html',
	providers: [FileUploadService, Setting],
    directives: [Progress, Bar, Progressbar, CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class UploadComponent implements AfterViewChecked {
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

    model =  new Content('', '', '', 1, '', '', 'true');		
	contentUploader ;
	setting ;
	
	constructor(fuploader:FileUploadService, setting:Setting) {
		this.contentUploader = fuploader ;
		this.setting = setting ;
		
		this.model.mbId = localStorage.getItem('id') ;
		this.model.accessToken = localStorage.getItem('accessToken') ;
		
		if( this.model.mbId === undefined || this.model.mbId ===  null ||
			this.model.accessToken === undefined || this.model.accessToken === null ) {
			
			alert("로그인 되지 않았거나, 세션 유효기간이 지났습니다. 재 로그인 후 사용해 주십시요 ") ;
			window.location.href = "/" ;
		}
	}

	submitted = false;

	// TODO: Remove this when we're done
	get diagnostic() { return JSON.stringify(this.model); }

	// Reset the form with a new hero AND restore 'pristine' class state
	// by toggling 'active' flag which causes the form
	// to be removed/re-added in a tick via NgIf
	// TODO: Workaround until NgForm has a reset method (#6822)
	active = true;

	// submit 최종 폼체크
	file ;
    selectFile($event): void {
		var inputValue = $event.target;
		this.file = inputValue.files[0];
		console.debug("Input File name: " + this.file.name + " type:" + this.file.size + " size:" + this.file.size);
	}

    bWrest = false ;
    ngAfterViewChecked() {
		
    	if( this.bWrest == false ) {
    		wrestInitialized();
    		this.bWrest = true ;
    	}
	}

	onSubmit() { 
			//로딩 화면 지움
    	  	var loading_view = document.getElementById("delay");
    	  	loading_view.style.display = "block"; 
    	  	
       	 	//2016.10.11 이중길 추가. 뷰어로 바로 넘어가지 않고 등록버튼은 비활성화.
			var converterBtn = document.getElementById("converter_btn");
			converterBtn.disabled = true;
    	  	//this.contentUploader.upload2("http://yally.net:8080/api/v2/users/content/upload", 
    	  	this.contentUploader.upload2(this.setting.hostUrl+"/api/v2/users/content/upload", 
								this.file, 
								this.model.mbId,
								this.model.caId, 
								this.model.title, 
								this.model.rmk,
								this.model.open,
								this.setting.accessToken)
					.then(function(response){
						console.log(response);
						if( response === null || typeof response === 'undefined' ) {
							
							return false ;
						}
						else {
							var loading_view_2 = document.getElementById("delay");
    	  					loading_view_2.style.display = "none";
    	  				
            				var converterBtn = document.getElementById("converter_btn");
		         			converterBtn.disabled = false;
		         
		         				//원본증명 여부 체크박스 점검. 디폴트는 체크되어있지 않다.
		         			original_checkbox = document.getElementById("original_check");
		         			console.log("check : " + original_checkbox.checked);
		         			
		         			if(original_checkbox.checked == true) {		         			
		         					//원본증명 결과 업로드 
		         				var orig_update_xhr = new XMLHttpRequest();
		         				
		         				orig_update_xhr.onreadystatechange = () => {
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
						                    	var strarr = response.message.split("/");
						                    	set = new Setting();
						         				console.log('set.hostUrl' + set.hostUrl);
												
						       					var orig_update_data =  'uuid=' + strarr[1].substring(0, strarr[1].length-4) + 
										       						'&origStatus=' + res.result + 
										       						'&origKey=' + res.trxid + 
										       						'&accessToken=' + localStorage.getItem("accessToken");
										       	var orig_update_url = set.hostUrl + '/api/v2/users/content/original?' + orig_update_data;
										       						
						                    	orig_update_xhr.open('PUT', orig_update_url, true);
						       					orig_update_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
						       					console.log(orig_update_data);
						       					orig_update_xhr.send(null);
						                    }
						                    	//요청이 오류라고 알림.
						                    else if(res.result == "0") {
						                    	alert("원본 등록 요청에 오류가 발생했습니다. " + res.msg);
						                    }
							                  //이미 원본증명된 파일이라고 알리고 진행처리.
						                    else if(res.result == "9") {
						                    	console.log('https://live.blockcypher.com/btc-testnet/tx/' + res.trxid);
						                    }      
							                  //썸네일 추가 뷰로 이동합니다
											setting = new Setting();
											var strarr = response.message.split("/");
											window.location.href = setting.hostUrl+"/#/thumnail?uuid=" + strarr[1].substring(0, strarr[1].length-4) + '&result=' + res.result + '&trxid=' + res.trxid;             
						                    	
						                    	
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
       							//2016.10.11 이중길 추가. 썸네일 추가 뷰로 이동합니다
								setting = new Setting();
								
								var strarr = response.message.split("/");
								window.location.href = setting.hostUrl+"/#/thumnail?uuid=" + strarr[1].substring(0, strarr[1].length-4);
       						}
										
							return ;
						}
					},function(response){
						if( response === null || typeof response === 'undefined' ) {
							
							return false ;
						}
						else {
							
							alert(response.message) ;
							return ;
						}
					})
					.catch(function(err){
						
						alert(err) ;
					}) ;
		
		return false ;
	}	
	
	/**
	 * @param fileInput
	 
	public psdTemplateSelectionHandler (fileInput: any){
	    let FileList: FileList = fileInput.target.files;

	    for (let i = 0, length = FileList.length; i < length; i++) {
	        this.psdTemplates.push(FileList.item(i));
	    }

	    this.progressBarVisibility = true;
	}

	public async psdTemplateUploadHandler (): Promise<any> {
	    let result: any;

	    if (!this.psdTemplates.length) {
	        return;
	    }

	    this.isSubmitted = true;

	    this.fileUploadService.getObserver()
	        .subscribe(progress => {
	            this.uploadProgress = progress;
	        });

	    try {
	        result = await this.fileUploadService.upload(this.uploadRoute, this.psdTemplates);
	    } catch (error) {
	        document.write(error)
	    }

	    if (!result['images']) {
	        return;
	    }

	    this.saveUploadedTemplatesData(result['images']);
	    this.redirectService.redirect(this.redirectRoute);
	}
	*/
}
