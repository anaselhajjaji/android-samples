package anaware.flatbufferssample.json;

import com.dslplatform.json.CompiledJson;

import java.util.ArrayList;
import java.util.List;

import anaware.flatbufferssample.flatbuffersgen.PeopleList;

@CompiledJson
public class PeopleListJson {
    public List<PeopleJson> peoples;

    public PeopleListJson() {
    }

    public PeopleListJson(PeopleList flatbuffers) {
        this.peoples = new ArrayList<>(flatbuffers.peoplesLength());
        for (int i = 0; i < flatbuffers.peoplesLength(); i++) {
            peoples.add(new PeopleJson(flatbuffers.peoples(i)));
        }
    }

    public String howLong(long startTime) {
        long endTime = System.currentTimeMillis() - startTime;
        return "Elements: " + peoples.size() + ": load time: " + endTime + "ms";
    }
}
