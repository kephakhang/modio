import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {User} from './../../model/user';
import {RouteParams} from 'angular2/router';


@Component({
	selector: 'communityview',
	templateUrl: 'src/components/communityview/communityview.html',
	providers: [Dribbble, Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class CommunityviewComponent implements OnInit, OnChanges {
	sn;
	mbid;
	title;
	rmk;

	constructor(dl: Dribbble, setting:Setting, rp:RouteParams){
    	 this.sn = rp;
        this.setting = setting ;
        this.setting.accessToken = localStorage.getItem("accessToken") ;
        
        console.log('sn : ' + this.sn.params.id);
        
        var xhr = new XMLHttpRequest();
        var url = this.setting.hostUrl + '/api/v2/users/content/board?sn='+this.sn.params.id+'&title=null&rmk=null&startPoint=0&endPoint=0&accessToken=fff';

		 xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					this.title = JSON.parse(xhr.response)._title;
					this.mbid = JSON.parse(xhr.response)._mbId;
					this.rmk = JSON.parse(xhr.response)._rmk;
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
    	window.location.href = "/#/communitymodify?id="+this.sn.params.id ;
    }
    
    ondelete(){

    	 var xhr = new XMLHttpRequest();
        var url = this.setting.hostUrl + '/api/v2/users/content/board?sn='+this.sn.params.id+'&title=null&rmk=null&startPoint=0&endPoint=0&accessToken=fff';

		 xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					this.title = JSON.parse(xhr.response)._title;
					this.mbid = JSON.parse(xhr.response)._mbId;
					this.rmk = JSON.parse(xhr.response)._rmk;
					console.log(JSON.parse(xhr.response));
					alert('삭제를 완료하였습니다.');
					window.location.href = "/#/community" ;
				} 
				else {
					console.log(xhr.response);
		 	    }
			}
		};
	
		xhr.open('DELETE', url, true);
		xhr.send(null);

    }
    
}
