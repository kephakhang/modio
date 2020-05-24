package com.youngplussoft.jpa.entity;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;


public class Error {
  private Long errcode = null;

  private String message = null;
  
  private String rawMessage = null;

  public Error errcode(Long errcode) {
    this.errcode = errcode;
    return this;
  }

   /*
   * 에러 코드 번호
   * @return errcode
  **/
  @ApiModelProperty(value = "에러 코드 번호")
  public Long getErrcode() {
    return errcode;
  }

  public void setErrcode(Long errcode) {
    this.errcode = errcode;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

   /*
   * 에러 메세지
   * @return message
  **/
  @ApiModelProperty(value = "에러 메세지")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  
//=============================================
  public Error rawMessage(String message) {
	    this.rawMessage = message;
	    return this;
	  }

   /*
   * Raw 응답 메세지
   * @return rawMessage
  **/
  @ApiModelProperty(value = "Raw 응답 메세지")
  public String getRawMessage() {
    return rawMessage;
  }

  public void setRawMessage(String message) {
    this.rawMessage = message;
  }

  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.errcode, error.errcode) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.rawMessage, error.rawMessage) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(errcode, message, rawMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");

    sb.append("\"errcode\": ").append(toIndentedString(errcode)).append(",\n");
    sb.append("\"message\": \"").append(toIndentedString(message)).append("\",\n");
    sb.append("\"rawMessage\": \"").append(toIndentedString(rawMessage)).append("\"\n");
    sb.append("}");
    return sb.toString();
  }

  /*
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

