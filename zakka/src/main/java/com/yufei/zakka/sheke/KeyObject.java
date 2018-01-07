package com.yufei.zakka.sheke;

public class KeyObject {
    private String word1;
    private String word2;

    public KeyObject(String word1, String word2) {
        super();
        this.word1 = word1;
        this.word2 = word2;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((word1 == null) ? 0 : word1.hashCode());
        result = prime * result + ((word2 == null) ? 0 : word2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KeyObject other = (KeyObject) obj;
        if (word1 == null) {
            if (other.word1 != null)
                return false;
        } else if (!word1.equals(other.word1))
            return false;
        if (word2 == null) {
            if (other.word2 != null)
                return false;
        } else if (!word2.equals(other.word2))
            return false;
        return true;
    }

}
