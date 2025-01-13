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

import java.util.ArrayList;
import java.util.List;

public class Price  extends CommandBase {
    private GuiNewChat chat;

    @Override
    public String getCommandName() {
        return "value";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

//    @Override
//    public String getCommandUsage(ICommandSender sender) {
//        return Constants.prefix + EnumChatFormatting.DARK_RED
//                + "Correct Usage: " + EnumChatFormatting.RED
//                + "/" + getCommandName() + " <attribute> <item> <from> <to>\n"
//                + EnumChatFormatting.RED + "Make sure to enter attributes and items without spaces.";
//    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList();
        aliases.add("v");
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

        try{
            ItemsAttrs itemAttrs = new ItemsAttrs(player.getHeldItem());

            Requests req = new Requests();

            req.sendGetRequest("/v0/api/price","attr1=",
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
        }catch (Exception e){
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED+"This item is not kuudra loot!"));
        }
    }
    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> options = new ArrayList<>();
        if(args.length < 5) for(String attribute : Constants.supportedAttributes) {
            if(attribute.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) {
                options.add(attribute.replaceAll(" ", ""));
            }
        }
        return options;
    }

    private void wrongUsage(ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
    }

}
