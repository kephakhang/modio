import {Component, Directive, OnChanges, OnInit, Input} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router'
import {FileUploadService} from './../../services/fileUploadService';
import {RouteParams} from 'angular2/router';
import {Setting} from './../../services/setting';

@Component({
    selector: 'sharecontentstable',
    templateUrl: 'src/components/sharecontentstable/sharecontentstable.html',
    providers: [Setting],
    directives: [ CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class SharecontentstableComponent implements OnInit, OnChanges {
	
	posts = new Array();
	url;
	page = 0;
	
    constructor(setting:Setting, params: RouteParams){
        this.setting = setting;
        this.url = params;
        
		var xhr = new XMLHttpRequest();
		
		var url = this.setting.hostUrl + 
	      		'/api/v2/users/content/list?startPoint=0&endPoint=10&caId=' + '0' + 
	       		'&filter=' + '0' + 
	       		'&option=' + '4' + 
	       		'&optionParam=' + this.url.params.sn;
	       		
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

    return_uuid() {
        return this.url.params.id;
    }
    return_orig_status() {
    	return this.url.params.orig_status;
    }
    
    loadMore() {
        this.page++;
        
        var xhr = new XMLHttpRequest();
	
		var url = this.setting.hostUrl + 
	      		'/api/v2/users/content/list?startPoint=' + this.page*10 + '&endPoint=10&caId=' + '0' + 
	       		'&filter=' + '0' + 
	       		'&option=' + '4' + 
	       		'&optionParam=' + this.url.params.sn;
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
}
