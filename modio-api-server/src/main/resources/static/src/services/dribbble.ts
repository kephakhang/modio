import {Injectable} from 'angular2/core';
import {JSONP_PROVIDERS, Jsonp} from 'angular2/http' ;
import {Router} from 'angular2/router' ;
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/share';

@Injectable()
export class Dribbble {
	jsonp;
	api: string = 'http://api.dribbble.com/v1/';
	token: string = 'bc0239a39745e8604bb996d5ae6cd73ca605d4a0b448de4ab3b21b31fd610966';
	constructor(jsonp:Jsonp) {
		this.jsonp = jsonp;
	}
	getPosts(page){
		return this.jsonp.get(this.api + '/shots?page='+ page +'&access_token='+this.token+'&callback=JSONP_CALLBACK');
	}
	getPost(id){
		return this.jsonp.get(this.api + '/shots/'+id+'?access_token='+this.token+'&callback=JSONP_CALLBACK');
	}
	
	postRequest(url, json) :Promise<any> {
	    return new Promise((resolve, reject) => {
	        //let formData: any = new FormData() ;
	        let xhr = new XMLHttpRequest() ;
	                
	        xhr.onreadystatechange = function () {
	            if (xhr.readyState === 4) {
	                if (xhr.status === 200) {
	                    resolve(JSON.parse(xhr.response)) ;
	                } else {
	                    reject(JSON.parse(xhr.response)) ;
	                }
	            }
	        };
	        xhr.open("POST", url, true) ;
	        xhr.setRequestHeader('Content-type', 'application/json');
	        xhr.send(JSON.stringify(json)) ;
	    });
	}
	
	putRequest(url, json) :Promise<any> {
	    return new Promise((resolve, reject) => {
	        //let formData: any = new FormData() ;
	        let xhr = new XMLHttpRequest() ;
	                
	        xhr.onreadystatechange = function () {
	            if (xhr.readyState === 4) {
	                if (xhr.status === 200) {
	                    resolve(JSON.parse(xhr.response)) ;
	                } else {
	                    reject(JSON.parse(xhr.response)) ;
	                }
	            }
	        };
	        xhr.open("PUT", url, true) ;
	        xhr.setRequestHeader('Content-type', 'application/json');
	        xhr.send(JSON.stringify(json)) ;
	    });
	}
	
	getRequest(url) :Promise<any> {
	    return new Promise((resolve, reject) => {
	        //let formData: any = new FormData() ;
	        let xhr = new XMLHttpRequest() ;
	                
	        xhr.onreadystatechange = function () {
	            if (xhr.readyState === 4) {
	                if (xhr.status === 200) {
	                    resolve(JSON.parse(xhr.response)) ;
	                } else {
	                    reject(JSON.parse(xhr.response)) ;
	                }
	            }
	        };
	        xhr.open("GET", url, true) ;
	        xhr.send(null) ;
	    });
	}
}