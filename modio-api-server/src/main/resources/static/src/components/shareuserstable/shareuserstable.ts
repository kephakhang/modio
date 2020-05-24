import {Component, Directive, OnChanges, OnInit, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router'
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {JSONP_PROVIDERS} from 'angular2/http'
import {Redirect} from './../../services/redirectURL';
import {RouteParams} from 'angular2/router';

@Component({
	selector: 'shareuserstable',
	templateUrl: 'src/components/shareuserstable/shareuserstable.html',
	providers: [Dribbble, Setting],
    directives: [ROUTER_DIRECTIVES]
})

export class ShareuserstableComponent implements OnInit, OnChanges{
    name: string = 'World';
    posts = new Array();
    dl:Dribbble;
    page = 0;
	setting ;
	id;
		
    constructor(dl: Dribbble, setting:Setting, params : RouteParams){
		this.setting = setting ;
        this.id = params;
        this.name = localStorage.getItem("id");
       
       
		var xhr = new XMLHttpRequest();
	    var url = this.setting.hostUrl + 
	      		'/api/v2/users/content/list?startPoint=0&endPoint=10&caId=' + '0' + 
	       		'&filter=' + '0' + 
	       		'&option=' + '5' + 
	       		'&optionParam=' + this.id.params.id;
	       		
        console.log(url);

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
    
    loadMore() {
        this.page++;
        
        var xhr = new XMLHttpRequest();
	
	    var url = this.setting.hostUrl + 
	      		'/api/v2/users/content/list?startPoint=' + this.page*10 + '&endPoint=10&caId=' + '0' + 
	       		'&filter=' + '0' + 
	       		'&option=' + '5' + 
	       		'&optionParam=' + this.id.params.id;
	    console.log(url);
			
		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					JSON.parse(xhr.response).forEach(post=> {
						this.posts.push(post);
					});
					
					console.log(JSON.parse(xhr.response) + this.page*18);
				} 
				else {
					console.log(xhr.response);
		 	    }
			}
		};
	
		xhr.open('GET', url, true);
		xhr.send(null);
    }

    ngOnInit() {   	
    	
    }
    

    ngOnChanges(changes) {
        // if the change happened in the collapse property
    
    }
}
