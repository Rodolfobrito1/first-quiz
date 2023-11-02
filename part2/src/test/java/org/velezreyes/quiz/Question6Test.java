package org.velezreyes.quiz.question6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private static VendingMachineImpl instance = null;
    private Map<String, Integer> drinkPrices;
    private Map<String, Boolean> fizzyDrinks;
    private int moneyInserted;

    private VendingMachineImpl() {
        drinkPrices = new HashMap<>();
        drinkPrices.put("ScottCola", 75);
        drinkPrices.put("KarenTea", 100);

        fizzyDrinks = new HashMap<>();
        fizzyDrinks.put("ScottCola", true);
        fizzyDrinks.put("KarenTea", false);

        moneyInserted = 0;
    }

    public static VendingMachineImpl getInstance() {
        if (instance == null) {
            instance = new VendingMachineImpl();
        }
        return instance;
    }

    @Override
    public void insertQuarter() {
        moneyInserted += 25;
    }

    @Override
    public Drink pressButton(String drinkName) throws NotEnoughMoneyException, UnknownDrinkException {
        if (drinkPrices.containsKey(drinkName)) {
            int price = drinkPrices.get(drinkName);
            boolean isFizzy = fizzyDrinks.get(drinkName);

            if (moneyInserted >= price) {
                moneyInserted -= price;
                return new Drink(drinkName, isFizzy);
            } else {
                throw new NotEnoughMoneyException();
            }
        } else {
            throw new UnknownDrinkException();
        }
    }

    @Override
    public void reset() {
        moneyInserted = 0;
    }
}
