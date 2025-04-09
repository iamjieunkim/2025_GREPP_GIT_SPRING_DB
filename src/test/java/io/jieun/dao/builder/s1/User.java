package io.jieun.dao.builder.s1;

public class User {

    private String name;
    private Integer age;
    private String email;
    private String address;

    public static Builder builder() {
        return new Builder();
    }

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.address = builder.address;
    }


    public static class Builder {

        private String name;
        private Integer age;
        private String email;
        private String address;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public User builder() {
            return new User(this);
        }


    }


}
