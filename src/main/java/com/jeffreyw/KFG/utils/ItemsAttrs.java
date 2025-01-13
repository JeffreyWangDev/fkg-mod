package com.jeffreyw.KFG.utils;

import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import scala.Console;

public class ItemsAttrs {
    public String attr1;
    public String attr2;
    public int lvl1;
    public int lvl2;

    //    private final Slot slot;
    public final String id;
    private final String displayName;


    public ItemsAttrs(ItemStack item) {

//        this.slot = slot;


        Console.println(item.getTagCompound().toString());
        NBTTagCompound data = item.getTagCompound();

        NBTTagCompound attributes = data.getCompoundTag("ExtraAttributes").getCompoundTag("attributes");

        Console.println(attributes.toString());
        Console.println(data.toString());
        this.attr1 = attributes.getKeySet().toArray()[0].toString();
        this.lvl1 = attributes.getInteger(this.attr1);

        try {
            this.attr2 = attributes.getKeySet().toArray()[1].toString();
            this.lvl2 = attributes.getInteger(this.attr2);
        } catch (Exception e) {
            this.attr2 = "";
        }

        this.id = data.getCompoundTag("ExtraAttributes").getString("id").toLowerCase();
        this.displayName = data.getCompoundTag("display").getString("Name");
    }

    public String toString() {
        return attr1 + ":" + attr2 + ":" + lvl1 + ":" + lvl2;
    }

}
