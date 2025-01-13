package com.jeffreyw.KFG;
import com.jeffreyw.KFG.commands.Value;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = KFG.MODID, version = KFG.VERSION)
public class KFG {
    public static final String MODID = "KFG";
    public static final String VERSION = "BETA-0.0.1";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
//        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        ClientCommandHandler.instance.registerCommand(new Value());
    }
}
