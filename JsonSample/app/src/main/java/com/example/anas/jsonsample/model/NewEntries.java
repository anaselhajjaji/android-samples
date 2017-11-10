
package com.example.anas.jsonsample.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewEntries {

    @SerializedName("has_next")
    @Expose
    private Boolean hasNext;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("results")
    @Expose
    private List<Object> results = null;

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

}
