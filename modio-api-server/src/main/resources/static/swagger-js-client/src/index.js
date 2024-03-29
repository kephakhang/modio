/**
 * SolidModel
 * No descripton provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * Contact: mgkaki@yally.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

(function(factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['ApiClient', 'model/Category', 'model/Content', 'model/Error', 'model/Login', 'model/Member', 'model/Response', 'api/DefaultApi'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('./ApiClient'), require('./model/Category'), require('./model/Content'), require('./model/Error'), require('./model/Login'), require('./model/Member'), require('./model/Response'), require('./api/DefaultApi'));
  }
}(function(ApiClient, Category, Content, Error, Login, Member, Response, DefaultApi) {
  'use strict';

  /**
   * ERROR_UNKNOWN.<br>
   * The <code>index</code> module provides access to constructors for all the classes which comprise the public API.
   * <p>
   * An AMD (recommended!) or CommonJS application will generally do something equivalent to the following:
   * <pre>
   * var SolidModel = require('index'); // See note below*.
   * var xxxSvc = new SolidModel.XxxApi(); // Allocate the API class we're going to use.
   * var yyyModel = new SolidModel.Yyy(); // Construct a model instance.
   * yyyModel.someProperty = 'someValue';
   * ...
   * var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
   * ...
   * </pre>
   * <em>*NOTE: For a top-level AMD script, use require(['index'], function(){...})
   * and put the application logic within the callback function.</em>
   * </p>
   * <p>
   * A non-AMD browser application (discouraged) might do something like this:
   * <pre>
   * var xxxSvc = new SolidModel.XxxApi(); // Allocate the API class we're going to use.
   * var yyy = new SolidModel.Yyy(); // Construct a model instance.
   * yyyModel.someProperty = 'someValue';
   * ...
   * var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
   * ...
   * </pre>
   * </p>
   * @module index
   * @version 1.0.0
   */
  var exports = {
    /**
     * The ApiClient constructor.
     * @property {module:ApiClient}
     */
    ApiClient: ApiClient,
    /**
     * The Category model constructor.
     * @property {module:model/Category}
     */
    Category: Category,
    /**
     * The Content model constructor.
     * @property {module:model/Content}
     */
    Content: Content,
    /**
     * The Error model constructor.
     * @property {module:model/Error}
     */
    Error: Error,
    /**
     * The Login model constructor.
     * @property {module:model/Login}
     */
    Login: Login,
    /**
     * The Member model constructor.
     * @property {module:model/Member}
     */
    Member: Member,
    /**
     * The Response model constructor.
     * @property {module:model/Response}
     */
    Response: Response,
    /**
     * The DefaultApi service constructor.
     * @property {module:api/DefaultApi}
     */
    DefaultApi: DefaultApi
  };

  return exports;
}));
