export class Logout {

  constructor(
      
      public id: string,		//사용자 아이디(email)
      public accessToken: string	// 접근허가 토큰
  ) {  }

}