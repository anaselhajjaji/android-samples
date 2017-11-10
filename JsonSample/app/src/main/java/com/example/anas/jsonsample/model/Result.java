
package com.example.anas.jsonsample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("entry_name")
    @Expose
    private String entryName;
    @SerializedName("event_total")
    @Expose
    private Integer eventTotal;
    @SerializedName("player_name")
    @Expose
    private String playerName;
    @SerializedName("movement")
    @Expose
    private String movement;
    @SerializedName("own_entry")
    @Expose
    private Boolean ownEntry;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("last_rank")
    @Expose
    private Integer lastRank;
    @SerializedName("rank_sort")
    @Expose
    private Integer rankSort;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("entry")
    @Expose
    private Integer entry;
    @SerializedName("league")
    @Expose
    private Integer league;
    @SerializedName("start_event")
    @Expose
    private Integer startEvent;
    @SerializedName("stop_event")
    @Expose
    private Integer stopEvent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public Integer getEventTotal() {
        return eventTotal;
    }

    public void setEventTotal(Integer eventTotal) {
        this.eventTotal = eventTotal;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public Boolean getOwnEntry() {
        return ownEntry;
    }

    public void setOwnEntry(Boolean ownEntry) {
        this.ownEntry = ownEntry;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getLastRank() {
        return lastRank;
    }

    public void setLastRank(Integer lastRank) {
        this.lastRank = lastRank;
    }

    public Integer getRankSort() {
        return rankSort;
    }

    public void setRankSort(Integer rankSort) {
        this.rankSort = rankSort;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public Integer getLeague() {
        return league;
    }

    public void setLeague(Integer league) {
        this.league = league;
    }

    public Integer getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(Integer startEvent) {
        this.startEvent = startEvent;
    }

    public Integer getStopEvent() {
        return stopEvent;
    }

    public void setStopEvent(Integer stopEvent) {
        this.stopEvent = stopEvent;
    }

}
