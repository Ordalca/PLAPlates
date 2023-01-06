package me.ordalca.plaplates;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.moveskills.UseMoveSkillEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.registries.PixelmonBlocks;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.battles.attacks.EffectTypeAdapter;
import me.ordalca.plaplates.init.AdvancedJudgment;
import me.ordalca.plaplates.init.ItemInit;
import me.ordalca.plaplates.init.LegendPlateItem;
import me.ordalca.plaplates.init.BlankPlateItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModFile.MOD_ID)
@Mod.EventBusSubscriber(modid = ModFile.MOD_ID)
public class ModFile {

    public static final String MOD_ID = "plaplates";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static ModFile instance;

    public ModFile() {
        instance = this;

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        ItemInit.setup(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        Pixelmon.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Loaded PLAPlate mod");
        EffectTypeAdapter.EFFECTS.put("Judgment", AdvancedJudgment.class);
    }

    public static ModFile getInstance() {
        return instance;
    }
    public static Logger getLogger() {
        return LOGGER;
    }

    @SubscribeEvent
    public void useMoveSkill(UseMoveSkillEvent event) {
        Pokemon pokemon = event.pixelmon.getPokemon();
        if (event.moveSkill.id.equals("forage") && pokemon.getSpecies().is(PixelmonSpecies.ARCEUS)) {
            Tuple<BlockPos, Direction> data = (Tuple)event.data;
            if (event.pixelmon.level.getBlockState((BlockPos)data.getA()).getBlock() == PixelmonBlocks.timespace_altar.getBlock()) {
                if (pokemon.getOwnerPlayer().inventory.getFreeSlot() != -1) {
                    if (pokemon.getFriendship() == 255 && !hasData("foundLegendPlate", pokemon)) {
                        pokemon.getOwnerPlayer().inventory.add(ItemInit.getItem("plaplates:legend_plate"));
                        pokemon.getPersistentData().putBoolean("foundLegendPlate", true);
                    } else if (!hasData("foundBlankPlate", pokemon)) {
                        pokemon.getOwnerPlayer().inventory.add(ItemInit.getItem("plaplates:blank_plate"));
                        pokemon.getPersistentData().putBoolean("foundBlankPlate", true);
                    }
                    event.setCanceled(true);
                }
            }
        }
    }

    public boolean hasData(String field, Pokemon pokemon) {
        return pokemon.getPersistentData().getBoolean(field);
    }
}
