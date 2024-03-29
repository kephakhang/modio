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

(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['ApiClient'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('../ApiClient'));
  } else {
    // Browser globals (root is window)
    if (!root.SolidModel) {
      root.SolidModel = {};
    }
    root.SolidModel.Content = factory(root.SolidModel.ApiClient);
  }
}(this, function(ApiClient) {
  'use strict';




  /**
   * The Content model module.
   * @module model/Content
   * @version 1.0.0
   */

  /**
   * Constructs a new <code>Content</code>.
   * @alias module:model/Content
   * @class
   */
  var exports = function() {
    var _this = this;















  };

  /**
   * Constructs a <code>Content</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/Content} obj Optional instance to populate.
   * @return {module:model/Content} The populated <code>Content</code> instance.
   */
  exports.constructFromObject = function(data, obj) {
    if (data) {
      obj = obj || new exports();

      if (data.hasOwnProperty('id')) {
        obj['id'] = ApiClient.convertToType(data['id'], 'Integer');
      }
      if (data.hasOwnProperty('mb_id')) {
        obj['mb_id'] = ApiClient.convertToType(data['mb_id'], 'String');
      }
      if (data.hasOwnProperty('ca_id')) {
        obj['ca_id'] = ApiClient.convertToType(data['ca_id'], 'Integer');
      }
      if (data.hasOwnProperty('ca_name')) {
        obj['ca_name'] = ApiClient.convertToType(data['ca_name'], 'String');
      }
      if (data.hasOwnProperty('title')) {
        obj['title'] = ApiClient.convertToType(data['title'], 'String');
      }
      if (data.hasOwnProperty('rmk')) {
        obj['rmk'] = ApiClient.convertToType(data['rmk'], 'String');
      }
      if (data.hasOwnProperty('fname')) {
        obj['fname'] = ApiClient.convertToType(data['fname'], 'String');
      }
      if (data.hasOwnProperty('ftype')) {
        obj['ftype'] = ApiClient.convertToType(data['ftype'], 'String');
      }
      if (data.hasOwnProperty('fsize')) {
        obj['fsize'] = ApiClient.convertToType(data['fsize'], 'Integer');
      }
      if (data.hasOwnProperty('fpath')) {
        obj['fpath'] = ApiClient.convertToType(data['fpath'], 'String');
      }
      if (data.hasOwnProperty('thumbnail')) {
        obj['thumbnail'] = ApiClient.convertToType(data['thumbnail'], 'String');
      }
      if (data.hasOwnProperty('json')) {
        obj['json'] = ApiClient.convertToType(data['json'], 'String');
      }
      if (data.hasOwnProperty('regtime')) {
        obj['regtime'] = ApiClient.convertToType(data['regtime'], 'Date');
      }
      if (data.hasOwnProperty('udptime')) {
        obj['udptime'] = ApiClient.convertToType(data['udptime'], 'Date');
      }
    }
    return obj;
  }

  /**
   * 컨텐츠 아이디
   * @member {Integer} id
   */
  exports.prototype['id'] = undefined;
  /**
   * 사용자 아이디(email)
   * @member {String} mb_id
   */
  exports.prototype['mb_id'] = undefined;
  /**
   * 컨텐츠 카테고리 아이디
   * @member {Integer} ca_id
   */
  exports.prototype['ca_id'] = undefined;
  /**
   * 컨텐츠 카테고리 명
   * @member {String} ca_name
   */
  exports.prototype['ca_name'] = undefined;
  /**
   * 컨텐츠 제목
   * @member {String} title
   */
  exports.prototype['title'] = undefined;
  /**
   * 컨텐츠 내용
   * @member {String} rmk
   */
  exports.prototype['rmk'] = undefined;
  /**
   * 컨텐츠 원본 파일 명
   * @member {String} fname
   */
  exports.prototype['fname'] = undefined;
  /**
   * 컨텐츠 원본 파일 타입
   * @member {String} ftype
   */
  exports.prototype['ftype'] = undefined;
  /**
   * 컨텐츠 원본 파일 사이즈
   * @member {Integer} fsize
   */
  exports.prototype['fsize'] = undefined;
  /**
   * 컨텐츠 원본 Path
   * @member {String} fpath
   */
  exports.prototype['fpath'] = undefined;
  /**
   * 컨텐츠 썸네일 Path
   * @member {String} thumbnail
   */
  exports.prototype['thumbnail'] = undefined;
  /**
   * 컨텐츠 JSON
   * @member {String} json
   */
  exports.prototype['json'] = undefined;
  /**
   * 컨텐츠 등록 시각
   * @member {Date} regtime
   */
  exports.prototype['regtime'] = undefined;
  /**
   * 컨텐츠 수 시각
   * @member {Date} udptime
   */
  exports.prototype['udptime'] = undefined;



  return exports;
}));


