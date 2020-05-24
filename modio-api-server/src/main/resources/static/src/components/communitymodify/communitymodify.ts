import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {User} from './../../model/user';
import {RouteParams} from 'angular2/router';

@Component({
	selector: 'communitymodify',
	templateUrl: 'src/components/communitymodify/communitymodify.html',
	providers: [Dribbble, Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class CommunityModifyComponent implements OnInit, OnChanges {
	
	rp;
	mbid;
	title;
	rmk;
	
	constructor(dl: Dribbble, setting:Setting, rp:RouteParams){
		this.rp = rp;
		this.setting = setting ;
       this.setting.accessToken = localStorage.getItem("accessToken") ;
        
       var xhr = new XMLHttpRequest();
       var url = this.setting.hostUrl + '/api/v2/users/content/board?sn='+this.rp.params.id+'&title=null&rmk=null&startPoint=0&endPoint=0&accessToken=fff';

		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					this.title = JSON.parse(xhr.response)._title;
					document.getElementById("title").value = this.title;
					this.mbid = JSON.parse(xhr.response)._mbId;
					this.rmk = JSON.parse(xhr.response)._rmk;
					document.getElementById("rmk").value = this.rmk;
					console.log(JSON.parse(xhr.response));
				} 
				else {
					console.log(xhr.response);
	 	    	}
			}
		};
	
		xhr.open('GET', url, true);
		xhr.send(null);
	}
	
	onupdate(){
		
		if (confirm("정말 수정하시겠습니까??") == true){
			this.title = document.getElementById("title").value;
			this.rmk = document.getElementById("rmk").value;
			
			var xhr = new XMLHttpRequest();
       	var url = this.setting.hostUrl + '/api/v2/users/content/board?sn='+this.rp.params.id+'&title='+this.title+'&rmk='+this.rmk+'&startPoint=0&endPoint=0&accessToken=fff';

			xhr.onreadystatechange = () => {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						console.log(JSON.parse(xhr.response));
						window.location.href = "/#/communityview?id="+this.rp.params.id;
					} 
					else {
						console.log(xhr.response);
	 	    		}
				}
			};
			xhr.open('PUT', url, true);
			xhr.send(null);
			
		}else{   //취소
    		return;
		}
	}
	
	onback(){
		window.location.href = "/#/communityview?id="+this.rp.params.id ;
	}
}
