export class User {

  constructor(
      
      public id: string,		//사용자 아이디(email)
      public password: string,	//사용자 암호
      public level?: number,	//사용자 타입/레벨
      public name?: string,		//사용자 이름
      public tel?: string,		//사용자 전화번
      public birth?: string,	//사용자 생일
      public regtime?: string,	//사용자 등록 시각
      public updtime?: string	//사용자 정보 수정 시각
  ) {  }

}