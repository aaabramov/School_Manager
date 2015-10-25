/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

/**
 *
 * @author abrasha
 */
public class DatabaseIndexes {

    public static class Students {

        public static final String TABLE = "students";
        public static final String ID_STUDENT = "id_student";
        public static final String FIRST_NAME = "fname";
        public static final String LAST_NAME = "lname";
        public static final String PATRONYMIC = "patronymic";
        public static final String BIRTHDAY = "bday";
        public static final String PHONE = "phone";
        public static final String ADDRESS = "address";
        public static final String ID_GROUP = "id_group";
        public static final String NOTES = "notes";
    }

    public static class Teachers {

        public static final String TABLE = "teachers";
        public static final String ID_TEACHER = "id_teacher";
        public static final String FIRST_NAME = "fname";
        public static final String LAST_NAME = "lname";
        public static final String PATRONYMIC = "patronymic";
        public static final String BDAY = "bday";
        public static final String PHONE = "phone";
        public static final String ADDRESS = "address";
        public static final String SUBJECTS = "subjects";
        public static final String NOTES = "notes";

    }

    public static class Parents {

        public static final String TABLE = "parents";
        public static final String ID_PARENT = "id_parent";
        public static final String FIRST_NAME = "fname";
        public static final String LAST_NAME = "lname";
        public static final String PATRONYMIC = "patronymic";
        public static final String PHONE = "phone";
        public static final String ADDRESS = "address";
        public static final String JOB = "job";
        public static final String NOTES = "notes";
    }

    public static class Subjects {

        public static final String TABLE = "subjects";
        public static final String ID_SUBJECT = "id_subject";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
    }

    public static class Families {

        public static final String TABLE = "families";
        public static final String ID_SUBJECT = "id_parent";
        public static final String NAME = "id_student";
    }

    public static class Groups {

        public static final String TABLE = "groups";
        public static final String ID_GROUP = "id_group";
        public static final String ID_CURATOR = "id_curator";
        public static final String CODE = "code";
        public static final String NOTE = "notes";

    }

    public static class Journal {

        public static final String TABLE = "journal";
        public static final String ID_LESSON = "id_lesson";
        public static final String ID_STUDENT = "id_student";
        public static final String MARK = "mark";
        public static final String MARK_TYPE = "mark_type";
        public static final String PRESENCE = "presence";
    }

    public static class Schedule {

        public static final String TABLE = "schedule";
        public static final String ID_DAY = "id_day";
        public static final String ID_GROUP = "id_group";
        public static final String ID_LESSON = "id_lesson";
        public static final String ID_SUBJECT = "id_subject";
        public static final String ID_TEACHER = "id_teacher";
        public static final String NUMBER = "number";
    }

    public static class Users {

        public static final String TABLE = "users";
        public static final String ID_USER = "id_user";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String ACC_TYPE = "acc_type";
    }

    public static class Admins {

        public static final String TABLE = "admins";
        public static final String ID_ADMIN = "id_admin";
        public static final String LAST_SEEN = "last_seen";
    }

}
