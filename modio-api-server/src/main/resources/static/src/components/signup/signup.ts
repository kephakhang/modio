import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {User} from './../../model/user';


@Component({
	selector: 'signup',
	templateUrl: 'src/components/signup/signup.html',
	providers: [Dribbble, Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class SignupComponent implements AfterViewChecked {
	id = localStorage.getItem('id') ;
    model = new User('', '', 1, '') ;
    password1 = '' ;
    password2 = '' ;
	dribbble ;
	setting ;
	router ;
	constructor(dl:Dribbble, setting:Setting, router:Router) {
		this.dribbble = dl ;
		this.setting = setting ;
		this.router = router ;
	}
	
	bWrest = false ;
    ngAfterViewChecked() {
		
    	if( this.bWrest == false ) {
    		wrestInitialized();
    		this.bWrest = true ;
    	}
	
	}

	
	nextTab(elem) {
	    $(elem).next().find('a[data-toggle="tab"]').click();
	}	
	prevTab(elem) {
	    $(elem).prev().find('a[data-toggle="tab"]').click();
	}	
	nextStep() {
		var $active = $('.wizard .nav-tabs li.active');
		$active.next().removeClass('disabled') ;
		this.nextTab($active) ;
		window.scrollTo(0, 0);
	}	
	prevStep() {
		var $active = $('.wizard .nav-tabs li.active');
		this.prevTab($active) ;
	}
	onAgree() {
		
		if( $("#agree11").is(':checked') && $("#agree21").is(':checked') ) {
			this.nextStep() ;
		}
		/*
		else if( !$("#agree11").is(':checked') ){		
			alert("동의 버튼을 체크해 주세요!!!") ; 
			$("#agree11").focus() ;
		}
		else {		
			alert("동의 버튼을 체크해 주세요!!!") ; 
			$("#agree21").focus() ;
		}
		*/
	}
	
	
	
	submitted = false;

	// TODO: Remove this when we're done
	get diagnostic() { return JSON.stringify(this.model); }

	// Reset the form with a new hero AND restore 'pristine' class state
	// by toggling 'active' flag which causes the form
	// to be removed/re-added in a tick via NgIf
	// TODO: Workaround until NgForm has a reset method (#6822)
	active = true;

	onSubmit() { 
	
		
		if( this.password1 != this.password2 ) {
			wrestAlert("입력한 암호가 서로 일치하지 않습니다.") ; 
			return false ;
		}

        //2016.10.11 이중길 추가. 뷰어로 바로 넘어가지 않고 등록버튼은 비활성화.
		var signupBtn = document.getElementById("signupBtn");
		signupBtn.disabled = true;

		localStorage.setItem('id', this.model.id) ;
		this.model.password = Sha256.hash(this.password1) ;
		this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users", this.model)	
					.then(function(response){

						if( response === null || typeof response === 'undefined' ) {
							signupBtn.disabled = false;
							return false ;
						}
						else {
							var $active = $('.wizard .nav-tabs li.active');
							$active.next().removeClass('disabled') ;
							$($active).next().find('a[data-toggle="tab"]').click();
							window.scrollTo(0, 0);
							//loginBtn.disabled = false;
							return ;
						}
					},function(response){
						if( response === null || typeof response === 'undefined' ) {
							signupBtn.disabled = false;
							return false ;
						}
						else {
							
							wrestAlert(response.message) ;
							signupBtn.disabled = false;
							return ;
						}
						
					})
					.catch(function(err){
						
						wrestAlert(err) ;
						signupBtn.disabled = false;
					}) ;
		
		return false ;	
	}	
}
