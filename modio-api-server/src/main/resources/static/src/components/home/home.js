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
var animation_builder_1 = require('angular2/src/animate/animation_builder');
var router_1 = require('angular2/router');
var dribbble_1 = require('./../../services/dribbble');
var HomeComponent = (function () {
    function HomeComponent(dl, anim) {
        var _this = this;
        this.duration = 500;
        this.name = 'World';
        this.page = 0;
        this.animIdx = 0;
        this.dl = dl;
        this.page = 1;
        this.dl.getPosts(this.page).subscribe(function (res) {
            _this.posts = res.json().data;
        });
        this.anim = anim.css();
    }
    HomeComponent.prototype.routerCanDeactivate = function (next, prev) {
        window.scrollTo(0, 0);
        return true;
    };
    HomeComponent.prototype.gotoPost = function (id) {
        alert(id);
    };
    HomeComponent.prototype.loadMore = function () {
        var _this = this;
        this.page++;
        this.dl.getPosts(this.page).subscribe(function (res) {
            res.json().data.forEach(function (post) {
                _this.posts.push(post);
            });
        });
    };
    HomeComponent.prototype.animListener = function () {
        this.animIdx = (this.animIdx + 1) % 4;
        alert(this.animIdx);
    };
    HomeComponent.prototype.prevMain = function () {
    };
    HomeComponent.prototype.nextMain = function () {
    };
    HomeComponent.prototype.ngOnInit = function () {
        /*
        this.anim = document.querySelector("#res-window img") ;
        this.anim.addEventListener("animationend", this.animListener, false);
        this.anim.addEventListener("webkitAnimationEnd", this.animListener, false);
        this.anim.addEventListener("oanimationend", this.animListener, false);
        this.anim.addEventListener("MSAnimationEnd", this.animListener, false);
        */
    };
    HomeComponent.prototype.ngOnChanges = function (changes) {
        // if the change happened in the collapse property
        alert(changes);
        /*
        if (changes.collapse) {
              if (this.collapse) {
                // Logic to hide the element
              } else {
                // Logic to show the element
              }
        }
        */
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Number)
    ], HomeComponent.prototype, "duration", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Boolean)
    ], HomeComponent.prototype, "collapse", void 0);
    HomeComponent = __decorate([
        core_1.Component({
            selector: 'home',
            templateUrl: 'src/components/home/home.html',
            providers: [dribbble_1.Dribbble],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [dribbble_1.Dribbble, animation_builder_1.AnimationBuilder])
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
//# sourceMappingURL=home.js.map