package despresso.logic;

import java.util.ArrayList;
import java.util.Arrays;

public class Mood {
    private final String name;
    private final ArrayList<String> subMoods;

    public Mood(String name, String[] subMoods) {
        this.name = name;
        this.subMoods = new ArrayList<>(Arrays.asList(subMoods));
        System.out.println("SubMoods: " + this.subMoods);
    }

    public Mood(String name) {
        System.out.println("Submood: " + name);
        this.name = name;
        this.subMoods = null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSubMoods() {
        System.out.println("Mood: Requested submoods for " + this.name + ": " + this.subMoods);
        return this.subMoods;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
