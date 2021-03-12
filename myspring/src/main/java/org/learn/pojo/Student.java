package org.learn.pojo;

public class Student {

    private String sno;
    private String sname;
    private String ssex;
    private String sbirthday;

    public Student(){}

    public Student(String no, String name, String sex, String sbirthday) {
        this.sno = no;
        this.sname = name;
        this.ssex = sex;
        this.sbirthday = sbirthday;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sbirthday='" + sbirthday + '\'' +
                '}';
    }
}
