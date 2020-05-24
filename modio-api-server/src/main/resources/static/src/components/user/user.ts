import {Component, Directive, OnChanges, OnInit, Input, AfterViewChecked} from 'angular2/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES} from 'angular2/common';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router' ;
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {User} from './../../model/user';


@Component({
	selector: 'user',
	templateUrl: 'src/components/user/user.html',
	providers: [Dribbble, Setting],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, ROUTER_DIRECTIVES]
})
export class UserComponent implements OnInit, AfterViewChecked {
	id = localStorage.getItem('id') ;
//    model = new User('', '', 1, '', '', '', '', '') ;
	model = new Array();
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
	
	ngOnInit() {
		
		this.setting.id = localStorage.getItem('id') ;
		this.setting.accessToken = localStorage.getItem('accessToken') ;
		
		/*
		let params: URLSearchParams = new URLSearchParams();
		params.set('id', this.setting.id);
		params.set('accessToken', this.setting.accessToken);

		var response =  this.http.get(this.setting.hostUrl + "/api/v2/users", { search: params });
		
		this.model = JSON.parse(response)
		
		
		/*/
		
		//url = 'http://yally.net:8080/api/v2/users/content/list?startPoint=0&endPoint=12&caId=0&filter=0';
		var url = this.setting.hostUrl + "/api/v2/users?id=" + this.setting.id + "&accessToken=" + this.setting.accessToken ;
		
		let xhr = new XMLHttpRequest() ;
        
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    this.model = JSON.parse(xhr.response) ;
                    
                    signupBtn.disabled = false;
                } else {
                    var res = JSON.parse(xhr.response) ;
                    wrestAlert(res.message) ;
					signupBtn.disabled = false;
					return ;
                }
            }
        };
        xhr.open("GET", url, true) ;
        xhr.send(null) ;

        /*
		this.dribbble.getRequest(url)	
				.then(function(response){
		
						this.model = new User(response.id, response.password, response.level, response.name) ;
						return ;
				},function(response){
						
						wrestAlert(response.message) ;
						signupBtn.disabled = false;
						return ;
					
				})
				.catch(function(err){
					
					wrestAlert(err) ;
					signupBtn.disabled = false;
				}) ;
				
				
		
			return false ;	
			*/
	}
	
	submitted = false;

	// TODO: Remove this when we're done
	//get diagnostic() { return JSON.stringify(this.model); }

	// Reset the form with a new hero AND restore 'pristine' class state
	// by toggling 'active' flag which causes the form
	// to be removed/re-added in a tick via NgIf
	// TODO: Workaround until NgForm has a reset method (#6822)
	active = true;

	file ;
    selectFile($event): void {
		var inputValue = $event.target;
		this.file = inputValue.files[0];
		console.debug("Input File name: " + this.file.name + " type:" + this.file.size + " size:" + this.file.size);
	}

    
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
		this.dribbble.putRequest(this.setting.hostUrl + "/api/v2/users", this.model)	
					.then(function(response){

						if( response === null || typeof response === 'undefined' ) {
							signupBtn.disabled = false;
							return false ;
						}
						else {
							
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
