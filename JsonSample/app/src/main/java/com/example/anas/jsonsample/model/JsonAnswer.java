
package com.example.anas.jsonsample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonAnswer {

    @SerializedName("new_entries")
    @Expose
    private NewEntries newEntries;
    @SerializedName("league")
    @Expose
    private League league;
    @SerializedName("standings")
    @Expose
    private Standings standings;
    @SerializedName("update_status")
    @Expose
    private Integer updateStatus;

    public NewEntries getNewEntries() {
        return newEntries;
    }

    public void setNewEntries(NewEntries newEntries) {
        this.newEntries = newEntries;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Standings getStandings() {
        return standings;
    }

    public void setStandings(Standings standings) {
        this.standings = standings;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

}
