System.register([], function(exports_1) {
    var Content;
    return {
        setters:[],
        execute: function() {
            Content = (function () {
                function Content(accessToken, id, mbId, caId, title, rmk, open, caName, fname, ftype, fsize, regtime, updtime) {
                    this.accessToken = accessToken;
                    this.id = id;
                    this.mbId = mbId;
                    this.caId = caId;
                    this.title = title;
                    this.rmk = rmk;
                    this.open = open;
                    this.caName = caName;
                    this.fname = fname;
                    this.ftype = ftype;
                    this.fsize = fsize;
                    this.regtime = regtime;
                    this.updtime = updtime;
                }
                return Content;
            })();
            exports_1("Content", Content);
        }
    }
});
