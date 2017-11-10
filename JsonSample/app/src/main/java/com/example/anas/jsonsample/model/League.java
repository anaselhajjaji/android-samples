
package com.example.anas.jsonsample.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class League {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("leagueban_set")
    @Expose
    private List<Object> leaguebanSet = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private Object shortName;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("closed")
    @Expose
    private Boolean closed;
    @SerializedName("forum_disabled")
    @Expose
    private Boolean forumDisabled;
    @SerializedName("make_code_public")
    @Expose
    private Boolean makeCodePublic;
    @SerializedName("rank")
    @Expose
    private Object rank;
    @SerializedName("size")
    @Expose
    private Object size;
    @SerializedName("league_type")
    @Expose
    private String leagueType;
    @SerializedName("_scoring")
    @Expose
    private String scoring;
    @SerializedName("reprocess_standings")
    @Expose
    private Boolean reprocessStandings;
    @SerializedName("admin_entry")
    @Expose
    private Integer adminEntry;
    @SerializedName("start_event")
    @Expose
    private Integer startEvent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getLeaguebanSet() {
        return leaguebanSet;
    }

    public void setLeaguebanSet(List<Object> leaguebanSet) {
        this.leaguebanSet = leaguebanSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getShortName() {
        return shortName;
    }

    public void setShortName(Object shortName) {
        this.shortName = shortName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getForumDisabled() {
        return forumDisabled;
    }

    public void setForumDisabled(Boolean forumDisabled) {
        this.forumDisabled = forumDisabled;
    }

    public Boolean getMakeCodePublic() {
        return makeCodePublic;
    }

    public void setMakeCodePublic(Boolean makeCodePublic) {
        this.makeCodePublic = makeCodePublic;
    }

    public Object getRank() {
        return rank;
    }

    public void setRank(Object rank) {
        this.rank = rank;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    public String getLeagueType() {
        return leagueType;
    }

    public void setLeagueType(String leagueType) {
        this.leagueType = leagueType;
    }

    public String getScoring() {
        return scoring;
    }

    public void setScoring(String scoring) {
        this.scoring = scoring;
    }

    public Boolean getReprocessStandings() {
        return reprocessStandings;
    }

    public void setReprocessStandings(Boolean reprocessStandings) {
        this.reprocessStandings = reprocessStandings;
    }

    public Integer getAdminEntry() {
        return adminEntry;
    }

    public void setAdminEntry(Integer adminEntry) {
        this.adminEntry = adminEntry;
    }

    public Integer getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(Integer startEvent) {
        this.startEvent = startEvent;
    }

}
