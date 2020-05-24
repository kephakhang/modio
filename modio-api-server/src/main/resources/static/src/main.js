"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('angular2/core');
var browser_1 = require('angular2/platform/browser');
var http_1 = require('angular2/http');
var router_1 = require('angular2/router');
var home_1 = require('./components/home/home');
var post_1 = require('./components/post/post');
var upload_1 = require('./components/upload/upload');
var APP = (function () {
    function APP() {
    }
    APP.prototype.ngOnInit = function () {
        jQuery("#preloader").delay(100).fadeOut("slow");
        jQuery("#load").delay(100).fadeOut("slow");
    };
    APP = __decorate([
        router_1.RouteConfig([
            { path: '/snaps', component: home_1.HomeComponent, as: 'Home', useAsDefault: true },
            { path: '/upload', component: upload_1.UploadComponent, as: 'Upload' },
            { path: '/snaps/:id', component: post_1.PostComponent, as: 'Post' }
        ]),
        core_1.Component({
            selector: 'app',
            templateUrl: 'src/components/app/app.html',
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [])
    ], APP);
    return APP;
}());
exports.APP = APP;
browser_1.bootstrap(APP, [http_1.JSONP_PROVIDERS, router_1.ROUTER_PROVIDERS, core_1.provide(router_1.LocationStrategy, { useClass: router_1.HashLocationStrategy }),
    core_1.provide(router_1.APP_BASE_HREF, { useValue: '/' })]);
//# sourceMappingURL=main.js.map