import {Injectable} from 'angular2/core';

@Injectable()
export class Setting {
	hostUrl = (window.location.protocol == "80" ) ? 
		(window.location.protocol + "//" + window.location.hostname) : 
		(window.location.protocol + "//" + window.location.hostname + ":" + window.location.port) ;
	accessToken = null ;
	id = null ;
	ssntUrl = 'http://119.195.163.135:3390';
	
	public getCookie(name: string) {
        let ca: Array<string> = document.cookie.split(';');
        let caLen: number = ca.length;
        let cookieName = name + "=";
        let c: string;

        for (let i: number = 0; i < caLen; i += 1) {
            c = ca[i].replace(/^\s\+/g, "");
            if (c.indexOf(cookieName) == 0) {
                //return decodeURIComponent(c.substring(cookieName.length, c.length));
            	return c.substring(cookieName.length, c.length);
            }
        }
        return "";
    }

    public deleteCookie(name) {
        this.setCookie(name, "", -1);
    }

    public setCookie(name: string, value: string, expireDays: number, path: string = "") {
        let d:Date = new Date();
    	value = encodeURIComponent(value) ;
        d.setTime(d.getTime() + expireDays * 24 * 60 * 60 * 1000);
        let expires:string = "expires=" + d.toUTCString();
        document.cookie = name + "=" + value + "; " + expires + (path.length > 0 ? "; path=" + path : "");
    }
}