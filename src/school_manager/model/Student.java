/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

/**
 *
 * @author abrasha
 */
public class Student extends Person {

    private final int groupId;

    public int getGroupId() {
        return groupId;
    }

    public static class Builder extends Person.Builder<Student.Builder> {

        private int groupId;

        public Builder idGroup(int groupId) {
            this.groupId = groupId;
            return this;
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
        return getInitials();

    }

}
