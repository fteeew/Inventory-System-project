package com.example.user.project;

class Customer {
        private String name;
        private String phone;
        private String address;
        private int voidInd;
        public Customer(){

        }
        public Customer(String name,String address,String phone,int voidInd){
            this.name=name;
            this.phone=phone;
            this.address=address;
            this.voidInd=voidInd;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getVoidInd() {
            return voidInd;
        }

        public void setVoidInd(int voidInd) {
            this.voidInd = voidInd;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    "}";
        }
    }


