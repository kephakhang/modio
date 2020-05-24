# SolidModel.DefaultApi

All URIs are relative to *https://localhost:8080/api/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addUser**](DefaultApi.md#addUser) | **POST** /users | 
[**addUserContent**](DefaultApi.md#addUserContent) | **POST** /users/{id}/content | 
[**deleteUser**](DefaultApi.md#deleteUser) | **DELETE** /users/{id} | 
[**deleteUserContent**](DefaultApi.md#deleteUserContent) | **DELETE** /users/{id}/content/{id} | 
[**findUserById**](DefaultApi.md#findUserById) | **GET** /users/{id} | 
[**findUserContentById**](DefaultApi.md#findUserContentById) | **GET** /users/{id}/content/{id} | 
[**login**](DefaultApi.md#login) | **POST** /users/login | 
[**updateUser**](DefaultApi.md#updateUser) | **PUT** /users | 
[**updateUserContent**](DefaultApi.md#updateUserContent) | **PUT** /users/{id}/content | 
[**uploadUserContent**](DefaultApi.md#uploadUserContent) | **POST** /users/{id}/content/upload | 


<a name="addUser"></a>
# **addUser**
> Response addUser(member)



Post &#x60;Member&#x60; object. - 사용자 정보 추가 - **member** 은 사용자 정보 입력 파라미터 JSON 이며, 리턴 값은 성공/실패 여부 판단 값이다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var member = new SolidModel.Member(); // Member | 멤버 정보 JSON


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.addUser(member, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **member** | [**Member**](Member.md)| 멤버 정보 JSON | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="addUserContent"></a>
# **addUserContent**
> Response addUserContent(id, content)



Post &#x60;Content&#x60; object. - 사용자 컨텐츠 정보 추가 -  **id** 는 사용자 아이디(email) 이며, **content** 는 컨텐츠 정보 입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var id = "id_example"; // String | 사용자 아이디(email)

var content = new SolidModel.Content(); // Content | 사용자 컨텐츠 정보 JSON


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.addUserContent(id, content, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 사용자 아이디(email) | 
 **content** | [**Content**](Content.md)| 사용자 컨텐츠 정보 JSON | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="deleteUser"></a>
# **deleteUser**
> Response deleteUser(id)



delete &#x60;Member&#x60; - 사용자 정보 삭제 - **id** 는 사용자 아이디(email) 입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var id = "id_example"; // String | 멤버 정보


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.deleteUser(id, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 멤버 정보 | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="deleteUserContent"></a>
# **deleteUserContent**
> Response deleteUserContent(id, id2)



delete &#x60;Content&#x60; - 사용자 컨텐츠 정보 삭제 - **id** 는 사용자 아이디(email) 이며, **id** 는 컨텐츠 아이디 입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var id = "id_example"; // String | 사용자 아이디(email)

var id2 = 789; // Integer | 사용자 컨텐츠 아이디


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.deleteUserContent(id, id2, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 사용자 아이디(email) | 
 **id2** | **Integer**| 사용자 컨텐츠 아이디 | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="findUserById"></a>
# **findUserById**
> Member findUserById(id)



Get &#x60;Member&#x60; object. - 사용자 정보 수신 - **id** 는 사용자 아이디(email) 입니다. 리턴 값은 멤버(Member) object 입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var id = "id_example"; // String | 사용자 아이디(email)


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.findUserById(id, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 사용자 아이디(email) | 

### Return type

[**Member**](Member.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="findUserContentById"></a>
# **findUserContentById**
> Content findUserContentById(id, id2)



Get &#x60;Content&#x60; object. - 사용자 컨텐츠 정보 수신 - **id** 는 사용자 아이디(email) 이며, **id** 는 컨텐츠 아이디 입니다. 리턴 값은 멤버(Content) object 입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var id = "id_example"; // String | 사용자 아이디(email)

var id2 = 789; // Integer | 사용자 컨텐츠 아이디


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.findUserContentById(id, id2, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 사용자 아이디(email) | 
 **id2** | **Integer**| 사용자 컨텐츠 아이디 | 

### Return type

[**Content**](Content.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="login"></a>
# **login**
> Response login(login)



Check &#x60;Login&#x60; **login** 은 로그인 정보 값이다. 리턴 값은 성공/실패 여부 판단 값이다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var login = new SolidModel.Login(); // Login | 멤버 정보


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.login(login, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **login** | [**Login**](Login.md)| 멤버 정보 | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="updateUser"></a>
# **updateUser**
> Response updateUser(member)



Put &#x60;Member&#x60; object. - 사용자 정보 수정 -  **member** 는 사용자 정보 입력 파라미터 JSON 입니다. 리턴 값은 성공/실패 여부 판단 값입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var member = new SolidModel.Member(); // Member | 멤버 정보 JSON


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.updateUser(member, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **member** | [**Member**](Member.md)| 멤버 정보 JSON | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="updateUserContent"></a>
# **updateUserContent**
> Response updateUserContent(id, content)



Put &#x60;Content&#x60; object. - 사용자 컨텐츠 정보 수정 -  **id** 는 사용자 아이디(email) 이며, **content** 는 컨텐츠 정보 입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var id = "id_example"; // String | 사용자 아이디(email)

var content = new SolidModel.Content(); // Content | 사용자 컨텐츠 정보 JSON


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.updateUserContent(id, content, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 사용자 아이디(email) | 
 **content** | [**Content**](Content.md)| 사용자 컨텐츠 정보 JSON | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, text/html

<a name="uploadUserContent"></a>
# **uploadUserContent**
> Response uploadUserContent(id, file, caId, title, rmk)



upload &#x60;3D Content&#x60; file. - 사용자 컨텐츠 정보 추가 -  **id** 는 사용자 아이디(email) 이며, **file** 은 컨텐츠 파일 입니다. **content** 는 컨텐츠 정보 입니다. 

### Example
```javascript
var SolidModel = require('solid_model');

var apiInstance = new SolidModel.DefaultApi();

var id = "id_example"; // String | 사용자 아이디(email)

var file = "/path/to/file.txt"; // File | 업로드 파일

var caId = 56; // Integer | 컨텐츠 카테고리 아이디

var title = "title_example"; // String | 컨텐츠 제목

var rmk = "rmk_example"; // String | 컨텐츠 내용


var callback = function(error, data, response) {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
};
apiInstance.uploadUserContent(id, file, caId, title, rmk, callback);
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 사용자 아이디(email) | 
 **file** | **File**| 업로드 파일 | 
 **caId** | **Integer**| 컨텐츠 카테고리 아이디 | 
 **title** | **String**| 컨텐츠 제목 | 
 **rmk** | **String**| 컨텐츠 내용 | 

### Return type

[**Response**](Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json, text/html

