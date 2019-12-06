package com.example.restapp.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Banks")
public class Banks {

    private String bankName;
    private String url;

    public Banks(String bankName, String url) {
        this.bankName = bankName;
        this.url = url;
    }

    public Banks() {
    }

    public Banks(String bankName) {
        this.bankName = bankName;
    }

    @DynamoDBHashKey(attributeName = "bankName")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @DynamoDBAttribute(attributeName = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
