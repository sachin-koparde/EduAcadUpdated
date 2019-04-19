package com.handy;

public class post {
    private String name,usn,branch,sem,password;

    public post(){

    }



    public post(String name, String usn, String branch, String sem, String password) {
        this.name = name;
        this.usn = usn;
        this.branch = branch;
        this.sem = sem;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
