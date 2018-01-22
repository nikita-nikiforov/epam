package coffeepoint.entity.product.drink;

// Abstract class for all Coffees
public abstract class Coffee extends Drink {
    // Level of grinding of beans. Fine grinding — 1. Medium — 2. Coarse — 3.
    // Степень помола. Реализую, когда элементы CoffeeMachine задействую)
    GrindingLevel grindingLevel = GrindingLevel.MEDIUM;
}
