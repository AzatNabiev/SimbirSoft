package ru.itis.javalab.models;

import lombok.Data;

import java.util.Objects;

@Data
public class Word {
    private Integer id;
    private String value;
    private Integer count;
    
    public Word(){}
    public Word(Integer id, String value, Integer count){
        this.id=id;
        this.value=value;
        this.count=count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    public static Builder builder(){
        return new Word().new Builder();
    }

    public class Builder {
        private Word word;

        public Builder(){
            word = new Word();
        }
        public Builder value(String value){
            word.value = value;
            return this;
        }
        public Builder id(Integer id){
            word.id = id;
            return this;
        }
        public Builder count(Integer count){
            word.count = count;
            return this;
        }
        public Word build(){
            return  word;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return getId().equals(word.getId()) &&
                getValue().equals(word.getValue()) &&
                getCount().equals(word.getCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), getCount());
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", count=" + count +
                '}';
    }
}

