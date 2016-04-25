package anaware.flatbufferssample.json;

import anaware.flatbufferssample.flatbuffersgen.Friend;

public class FriendJson {
    public long id;
    public String name;

    public FriendJson() {
    }

    public FriendJson(Friend friends) {
        this.id = friends.id();
        this.name = friends.name();
    }
}
