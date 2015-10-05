/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author abrasha
 */
public class Student extends Person {

    @JsonProperty("id")
    private int groupId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public static class Builder extends Person.Builder<Student.Builder> {

        private int groupId;

        public Builder() {
        }

        public Builder id_group(int groupId) {
            this.groupId = groupId; return this;
        }

        @Override
        public Student build() {
            return (new Student(this));
        }

    }

    public Student(Student.Builder builder) {
        super(builder);
        this.groupId = builder.groupId;
    }

    @Override
    public String toString() {

        String result = "Student profile:"
                + "\nFirst name: " + getFName()
                + "\nLast name: " + getLName()
                + "\nPatronymic: " + getPatronymic()
                + "\nClass: " + groupId
                + "\nAddress: " + getAddress()
                + "\nPhone:" + getPhone()
                + "\nBirthday: " + getBirthday();

        return result;

    }

}
