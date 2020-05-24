import {Injectable} from 'angular2/core';
import {JSONP_PROVIDERS, Jsonp} from 'angular2/http'

@Injectable()
export class Redirect {
	jsonp;
	url: string = 'http://3dz.kr/solid3d/add_url.html?key=3dz-share-short-url-generator&ajax=jsonop&callback=JSONP_CALLBACK&cp=1&content_sn=';
	constructor(jsonp:Jsonp) {
		this.jsonp = jsonp;
	}
	geturl(id){
		return this.jsonp.get(this.url+ id);
	}
}