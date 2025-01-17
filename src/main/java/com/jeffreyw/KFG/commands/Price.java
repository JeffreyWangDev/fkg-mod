package com.jeffreyw.KFG.commands;

import com.jeffreyw.KFG.utils.ChatUtils;
import com.jeffreyw.KFG.utils.Constants;
import com.jeffreyw.KFG.utils.ItemsAttrs;
import com.jeffreyw.KFG.utils.Requests;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import scala.Console;

import java.util.ArrayList;
import java.util.List;

public class Price  extends CommandBase {
    private GuiNewChat chat;

    public String getCommandInfo(){
        return EnumChatFormatting.BLUE + "craftprice (c)"+EnumChatFormatting.GOLD+": Run this command for lowest way to upgrade your kuudra loot to higher attribute levels";
    }

    @Override
    public String getCommandName() {
        return "craftprice";
    }

//    @Override
//    public String getCommandUsage(ICommandSender sender) {
//        return "";
//    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return Constants.prefix + EnumChatFormatting.DARK_RED
                + "Correct Usage: " + EnumChatFormatting.RED
                + "/" + getCommandName() + " <attribute> <level> (item name, you can also hold the item)";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList();
        aliases.add("cp");
        aliases.add("c");
        return aliases;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(this.chat == null) this.chat = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        EntityPlayer player = (EntityPlayer) sender;
        if(args.length < 2) {
            this.wrongUsage(sender);
            return;
        }
        if(args.length >= 3){
            Requests req = new Requests();
            String name = args[2];
            if(args.length == 4){
                name += args[3];
            }
            req.sendGetRequest("/v0/api/craftprice","name="+name+"&attr1="+ args[0] +"&lvl1="+args[1],
                    new Requests.ResponseCallback() {
                        @Override
                        public void onResponse(String resp) {
                            boolean add_first = true;
                            sender.addChatMessage(new ChatComponentText(resp));
                            for(String i:resp.split("NEWLN")){
                                sender.addChatMessage(ChatUtils.decodeToFancyChatMessage(i,add_first));
                                add_first = false;
                            }
                        }
                        @Override
                        public void onError(Exception e) {
                            sender.addChatMessage(new ChatComponentText(
                                    e.toString()
                            ));
                        }
                    });
            return;
        }
        try{
            ItemsAttrs itemAttrs = new ItemsAttrs(player.getHeldItem());

            Requests req = new Requests();

            req.sendGetRequest("/v0/api/craftprice","name="+itemAttrs.id+"&attr1="+args[0]+"&lvl1="+args[1],
                    new Requests.ResponseCallback() {
                        @Override
                        public void onResponse(String resp) {
                            boolean add_first = true;
                            for(String i:resp.split("NEWLN")){
                                sender.addChatMessage(ChatUtils.decodeToFancyChatMessage(i,add_first));
                                add_first = false;
                            }
                        }
                        @Override
                        public void onError(Exception e) {
                            sender.addChatMessage(new ChatComponentText(
                                    EnumChatFormatting.DARK_RED+"Connection error: " + EnumChatFormatting.DARK_PURPLE+ e.toString()
                            ));
                        }
                    });
            return;
        }catch (Exception ignored){

        }
        this.wrongUsage(sender);

    }
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> options = new ArrayList<>();
        if(args.length == 1) for(String attribute : Constants.supportedAttributes) {
            if(attribute.toLowerCase().startsWith(args[0].toLowerCase())) {
                options.add(attribute.replaceAll(" ", ""));
            }
        }else if (args.length == 2) for (int i = 1; i < 11; i++) {
            options.add(((Integer)i).toString());
        }else if(args.length == 3)for(String itemID : Constants.supportedItems){
            if(itemID.toLowerCase().startsWith(args[2].toLowerCase())) {
                options.add(itemID.replaceAll(" ", ""));
            }
        }
        return options;
    }

    private void wrongUsage(ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
    }
}
