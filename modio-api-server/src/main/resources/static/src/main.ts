import {Component, provide, HostListener} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {Http, HTTP_PROVIDERS, Jsonp, JSONP_PROVIDERS} from 'angular2/http'
import {ROUTER_DIRECTIVES, RouteConfig, APP_BASE_HREF, LocationStrategy, HashLocationStrategy, ROUTER_PROVIDERS} from 'angular2/router' ;
import {Setting} from './services/setting';
import {Dribbble} from './services/dribbble';

import {UserComponent} from './components/user/user'; 
import {HomeComponent} from './components/home/home';
import {PostComponent} from './components/post/post';
import {UploadComponent} from './components/upload/upload'; 
import {ThumnailComponent} from './components/thumnail/thumnail'; 
import {MainComponent} from './components/main/main'; 
import {LoginComponent} from './components/login/login'; 
import {ErrorComponent} from './components/error/error'; 
import {SignupComponent} from './components/signup/signup'; 
import {EmailauthComponent} from './components/emailauth/emailauth'; 
import {MycontentsComponent} from './components/mycontents/mycontents'; 
import {SharecontentsComponent} from './components/sharecontents/sharecontents';
import {SharecontentstableComponent} from './components/sharecontentstable/sharecontentstable';
import {SharecontentstrackingComponent} from './components/sharecontentstracking/sharecontentstracking';
import {ShareuserstableComponent} from './components/shareuserstable/shareuserstable';
import {ModifiedcontentsComponent} from './components/modifiedcontents/modifiedcontents';
import {SearchcontentsComponent} from './components/searchcontents/searchcontents';
import {UserComponent} from './components/user/user';
import {ModifieduserComponent} from './components/modifieduser/modifieduser';
import {DeleteuserComponent} from './components/deleteuser/deleteuser';
import {CommunityComponent} from './components/community/community';
import {CommunityviewComponent} from './components/communityview/communityview';
import {CreateCommunityComponent} from './components/createcommunity/createcommunity';
import {CommunityModifyComponent} from './components/communitymodify/communitymodify';

import {Logout} from './model/logout';

@RouteConfig([
    {path: '/snaps', component: HomeComponent, as: 'Home' , useAsDefault: true},
    {path: '/upload', component: UploadComponent, as: 'Upload'},
    {path: '/snaps/:id', component: PostComponent, as: 'Post'},
    {path: '/thumnail', component: ThumnailComponent, as: 'Thumnail'},
    {path: '/login', component: LoginComponent, as: 'Login'},
    {path: '/main', component: MainComponent, as: 'Main'},
    {path: '/error', component: ErrorComponent, as: 'Error'},
    {path: '/signup', component: SignupComponent, as: 'Signup'},
    {path: '/emailauth', component: SignupComponent, as: 'Emailauth'},
    {path: '/mycontents', component: MycontentsComponent, as: 'Mycontents'},
    {path: '/sharecontents', component: SharecontentsComponent, as: 'Sharecontents'},
    {path: '/sharecontentstable', component: SharecontentstableComponent, as: 'Sharecontentstable'},
    {path: '/sharecontentstracking', component: SharecontentstrackingComponent, as: 'Sharecontentstracking'},
    {path: '/shareuserstable', component: ShareuserstableComponent, as: 'Shareuserstable'},
    {path: '/modifiedcontents', component: ModifiedcontentsComponent, as: 'Modifiedcontents'},
    {path: '/searchcontents', component: SearchcontentsComponent, as: 'Searchcontents'},
    {path: '/user', component: UserComponent, as: 'User'},
    {path: '/modifieduser', component: ModifieduserComponent, as: 'Modifieduser'},
    {path: '/deleteuser', component: DeleteuserComponent, as: 'Deleteuser'},
    {path: '/community', component: CommunityComponent, as: 'Community'},
    {path: '/communityview', component: CommunityviewComponent, as: 'Communityview'},
    {path: '/createcommunity', component: CreateCommunityComponent, as: 'CreateCommunity'},
    {path: '/communitymodify', component: CommunityModifyComponent, as: 'CommunityModify'}
])
@Component({
    selector: 'app',
    templateUrl: 'src/components/app/app.html',
    providers: [Dribbble, Setting],
    directives: [ROUTER_DIRECTIVES]
})
export class APP implements OnInit {
	id = null ;
	accessToken = null ;
	setting ;
	dribbble ;
	model = new Logout('', '') ;
	
	constructor(dl:Dribbble, setting:Setting){
		this.dribbble = dl ;
		this.setting = setting ;
		
		this.id = localStorage.getItem('id') ;
		this.accessToken = localStorage.getItem('accessToken') ;
		
		if( this.id === undefined || this.id === null ||
			this.accessToken === undefined || this.accessToken === null ) {
					
		}
		else {
		
			this.model.id = this.id ;
			this.model.accessToken = this.accessToken ;
			
			this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users/check", this.model)	
			.then(function(response){
	
				if( response === null || typeof response === 'undefined' ) {
					return false ;
				}
				else {
					
					return false ;
				}
			},function(response){
				if( response === null || typeof response === 'undefined' ) {
					return false ;
				}
				else {					
					//alert(response.message) ;
					localStorage.removeItem('id') ;
					localStorage.removeItem('accessToken') ;
					window.location.href = "/" ;
					return ;
				}
				
			})
			.catch(function(err){
				
				alert(err) ;
				localStorage.removeItem('id') ;
				localStorage.removeItem('accessToken') ;
				window.location.href = "/" ;
			}) ;
		}
	}
	
	ngOnInit() {
		
		jQuery("#preloader").delay(100).fadeOut("slow");
		jQuery("#load").delay(100).fadeOut("slow");
		
	}
	
	
	navOpen() {
		if( jQuery("#nav").hasClass("nav-open") )
			jQuery("#nav").removeClass("nav-open") ;
		else
			jQuery("#nav").addClass("nav-open") ;
	}
	
	logout() {
		
		
		this.id = localStorage.getItem('accessToken') ;
		this.accessToken = localStorage.getItem('accessToken') ;
		
		if( this.id === undefined || this.id === null ||
			this.accessToken === undefined || this.accessToken === null ) {
			localStorage.removeItem('id') ;
			localStorage.removeItem('accessToken') ;
			window.location.href = "/" ;
		}
		else {
		
			this.model.id = this.id ;
			this.model.accessToken = this.accessToken ;
			
			
			this.dribbble.postRequest(this.setting.hostUrl + "/api/v2/users/logout", this.model)	
					.then(function(response){
			
						if( response === null || typeof response === 'undefined' ) {
							return false ;
						}
						else {
							
							localStorage.removeItem('accessToken') ;
							localStorage.removeItem('id') ;
							window.location.href = "/" ;
							return ;
						}
					},function(response){
						if( response === null || typeof response === 'undefined' ) {
							return false ;
						}
						else {
							
							localStorage.removeItem('accessToken') ;
							localStorage.removeItem('id') ;
							window.location.href = "/" ;
							return ;
						}
						
					})
					.catch(function(err){
						
						alert(err) ;
						localStorage.removeItem('accessToken') ;
						localStorage.removeItem('id') ;
						window.location.href = "/" ;
					}) ;
		}
		
		return false ;		
	}
    
}

bootstrap(APP, [JSONP_PROVIDERS, ROUTER_PROVIDERS, provide(LocationStrategy, { useClass: HashLocationStrategy })
,provide(APP_BASE_HREF, {useValue: '/'})]);
