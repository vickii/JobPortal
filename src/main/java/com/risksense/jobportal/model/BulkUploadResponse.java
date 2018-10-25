package com.risksense.jobportal.model;

public class BulkUploadResponse {

    public BulkUploadResponse(long validJobsPresentInFile) {
        this.validJobsPresentInFile = validJobsPresentInFile;
    }

    private long validJobsPresentInFile;

    public long getValidJobsPresentInFile() {
        return validJobsPresentInFile;
    }

    public void setValidJobsPresentInFile(long validJobsPresentInFile) {
        this.validJobsPresentInFile = validJobsPresentInFile;
    }

    @Override
    public String toString() {
        return "BulkUploadResponse{" +
                "validJobsPresentInFile=" + validJobsPresentInFile +
                '}';
    }
}
