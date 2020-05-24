import {Component, Directive, OnChanges, OnInit, Input} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Setting} from './../../services/setting';



@Component({
	selector: 'error',
	templateUrl: 'src/components/error/error.html',
	providers: [Setting],
})
export class ErrorComponent {
	errcode ;
	message ;
	rawMessage ;
	
	setting ;

	constructor(setting:Setting) {
		this.setting = setting ;
		this.errcode = this.setting.getCookie("errcode") ;
		this.message = this.setting.getCookie("message") ;
		this.rawMessage = this.setting.getCookie("rawMessage") ;
	}
}
