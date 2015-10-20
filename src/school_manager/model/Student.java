/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import school_manager.helpers.DatabaseIndexes;

/**
 *
 * @author abrasha
 */
public class Student extends Person {

    @JsonProperty(DatabaseIndexes.Students.ID_GROUP)
    private int groupId;
    
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public static class Builder extends Person.Builder<Student.Builder> {

        private int groupId;
        private int studentId;

        public Builder() {
        }

        public Builder idGroup(int groupId) {
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
                //+ "\nStudent id: " + studentId
                + "\nClass: " + groupId
                + "\nAddress: " + getAddress()
                + "\nPhone:" + getPhone()
                + "\nBirthday: " + getBirthday();

        return result;

    }

}
