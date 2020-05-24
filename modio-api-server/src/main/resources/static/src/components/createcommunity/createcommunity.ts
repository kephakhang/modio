import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {User} from './../../model/user';
import {RouteParams} from 'angular2/router';

@Component({
	selector: 'createcommunity',
	templateUrl: 'src/components/createcommunity/createcommunity.html',
	providers: [Dribbble, Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class CreateCommunityComponent implements OnInit, OnChanges {

	name: string = 'World';
    posts = new Array();
    dl:Dribbble;
    setting ;
    router ;
    page = 0;
    title;
    rmk;
    user_id;
    
    constructor(dl: Dribbble, setting:Setting, router:Router){
    	 this.dl = dl;
        this.setting = setting ;
        this.router = router ;
        this.setting.accessToken = localStorage.getItem("accessToken") ;
    }

	onSubmit(){
		// 텍스트 값들과 id 값을 받아와 글을 등록하는 부분 (2016년 12월 14일 장재민)
		
		this.title = document.getElementById("title").value;
		this.rmk = document.getElementById("rmk").value;
		this.user_id = localStorage.getItem("id");

		var xhr = new XMLHttpRequest();
		var url = this.setting.hostUrl+'/api/v2/users/content/board?mb_id='+this.user_id+'&title='+this.title+'&rmk='+this.rmk+'&startPoint=0&endPoint=0&accessToken=d';
		
		xhr.onreadystatechange = () => {
          if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log(JSON.parse(xhr.response));
                    alert('등록에 성공하셨습니다.');
                    document.getElementById("title").value = '';
					   document.getElementById("rmk").value = '';
                    window.location.href = "/#/community" ;
                } 
                else {
                    console.log(xhr.response);
                }
           }
        };
		
		xhr.open('POST', url, true);
		xhr.setRequestHeader("Content-Type","text/html; charset=UTF-8");
		xhr.send(null);

	}
	
	onback(){
		document.getElementById("title").value = '';
		document.getElementById("rmk").value = '';
		window.location.href = "/#/community" ;
	}
}
