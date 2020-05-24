System.register(['angular2/core', 'angular2/common', './progress.directive', './bar.component', './progressbar.component'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, common_1, progress_directive_1, bar_component_1, progressbar_component_1;
    var Angular2Progressbar;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (progress_directive_1_1) {
                progress_directive_1 = progress_directive_1_1;
            },
            function (bar_component_1_1) {
                bar_component_1 = bar_component_1_1;
            },
            function (progressbar_component_1_1) {
                progressbar_component_1 = progressbar_component_1_1;
            }],
        execute: function() {
            Angular2Progressbar = (function () {
                function Angular2Progressbar() {
                    this.max = 200;
                    this.stackedValues = [];
                    this.generateNewProgressValues();
                    this.generateStackedValues();
                }
                Angular2Progressbar.prototype.generateNewProgressValues = function () {
                    var value = Math.floor((Math.random() * 100) + 1);
                    var type;
                    if (value < 20) {
                        type = 'success';
                    }
                    else if (value < 40) {
                        type = 'info';
                    }
                    else if (value < 60) {
                        type = 'warning';
                    }
                    else {
                        type = 'danger';
                    }
                    this.currentValue = value;
                    this.type = type;
                };
                ;
                Angular2Progressbar.prototype.generateStackedValues = function () {
                    var types = ['success', 'info', 'warning', 'danger'];
                    this.stackedValues = [];
                    var total = 0;
                    for (var i = 0, n = Math.floor((Math.random() * 4) + 1); i < n; i++) {
                        var index = Math.floor((Math.random() * 4));
                        var value = Math.floor((Math.random() * 30) + 1);
                        total += value;
                        this.stackedValues.push({
                            value: value,
                            max: value,
                            type: types[index]
                        });
                    }
                };
                ;
                Angular2Progressbar = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        template: "\n                <h4>Angular 2 Progressbar Example</h4>\n                <progressbar class=\"progress-striped\" value=\"55\" type=\"warning\" max=\"200\">55%</progressbar>\n\n                <hr/>\n                <h4>Angular 2 Dynamic Progressbar Example</h4>\n                <button type=\"button\" class=\"btn btn-sm btn-default\" (click)=\"generateNewProgressValues()\">Generate New Values</button>\n\n                <progressbar [animate]=\"false\" [max]=\"max\" [value]=\"currentValue\">\n                <span style=\"color:white; white-space:nowrap;\">{{currentValue}} / {{max}}</span>\n                </progressbar>\n\n                <hr/>\n                <h4>Angular 2 Progressbar With Animation Example</h4>\n                <progressbar [animate]=\"true\" [value]=\"currentValue\" [type]=\"type\"><b>{{currentValue}}%</b></progressbar>\n\n                <hr/>\n                <h4>Angular 2 Stacked Progressbar Example</h4>\n                <h3>\n                  <button type=\"button\" class=\"btn btn-sm btn-primary\" (click)=\"generateStackedValues()\">Stacked Values</button>\n                </h3>\n                <bs-progress>\n                  <bar *ngFor=\"#stacked of stackedValues\" [value]=\"stacked.value\" [type]=\"stacked?.type\">\n                    <span [hidden]=\"stacked.value < 5\">{{stacked?.value}}%</span>\n                  </bar>\n                </bs-progress>\n             ",
                        directives: [progress_directive_1.Progress, bar_component_1.Bar, progressbar_component_1.Progressbar, common_1.CORE_DIRECTIVES],
                    }), 
                    __metadata('design:paramtypes', [])
                ], Angular2Progressbar);
                return Angular2Progressbar;
            })();
            exports_1("Angular2Progressbar", Angular2Progressbar);
        }
    }
});
