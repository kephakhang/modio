import { Component } from 'angular2/core';
import { Injectable } from 'angular2/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/share';

@Injectable()
export class FileUploadService {
	/**
	 * @param Observable<number>
	 */
	private progress$: Observable<number>;
	
	/**
	 * @type {number}
	 */
	public progress: number = 0;
	
	private progressObserver: any;
	
	constructor () {
	    this.progress$ = new Observable(observer => {
	        this.progressObserver = observer
	    });
	}
	
	/**
	 * @returns {Observable<number>}
	 */
	public getObserver (): Observable<number> {
	    return this.progress$;
	}
	
	/**
	 * Upload files through XMLHttpRequest
	 *
	 * @param url
	 * @param files
	 * @returns {Promise<T>}
	 */
	public upload (url: string, files: File[]): Promise<any> {
	    return new Promise((resolve, reject) => {
	        let formData: FormData = new FormData(),
	            xhr: XMLHttpRequest = new XMLHttpRequest();
	
	        for (let i = 0; i < files.length; i++) {
	            formData.append("uploads[]", files[i], files[i].name);
	        }
	
	        xhr.onreadystatechange = () => {
	            if (xhr.readyState === 4) {
	                if (xhr.status === 200) {
	                    resolve(JSON.parse(xhr.response));
	                } else {
	                    reject(xhr.response);
	                }
	            }
	        };
	
	        FileUploadService.setUploadUpdateInterval(500);
	
	        xhr.upload.onprogress = (event) => {
	            this.progress = Math.round(event.loaded / event.total * 100);
	
	           /*  this.progressObserver.next(this.progress); */
	        };
	
	        xhr.open('POST', url, true);
	        xhr.send(formData);
	    });
	}

	/**
	 * Upload files through XMLHttpRequest
	 *
	 * @param url
	 * @param files
	 * @returns {Promise<T>}
	 */
	public upload2 (url: string, file: File, mb_id: string, ca_id: string, title: string, rmk: string, open: string, accessToken:string): Promise<any> {
	    return new Promise((resolve, reject) => {
	        let formData: FormData = new FormData(),
	            xhr: XMLHttpRequest = new XMLHttpRequest();
	      
	        formData.append("file", file, file.name);
			formData.append("mbId", mb_id) ;
			formData.append("caId", ca_id) ;
			formData.append("title", title) ;
			formData.append("rmk", rmk) ;
			formData.append("open", open);
			formData.append("accessToken", accessToken) ;
	
	        xhr.onreadystatechange = () => {
	            if (xhr.readyState === 4) {
	                if (xhr.status === 200) {
	                    resolve(JSON.parse(xhr.response));
	                } else {
	                    reject(xhr.response);
	                }
	            }
	        };
	
	        FileUploadService.setUploadUpdateInterval(500);
	
	        xhr.upload.onprogress = (event) => {
	            this.progress = Math.round(event.loaded / event.total * 100);
	
	           /* this.progressObserver.next(this.progress); */
	        };
	
	        xhr.open('POST', url, true);
	        xhr.send(formData);
	    });
	}
	
	/**
	 * Set interval for frequency with which Observable inside Promise will share data with subscribers.
	 *
	 * @param interval
	 */
	private static setUploadUpdateInterval (interval: number): void {
	    setInterval(() => {}, interval);
	}
}
