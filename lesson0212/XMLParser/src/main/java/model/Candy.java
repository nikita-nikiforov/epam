package model;

import java.util.ArrayList;
import java.util.List;

public class Candy {
    private String name;
    private int energy;
    private String type;
    private List<Ingredient> ingredients = new ArrayList<>();
    private Value value;
    private String manufacturer;

    public Candy() {
    }

    public Candy(String name, int energy, String type, List<Ingredient> ingredients,
                 Value value, String manufacturer) {
        this.name = name;
        this.energy = energy;
        this.type = type;
        this.ingredients = ingredients;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Candy{" +
                "name='" + name + '\'' +
                ", energy=" + energy +
                ", type='" + type + '\'' +
                ", ingredients=" + ingredients +
                ", value=" + value +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
