package me.ordalca.plaplates.init;

import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.items.heldItems.PlateItem;
import me.ordalca.plaplates.ModFile;
import java.util.ArrayList;


public class LegendPlateItem extends PlateItem {
    private Element currentType = Element.NORMAL;
    public LegendPlateItem() {
        super(Element.NORMAL);
    }

    @Override
    public double preProcessDamagingAttackUser(PixelmonWrapper attacker, PixelmonWrapper target, Attack attack, double damage) {
        return attack.getMove().getAttackType() == this.currentType ? damage * 1.2D : damage;
    }

    @Override
    public void onEndOfBattle(PixelmonWrapper pw) {
        if(pw.getSpecies().is(PixelmonSpecies.ARCEUS)) {
            pw.setForm("");
        }
    }

    public Element getNewType(PixelmonWrapper target) {
        ArrayList<Element> targetElements = new ArrayList<>(target.type);

        ArrayList<Element> options = getMostEffective(targetElements, new  ArrayList<>(Element.getAllTypes()));
        if(targetElements.size() > 0) {
            options = getResist(targetElements.get(0), options);
        }
        if(targetElements.size() > 1) {
            options = getResist(targetElements.get(1), options);
        }
        int rand = random.nextInt(options.size());
        return currentType = options.get(rand);
    }
    private ArrayList<Element> getMostEffective(ArrayList<Element> targetsTypes, ArrayList<Element> options) {
        ArrayList<Element> mostEffective = new ArrayList<>();
        float maxEffectiveness = 0.0F;
        for (Element newType : options) {
            float effectiveness = Element.getTotalEffectiveness(targetsTypes, newType);
            if (effectiveness >= maxEffectiveness) {
                if (effectiveness > maxEffectiveness) {
                    mostEffective.clear();
                    maxEffectiveness = effectiveness;
                }
                mostEffective.add(newType);
            }
        }
        return mostEffective;
    }
    private ArrayList<Element> getResist(Element typing, ArrayList<Element> options) {
        ArrayList<Element> mostResistant = new ArrayList<>();
        float maxResistance = 2.0F;
        for (Element newType : options) {
            float effectiveness = Element.getEffectiveness(newType, typing).value;
            if (effectiveness <= maxResistance) {
                if (effectiveness < maxResistance) {
                    mostResistant.clear();
                    maxResistance = effectiveness;
                }
                mostResistant.add(newType);
            }
        }
        return mostResistant;
    }
}
