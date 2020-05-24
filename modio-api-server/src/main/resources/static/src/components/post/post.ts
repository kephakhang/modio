import {Component} from 'angular2/core';
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {RouteParams} from 'angular2/router';
import {Redirect} from './../../services/redirectURL';
import {ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router'


@Component({
	selector: 'post',
	templateUrl: 'src/components/post/post.html',
	providers: [Dribbble, Setting, Redirect],
	directives: [ROUTER_DIRECTIVES]
})
export class PostComponent {
	re:Redirect;
	embedded_code;
	post = new Array();
	sn;
	id;
	title;
	rmk;
	fname;
	fsize;
	email;
	rp;
	category;
	extend;
	open;

    view_count;
    buy_count;
    share_count;
    down_count;
    redirect_code;
    orig_status;
    orig_key;
    
    temp_id;  // 2016년 12월 14일 삭제 문제로 관리자 계정 변수 추가.
    
    dl ;
    setting ;
    constructor(re:Redirect, dl:Dribbble, rp:RouteParams, setting:Setting){
		/*
		dl.getPost(rp.params.id).subscribe(res => {
			this.post = res.json().data;
		});
		*/
		this.re = re;
    	this.setting = setting ;
		this.rp = rp;
		console.log(this.rp.params.id);
        
        //페이지 데이터 로드
        var xhr = new XMLHttpRequest();

        //url_1 = 'http://yally.net:8080/api/v2/users/content?id=' + this.rp.params.id;
        this.setting.id = localStorage.getItem("id");
        this.setting.accessToken = localStorage.getItem("accessToken") ;
   			console.log('accessToken : ' + this.setting.accessToken);
        var url_1 = this.setting.hostUrl + '/api/v2/users/content?id=' + this.rp.params.id + '&accessToken=' + this.setting.accessToken;

        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                	this.sn = JSON.parse(xhr.response).sn;
                	this.id = JSON.parse(xhr.response).id;
                    this.title = JSON.parse(xhr.response).title;
                    this.rmk = JSON.parse(xhr.response).rmk;
                    this.fname = JSON.parse(xhr.response).fname;
                    this.fsize = JSON.parse(xhr.response).fsize;
                    this.email = JSON.parse(xhr.response).mbId;
                    this.view_count = JSON.parse(xhr.response).viewCount;
                    this.buy_count = JSON.parse(xhr.response).buyCount;
                    this.share_count = JSON.parse(xhr.response).shareCount;
                    this.down_count = JSON.parse(xhr.response).downCount;
                    this.redirect_code = JSON.parse(xhr.response).redirectCode;
                    this.orig_status = JSON.parse(xhr.response).orig_status;
                    this.orig_key = JSON.parse(xhr.response).orig_key;
                    this.category = JSON.parse(xhr.response).caId;
                    this.extend = this.fname.substring(this.fname.length - 4, this.fname.length);
                    this.open = JSON.parse(xhr.response).open;
                    console.log(JSON.parse(xhr.response));
                } 
                else {
                    console.log(xhr.response);
                }
            }
        };


        //조회 수 증가. countCode 값은 1을 사용.
        var vhr = new XMLHttpRequest();

        //url = 'http://yally.net:8080/api/v2/users/content/count?countCode=1&uuid=' + this.rp.params.id;
        var url = this.setting.hostUrl + '/api/v2/users/content/count?countCode=1&uuid=' + this.rp.params.id;

        vhr.onreadystatechange = () => {
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

    //다운 수 증가 메소드. countCode 값은 2를 사용.
    down_count_up() {
        var xhr = new XMLHttpRequest();

        var url_1 = this.setting.hostUrl + '/api/v2/users/content/count?countCode=2&uuid=' + this.rp.params.id;

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
        xhr.open('PUT', url_1, true);
        xhr.send(null);

		//packing server access
		/*
		var packing_xhr = new XMLHttpRequest();
		var packing_url = 'http://119.195.163.135:3390/securesnt/packing.do';
		packing_xhr.onreadystatechange = () => {
            if (packing_xhr.readyState === 4) {
                if (packing_xhr.status === 200) {
                    console.log(JSON.parse(packing_xhr.response));
                    
	                  //다운 이력 등록
                    this.content_service_insertDB(3, null);
                    
                    //packing file download
                    var link = document.createElement("a");
                    var url = this.setting.hostUrl + '/api/v2/content/binary/' + this.rp.params.id ;
			        link.href = url;
			        link.click();
                } 
                else {
                    console.log(packing_xhr.response);
                }
            }
        };
        */
        
        var dir_xhr = new XMLHttpRequest();
        var dir_url = this.setting.hostUrl + '/api/v2/content/getyyyymm/' + this.rp.params.id ;
        dir_xhr.onreadystatechange = () => {
            if (dir_xhr.readyState === 4) {
                if (dir_xhr.status === 200) {
                    console.log(JSON.parse(dir_xhr.response));
                    
                    /*
                  	packing_xhr.open('POST', packing_url, true);
                  	packing_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                  	
                  	file_3 = this.fname.substring(this.fname.length-4, this.fname.length);
                  	post_url = 	"ownerId=" + this.email + 
			        			"&customerId=" + localStorage.getItem("id") + 
			        			"&sourceFileNo=" + this.sn + 
			        			"&sourceFilePath=/usr/local/solid3d/" + dir_xhr.response + 
			        			"&sourceFileName=" + this.id + file_3 + 
			        			"&viewYn=Y" +
			        			"&printYn=Y" + 
			        			"&editYn=Y" + 
			        			"&targetFilePath=/usr/local/solid3d/" + dir_xhr.response +  
			        			"&targetFileName=" + this.id + 
			        			"&password=1234" +
			        			"&isEncrypt=Y";
			        console.log(post_url);
        			packing_xhr.send(post_url);
        			*/
        			
  	      			//다운 이력 등록
                    this.content_service_insertDB(3, null);
        			
	        			//일반파일 다운로드
        			var link = document.createElement("a");
                    var url = this.setting.hostUrl + '/api/v2/content/binary/' + this.rp.params.id + '/' + this.fname.substring(this.fname.length - 3, this.fname.length);
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
    }

    //구매 수 증가 메소드. countCode 값은 4를 사용.
    buy_count_up() {
    	alert("준비중입니다");
    /*
        var buy_count_xhr = new XMLHttpRequest();
        var buy_count_url = this.setting.hostUrl + '/api/v2/users/content/count?countCode=4&uuid=' + this.rp.params.id;
        buy_count_xhr.onreadystatechange = () => {
            if (buy_count_xhr.readyState === 4) {
                if (buy_count_xhr.status === 200) {
                    console.log(JSON.parse(buy_count_xhr.response));
                } 
                else {
                    console.log(buy_count_xhr.response);
                }
            }
        };
        
        var buy_history_xhr = new XMLHttpRequest();
        var buy_history_url = this.setting.hostUrl + '/api/v2/users/content/payment';
        buy_history_xhr.onreadystatechange = () => {
            if (buy_history_xhr.readyState === 4) {
                if (buy_history_xhr.status === 200) {
                    console.log(JSON.parse(buy_history_xhr.response));
                    
	                  //구매 카운트 증가
                    buy_count_xhr.open('PUT', buy_count_url, true);
        			buy_count_xhr.send(null);
                } 
                else {
                    console.log(buy_history_xhr.response);
                }
            }
        };

        
        	//구매 여부 확인 절차
		var buy_check_box_value = confirm("해당 상품을 구매하시겠습니까? [ " + this.fname + " ]");
		
		//구매 승락
		if(buy_check_box_value == true) {
			
			buy_history_xhr.open('POST', buy_history_url, true);

			buy_history_xhr.setRequestHeader("Content-Type","application/json; charset=UTF-8");
	        
	        var buy_history_json = new Object();
          	buy_history_json.mbId = localStorage.getItem("id");
          	buy_history_json.accessToken = localStorage.getItem("accessToken");
         	buy_history_json.coId = this.sn;
          	buy_history_json.price = 3000;
			buy_history_json.regtime = new Date();
			
	        buy_history_xhr.send(JSON.stringify(buy_history_json));
	        
	        console.log(JSON.stringify(buy_history_json));
	        console.log("구매 승락");
		}
		//구매 취소
		else { 
			console.log("구매 취소");
		}
		

        console.log("buy_count_up");
	*/
    }

    //공유 수 증가 메소드. countCode 값은 3를 사용.
    share_count_up() {      
        var xhr = new XMLHttpRequest();

        //url = 'http://yally.net:8080/api/v2/users/content/count?countCode=3&uuid=' + this.rp.params.id;
        var url = this.setting.hostUrl + '/api/v2/users/content/count?countCode=3&uuid=' + this.rp.params.id;

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
      
        xhr.open('PUT', url, true);
        xhr.send(null);

        console.log("share_count_up");
        
//        prompt("생성된 Short URL을 복사하세요.", '3dz.kr?' + this.redirect_code);
    }
    
    content_service_insertDB(t : int, u : string) {
    	var xhr = new XMLHttpRequest();

        var url = this.setting.hostUrl + '/api/v2/users/content/share';

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
      
        xhr.open('POST', url, true);
        xhr.setRequestHeader("Content-Type","application/json; charset=UTF-8");
	        
	    var share_insert_json = new Object();
       	share_insert_json.mbId = localStorage.getItem("id");
       	share_insert_json.accessToken = localStorage.getItem("accessToken");
      	share_insert_json.caId = this.sn;
       	share_insert_json.type = t;
		share_insert_json.url = u;
		share_insert_json.regtime = new Date();
			
	    xhr.send(JSON.stringify(share_insert_json));

        console.log("share insert db");
    }
    
    share_Email() {
    	var xhr = new XMLHttpRequest();

        var url = this.setting.hostUrl + '/api/v2/content/emailshare?';
        
        var data = $('#EmailshareForm').serialize() ;
        
        url += data ;
        
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log(xhr.response);
                    alert("공유 URL 을 지정된 이메일로 발송하였습니다.") ;
                } 
                else {
                    console.log(xhr.response);
                    alert("이메일 공유 중 오류가 발생하였습니다.") ;
                }
            }
        };
      
        xhr.open('POST', url, true);
        xhr.setRequestHeader("Content-Type","text/html; charset=UTF-8");
			
	    xhr.send(null);

        console.log("email share !!!");
    }
    
    share_FaceBook() {
    	var rc;	
    	//CORS를 이용한 요청 수신
        this.re.geturl(this.rp.params.id + "||" + localStorage.getItem("id") + "||" + "FaceBook").subscribe(res => {
                if(res._body.result == 1) {
                	console.log(res._body.msg);
                	rc = res._body.msg;
					this.content_service_insertDB(1, "FaceBook");		
					this.share_count_up();
			    }
		});
		
		window.setTimeout(function() {
	        window.open(
				"https://www.facebook.com/sharer/sharer.php?u=http://3dz.kr?" + rc,
				"_blank", 
				"width=" + (window.innerWidth / 2)
				",height=" + (window.innerHeight / 2)
			);
	    }, 1000);
	}
	
	share_Twitter() {	
		var rc;
		//CORS를 이용한 요청 수신
        this.re.geturl(this.rp.params.id + "||" + localStorage.getItem("id") + "||" + "Twitter").subscribe(res => {
                if(res._body.result == 1) {
                	console.log(res._body.msg);
                	rc = res._body.msg;
					this.content_service_insertDB(1, "Twitter");
					this.share_count_up();
				}
		});
		
		window.setTimeout(function() {
	        window.open(
				"https://twitter.com/intent/tweet?text=http://3dz.kr?" + rc,
				"MsgWindow", 
				"width=" + window.innerWidth / 2 + 
				",height=" + window.innerHeight / 2
			);
	    }, 1000);
		
	}
	
	embedded_tag() {	
		//CORS를 이용한 요청 수신
        this.re.geturl(this.rp.params.id + "||" + localStorage.getItem("id") + "||" + "Embedded_Tag").subscribe(res => {
                if(res._body.result == 1) {
                	console.log(res._body.msg);
			//		this.embedded_code = "http://3dz.kr?" + res._body.msg;
					this.embedded_code = '<iframe id=\"viewer\" width=\"1000\" height=\"600\"  src=\"http://3dz.kr?' + res._body.msg + '\" frameborder=\"0\" allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" onmousewheel=\"\" allowtransparency=\"true\"></iframe>';
					this.content_service_insertDB(2, "Embedded_Tag");
					this.share_count_up();
				}
		});
	}
	
	
	delete_content() {
		
		if(localStorage.getItem("id") == 'admin@real3d.com'){
			this.temp_id = this.email;
			//alert(this.temp_id);
		}else{
			this.temp_id = localStorage.getItem("id");
		}
		
		var delete_content_value = confirm("해당 상품을 삭제하시겠습니까? [ " + this.fname + " ]");
		
		//삭제
		if(delete_content_value == true) {
			//delete db and contents file..
			
			var xhr = new XMLHttpRequest();

	        //var url = this.setting.hostUrl + '/api/v2/users/content' + '?mb_id=' + localStorage.getItem("id") + '&id=' + this.rp.params.id + '&accessToken=' + localStorage.getItem("accessToken");
			 
			 var url = this.setting.hostUrl + '/api/v2/users/content' + '?mb_id=' + this.temp_id + '&id=' + this.rp.params.id + '&accessToken=' + localStorage.getItem("accessToken");	
	        xhr.onreadystatechange = () => {
	            if (xhr.readyState === 4) {
	                if (xhr.status === 200) {
	                    alert("삭제에 성공했습니다.");
	                    window.location.href = "/" ;
	                }
	                else {
	                    console.log(xhr.response);
	                }
	            }
	        };
	      	
	        xhr.open('DELETE', url, true);
	        xhr.send(null);
	   		 
		}
		//취소
		else {
			//nothing
		}

	}
	
}
