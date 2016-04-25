package anaware.flatbufferssample.json;

import java.util.ArrayList;
import java.util.List;

import anaware.flatbufferssample.flatbuffersgen.People;

public class PeopleJson {
    public String id;
    public long index;
    public String guid;
    public String name;
    public String gender;
    public String company;
    public String email;
    public List<FriendJson> friends;

    public PeopleJson() {
    }

    public PeopleJson(People peoples) {
        this.id = peoples.id();
        this.index = peoples.index();
        this.guid = peoples.guid();
        this.name = peoples.name();
        this.gender = peoples.gender();
        this.company = peoples.company();
        this.email = peoples.email();
        this.friends = new ArrayList<>(peoples.friendsLength());
        for (int i = 0; i < peoples.friendsLength(); i++) {
            this.friends.add(new FriendJson(peoples.friends(i)));
        }
    }
}
