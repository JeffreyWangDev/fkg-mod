package com.jeffreyw.KFG.commands;

import com.jeffreyw.KFG.utils.Constants;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class Fkg extends CommandBase {
    public String getCommandInfo(){
        return EnumChatFormatting.BLUE + "kfg"+EnumChatFormatting.GOLD+": Run this command for help!";
    }
    @Override
    public String getCommandName() {
        return "fkg";
    }
    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList();
        aliases.add("forkuudragrinders");
        return aliases;
    }
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Run /fkg for help!";
    }
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        String text = Constants.prefix;
        text+="Commands:";
        sender.addChatMessage(new ChatComponentText(text));
        List<String> commands = new ArrayList();
        commands.add(this.getCommandInfo());
        commands.add(new Value().getCommandInfo());
        commands.add(new Price().getCommandInfo());

        for (String cmInfo : commands) {
            sender.addChatMessage(new ChatComponentText("   "+cmInfo));
        }
    }
}
