import {Component, Directive, OnChanges, OnInit, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES, CanDeactivate, ComponentInstruction} from 'angular2/router'
import {Dribbble} from './../../services/dribbble';
import {Setting} from './../../services/setting';
import {JSONP_PROVIDERS} from 'angular2/http'
import {Redirect} from './../../services/redirectURL';
import {RouteParams} from 'angular2/router';

@Component({
	selector: 'mycontents',
	templateUrl: 'src/components/mycontents/mycontents.html',
	providers: [Dribbble, Setting],
    directives: [ROUTER_DIRECTIVES]
})

export class MycontentsComponent implements OnInit, OnChanges{
    name: string = 'World';
    posts = new Array();
    dl:Dribbble;
    page = 0;
    category;
    filter;
    caId;
    filter_r;
	setting ;
	my_btn_count = 0;
	buy_btn_count = 1;
	btn_status;
	btn;	
		
    constructor(dl: Dribbble, setting:Setting, category : RouteParams, filter : RouteParams, btn : RouteParams){
		this.setting = setting ;
       	this.dl = dl;
       	this.category = category;
       	this.filter = filter;
       	this.btn = btn;
       	this.btn_status = btn;
       	this.name = localStorage.getItem("id");
       
        //초기화면은 카테고리, 필터링으로 구분하기 위한 부분. 초기화면이 아닐 경우는 사용자가 선택한 텍스트로 변환.
        if(this.category.params.category != undefined) {
            document.getElementById("category").innerHTML = this.category.params.category;
        }
        else {
            document.getElementById("category").innerHTML = '카테고리 ▼';
        }   

        if(this.filter.params.filter != undefined) {
            document.getElementById("filter").innerHTML = this.filter.params.filter;
        }
        else {
            document.getElementById("filter").innerHTML = '필 터 링 ▼';
        }
        
        if(this.btn.params.btn == undefined)
        	this.btn.params.btn = 0;
        	

        console.log('main page..');
        console.log(this.category);

        /*
        this.dl.getPosts(this.page).subscribe(res => {
            this.posts = res.json().data;
        });
        */
        
        var xhr = new XMLHttpRequest();
        
        if(this.category.params.category == '전체 ▼') {
            this.caId = '0';
        }
        else if(this.category.params.category == '사람 ▼') {
            this.caId = '1';
        }
        else if(this.category.params.category == '사물 ▼') {
            this.caId = '2';
        }
        else {
            this.caId = '0';
        }

        if(this.filter.params.filter == '조회수 ▼') {
            this.filter_r = '1';
        }
        else if(this.filter.params.filter == '다운수 ▼') {
            this.filter_r = '2';
        }
        else if(this.filter.params.filter == '공유수 ▼') {
            this.filter_r = '3';
        }
        else if(this.filter.params.filter == '구매수 ▼') {
            this.filter_r = '4';
        }
        else {
            this.filter_r = '0';
        }

        console.log('caId' + this.caId);
        console.log('filter_r' + this.filter_r);

		var url;
		
		if(this.btn.params.btn == 1) {
			this.btn.params.btn = 0;
			//버튼 클릭시마다 option 값이 달라지기 때문에 결과가 달라짐
	    	url = this.setting.hostUrl + 
	      		'/api/v2/users/content/list?startPoint=0&endPoint=18&caId=' + this.caId + 
	       		'&filter=' + this.filter_r + 
	       		'&option=' + this.btn.params.btn + 
	       		'&optionParam=' + localStorage.getItem("id");
	       alert('준비중입니다');
	       this.btn.params.btn = 1;
		}
		else {
			//버튼 클릭시마다 option 값이 달라지기 때문에 결과가 달라짐
	    	url = this.setting.hostUrl + 
	      		'/api/v2/users/content/list?startPoint=0&endPoint=18&caId=' + this.caId + 
	       		'&filter=' + this.filter_r + 
	       		'&option=' + this.btn.params.btn + 
	       		'&optionParam=' + localStorage.getItem("id");
		}

		
	       		
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

    routerCanDeactivate(next: ComponentInstruction, prev: ComponentInstruction) {
        window.scrollTo(0, 0);
        return true;
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
	
		var url;
		//버튼 클릭시마다 option 값이 달라지기 때문에 결과가 달라짐
	    url = this.setting.hostUrl + 
	      		'/api/v2/users/content/list?startPoint=' + this.page*18 + '&endPoint=18&caId=' + this.caId + 
	       		'&filter=' + this.filter_r + 
	       		'&option=' + this.btn.params.btn + 
	       		'&optionParam=' + localStorage.getItem("id");
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

    ngOnInit() {   	
    	
    }
    

    ngOnChanges(changes) {
        // if the change happened in the collapse property
    
    }
}
