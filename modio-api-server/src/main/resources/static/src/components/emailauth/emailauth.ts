import {Component, Directive, OnChanges, OnInit, Input} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Setting} from './../../services/setting';



@Component({
	selector: 'emailauth',
	templateUrl: 'src/components/emailauth/emailauth.html',
	providers: [Setting],
})
export class EmailauthComponent {
	id ;
	message ;
	accessToken ;
	
	setting ;

	constructor(setting:Setting) {
		this.setting = setting ;
		this.id = this.setting.getCookie("id") ;
		this.message = this.setting.getCookie("message") ;
		this.accessToken = this.setting.getCookie("accessToken") ;
		localStorage.setItem('accessToken', this.accessToken) ;
	}
}
