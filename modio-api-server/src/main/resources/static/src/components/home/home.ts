import {Component, Directive, OnChanges, OnInit, Input} from 'angular2/core';
import {Router, ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router'
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {JSONP_PROVIDERS} from 'angular2/http'
import {Redirect} from './../../services/redirectURL';

@Component({
	selector: 'home',
	templateUrl: 'src/components/home/home.html',
	providers: [Dribbble, Setting],
    directives: [ROUTER_DIRECTIVES]
})

export class HomeComponent implements OnInit, OnChanges{
	name: string = 'World';
    posts = new Array();
    dl:Dribbble;
    setting ;
    router ;
    page = 0;
  
    constructor(dl: Dribbble, setting:Setting, router:Router){
    	
        this.dl = dl;
        this.setting = setting ;
        this.router = router ;
        this.setting.accessToken = localStorage.getItem("accessToken") ;
        /*
        this.dl.getPosts(this.page).subscribe(res => {
            this.posts = res.json().data;
        });
        */
        
        var xhr = new XMLHttpRequest();
	
		//url = 'http://yally.net:8080/api/v2/users/content/list?startPoint=0&endPoint=12&caId=0&filter=0';
		var url = this.setting.hostUrl + '/api/v2/users/content/list?startPoint=0&endPoint=12&caId=0&filter=0';

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
    
    share_FaceBook() {		
		window.setTimeout(function() {
	        window.open(
				"https://www.facebook.com/sharer/sharer.php?u=http://3dz.kr?" + "131",
				"_blank", 
				"width=" + (window.innerWidth / 2)
				",height=" + (window.innerHeight / 2)
			);
	    }, 1000);
	}
	
	share_Twitter() {	
		window.setTimeout(function() {
	        window.open(
				"https://twitter.com/intent/tweet?text=http://3dz.kr?" + "131",
				"MsgWindow", 
				"width=" + window.innerWidth / 2 + 
				",height=" + window.innerHeight / 2
			);
	    }, 1000);
	}

    routerCanDeactivate(next: ComponentInstruction, prev: ComponentInstruction) {
        window.scrollTo(0, 0);
        return true;
    }
    
    callLogin() {
    	this.router.navigate(['/Login']) ;
    }
    callSignup() {
    	this.router.navigate(['/Signup']) ;
    }
    gotoPost(id){
        alert(id);
    }
    loadMore() {
        this.page++;
        
        /*
        this.dl.getPosts(this.page).subscribe(res => {
            res.json().data.forEach(post => {
                this.posts.push(post);
            });
        })
        */
        
        var xhr = new XMLHttpRequest();
	
		//url = 'http://yally.net:8080/api/v2/users/content/list?startPoint=' + this.page*12 + '&endPoint=12&caId=0&filter=0';
		var url = this.setting.hostUrl + '/api/v2/users/content/list?startPoint=' + this.page*12 + '&endPoint=12&caId=0&filter=0';

		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					JSON.parse(xhr.response).forEach(post=> {
						this.posts.push(post);
					});
					
					console.log(JSON.parse(xhr.response) + this.page*12);
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
