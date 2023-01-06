package me.ordalca.plaplates.init;

import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.api.pokemon.ability.AbilityRegistry;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.battles.attacks.specialAttacks.basic.Judgment;
import com.pixelmonmod.pixelmon.battles.controller.log.AttackResult;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;

public class AdvancedJudgment extends Judgment {
    @Override
    public AttackResult applyEffectStart(PixelmonWrapper user, PixelmonWrapper target) {
        if (user.getSpecies().is(PixelmonSpecies.ARCEUS)) {
            user.attack.overrideType(this.getOverrideType(user, target));
        }
        return AttackResult.proceed;
    }

    private Element getOverrideType(PixelmonWrapper user, PixelmonWrapper target) {
        Element type;
        boolean hasLegendPlate = (user.getHeldItem() instanceof LegendPlateItem);
        boolean isMultitype = user.getBattleAbility().isAbility(AbilityRegistry.MULTITYPE);
        if (hasLegendPlate && isMultitype) {
            LegendPlateItem item = (LegendPlateItem) user.getHeldItem();
            type = item.getNewType(target);
            if (user.type.size() != 1 || user.type.get(0) != type) {
                user.bc.sendToAll("pixelmon.effect.changetype", user.getNickname(), type.getLocalizedName());
                user.setForm(type.getName());
            }
        } else {
            type = user.type.get(0);
            if (user.attack.getType() == Element.MYSTERY) {
                type = Element.NORMAL;
            }
        }
        return type;
    }
}
