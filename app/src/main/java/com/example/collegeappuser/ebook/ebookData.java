package com.example.collegeappuser.ebook;

public class ebookData {
    private String name,pdfUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ebookData() {
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public ebookData(String name, String pdfUrl) {
        this.name = name;
        this.pdfUrl = pdfUrl;
    }
}
