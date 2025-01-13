package com.jeffreyw.KFG.utils;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import scala.Console;

// https://github.com/anderle02/attributemod/blob/master/src/main/java/dev/anderle/attributemod/utils/ChatUtils.java

public class ChatUtils {
    /**
     *  Convert text received from API to a nice text message.
     *  - substrings that start with "%t" will be converted to normal (colored) text
     *  - substrings that start with "%i" will add a ClickEvent to run the /viewauction command for auction id
     *  - substrings that start with "%n" will be shown as tooltip on HoverEvent
     */

    public static IChatComponent decodeToFancyChatMessage(String string, boolean add_first) {
        IChatComponent comp = new ChatComponentText(add_first ? Constants.prefix : "");
        for(String part : string.replaceAll("§", "\u00a7").split("#")) {
            if(part.startsWith("t")) {
                comp.appendSibling(new ChatComponentText( EnumChatFormatting.RESET+ part.replaceFirst("t", "").replace("GREEN", EnumChatFormatting.GREEN.toString())));
            } else if(part.startsWith("i")) {
                Console.println(part.replaceFirst("i", ""));
                comp.getSiblings().get(comp.getSiblings().size() - 1).getChatStyle()
                        .setChatClickEvent(new ClickEvent(
                                ClickEvent.Action.RUN_COMMAND,
                                "/viewauction " + part.replaceFirst("i", "").replace("\"","")
                        ) {
                            @Override
                            public Action getAction() {
                                return Action.RUN_COMMAND;
                            }
                        });
            } else if(part.startsWith("n")) {
                comp.getSiblings().get(comp.getSiblings().size() - 1).getChatStyle()
                        .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(
                                part.replaceFirst("n", "")
                        )));
            }
        }
        return comp;
    }
}
