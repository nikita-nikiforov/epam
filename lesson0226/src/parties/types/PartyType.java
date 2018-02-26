package parties.types;

public abstract class PartyType implements Comparable<PartyType>{
    private String name;
    private String description;
    private boolean indoors;
    private int price;
    private int duration; // in minutes

    public PartyType(String name, String description, boolean indoors, int price,
                     int duration) {
        this.name = name;
        this.description = description;
        this.indoors = indoors;
        this.price = price;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIndoors() {
        return indoors;
    }

    public void setIndoors(boolean indoors) {
        this.indoors = indoors;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int compareTo(PartyType partyType) {
        int result = price - partyType.price;
        if(result!=0)
            return result;
        result = duration - partyType.duration;
        if(result!=0)
            return result;
        return 0;
    }

    @Override
    public String toString() {
        return "PartyType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", indoors=" + indoors +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}