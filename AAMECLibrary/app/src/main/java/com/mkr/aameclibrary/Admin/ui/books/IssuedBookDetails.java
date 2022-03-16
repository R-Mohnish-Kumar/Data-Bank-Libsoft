package com.mkr.aameclibrary.Admin.ui.books;

public class IssuedBookDetails {
    String pushId;
    String issuerId;
    String issuerName;
    String title;
    String author;
    String publisher;
    String dateOfIssue;
    String dateOfReturn;
    String status;


    IssuedBookDetails(){

    }
    public IssuedBookDetails(String issuerId,String title, String author, String publisher, String dateOfIssue, String issuerName,String dateOfReturn,String pushId,String status) {
        this.issuerId=issuerId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.dateOfIssue = dateOfIssue;
        this.issuerName=issuerName;
        this.dateOfReturn=dateOfReturn;
        this.pushId=pushId;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
