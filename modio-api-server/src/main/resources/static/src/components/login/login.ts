import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {Login} from './../../model/login';


@Component({
	selector: 'login',
	templateUrl: 'src/components/login/login.html',
	providers: [Dribbble, Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class LoginComponent implements AfterViewChecked {
	id:string = null;	
    model ;  
	dribbble ;
	setting ;
	router ;
	password ;
	constructor(dl:Dribbble, setting:Setting, router:Router) {
		this.dribbble = dl ;
		this.setting = setting ;
		this.router = router ;
	}

	submitted = false;

	// TODO: Remove this when we're done
	get diagnostic() { return JSON.stringify(this.model); }

	// Reset the form with a new hero AND restore 'pristine' class state
	// by toggling 'active' flag which causes the form
	// to be removed/re-added in a tick via NgIf
	// TODO: Workaround until NgForm has a reset method (#6822)
	active = true;
	
	ngOnInit() {
		
		this.id = localStorage.getItem('id') ;
	    if (this.id === undefined || this.id == null ) 
	    	this.model = new Login('', '') ;
	    else
	    	this.model = new Login(this.id, '') ;	
		
	}
	
	bWrest = false ;
    ngAfterViewChecked() {
		
    	if( this.bWrest == false ){
    		wrestInitialized();
    		this.bWrest = true ;
    	}
	}

	onSubmit() { 
		
	
        //2016.10.11 이중길 추가. 뷰어로 바로 넘어가지 않고 등록버튼은 비활성화.
		var loginBtn = document.getElementById("login_btn");
		loginBtn.disabled = true;
		
		localStorage.setItem('id', this.model.id) ;
		this.model.password = Sha256.hash(this.password) ;
		this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users/login", this.model)	
					.then(function(response){

						if( response === null || typeof response === 'undefined' ) {
							loginBtn.disabled = false;
							return false ;
						}
						else {
							localStorage.setItem('accessToken', response.message) ;
							localStorage.setItem('id', response.id) ;
							localStorage.setItem('level', response.level) ;
							loginBtn.disabled = false;
							window.location.href = "/" ;						
							return ;
						}
					},function(response){
						if( response === null || typeof response === 'undefined' ) {
							loginBtn.disabled = false;
							return false ;
						}
						else {
							
							
							wrestAlert(response.message) ;
							loginBtn.disabled = false;
							return ;
						}
						
					})
					.catch(function(err){
						
						alert(err) ;
						loginBtn.disabled = false;
					}) ;
		
		return false ;
	}	
	
}
