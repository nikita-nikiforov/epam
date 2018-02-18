package model;

public class Value {
    private int proteins;
    private int fat;
    private int carbohydrates;

    public Value() {
    }

    public Value(int proteins, int fat, int carbohydrates) {
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public String toString() {
        return "Value{" +
                "proteins=" + proteins +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
