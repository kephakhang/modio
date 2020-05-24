package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Entity
@Document(collection="pointCharge")
public class PointCharge  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String paygate ;

    private String orderNo;

    private String userId;

    private Date regtime;

    private Double amount;

    private String productDesc;

    private String status ;

    private Double vat;

    private boolean autoExecute = true;

    private boolean cashReceipt = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public boolean isAutoExecute() {
        return autoExecute;
    }

    public void setAutoExecute(boolean autoExecute) {
        this.autoExecute = autoExecute;
    }

    public boolean isCashReceipt() {
        return cashReceipt;
    }

    public void setCashReceipt(boolean cashReceipt) {
        this.cashReceipt = cashReceipt;
    }

    public String getPaygate() {
        return paygate;
    }

    public void setPaygate(String paygate) {
        this.paygate = paygate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
