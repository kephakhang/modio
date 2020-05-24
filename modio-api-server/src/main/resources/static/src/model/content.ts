export class Content {

  constructor(
	  public accessToken: string, //사용자 접근 토큰
      public id: string, //컨텐츠 아이디
      public mbId: string, //사용자 아이디(email)
      public caId: number, //컨텐츠 카테고리 아이디      
      public title: string, // 컨텐츠 제목
      public rmk: string, // 컨텐츠 내용  
      public open: string, //공개 여부 값
      public caName?: string, // 컨텐츠 카테고리 명
      public fname?: string, // 컨텐츠 원본 파일 명    
      public ftype?: string, // 컨텐츠 원본 파일 타입
      public fsize?: number, // 컨텐츠 원본 파일 사이즈
      public regtime?: string, // 컨텐츠 등록 시각
      public updtime?: string // 컨텐츠 수 시각
  ) {  }

}