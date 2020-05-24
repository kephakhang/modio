import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {User} from './../../model/user';

@Component({
	selector: 'community',
	templateUrl: 'src/components/community/community.html',
	providers: [Dribbble, Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class CommunityComponent implements OnInit, OnChanges {
	
	name: string = 'World';
    posts = new Array();
    dl:Dribbble;
    setting ;
    router ;
    page = 0;

    constructor(dl: Dribbble, setting:Setting, router:Router){
    	
        this.dl = dl;
        this.setting = setting ;
        this.router = router ;
        this.setting.accessToken = localStorage.getItem("accessToken") ;
        /*
        this.dl.getPosts(this.page).subscribe(res => {
            this.posts = res.json().data;
        });
        */
        
        var xhr = new XMLHttpRequest();
	
		//url = 'http://yally.net:8080/api/v2/users/content/list?startPoint=0&endPoint=12&caId=0&filter=0';
		var url = this.setting.hostUrl + '/api/v2/users/content/boardlist?mb_id=null&title=null&rmk=null&startPoint=0&endPoint=12&accessToken=ddd';

		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					this.posts = JSON.parse(xhr.response);
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
    
    //게시글 더보기
    
   	loadMore() {
   	
       this.page++;
        
       var xhr = new XMLHttpRequest();

		var url = this.setting.hostUrl + '/api/v2/users/content/boardlist?mb_id=null&title=null&rmk=null&startPoint=' + this.page*12 + '&endPoint=12&accessToken=ddd';

		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					JSON.parse(xhr.response).forEach(post=> {
						this.posts.push(post);
					});
					
					console.log(JSON.parse(xhr.response) + this.page*12);
				} 
				else {
					console.log(xhr.response);
		 	    }
			}
		};
	
		xhr.open('GET', url, true);
		xhr.send(null);
    }

}
