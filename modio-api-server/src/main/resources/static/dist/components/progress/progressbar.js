System.register(['angular2/core', './progress', './bar'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, progress_1, bar_1;
    var Progressbar;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (progress_1_1) {
                progress_1 = progress_1_1;
            },
            function (bar_1_1) {
                bar_1 = bar_1_1;
            }],
        execute: function() {
            Progressbar = (function () {
                function Progressbar() {
                }
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', Boolean)
                ], Progressbar.prototype, "animate", void 0);
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', Number)
                ], Progressbar.prototype, "max", void 0);
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', String)
                ], Progressbar.prototype, "type", void 0);
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', Number)
                ], Progressbar.prototype, "value", void 0);
                Progressbar = __decorate([
                    core_1.Component({
                        selector: 'progressbar, [progressbar]',
                        directives: [progress_1.Progress, bar_1.Bar],
                        template: "\n    <div progress [animate]=\"animate\" [max]=\"max\">\n      <bar [type]=\"type\" [value]=\"value\">\n          <ng-content></ng-content>\n      </bar>\n    </div>\n  "
                    }), 
                    __metadata('design:paramtypes', [])
                ], Progressbar);
                return Progressbar;
            })();
            exports_1("Progressbar", Progressbar);
        }
    }
});
