package com.trustinlies.supernatural.util.commands;

import com.trustinlies.supernatural.Main;
import com.trustinlies.supernatural.util.Reference;
import com.trustinlies.supernatural.util.objects.guis.SkillsGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandProfessions implements ICommand {

    private List aliases = null;


    public CommandProfessions(){
        aliases = new ArrayList();
        aliases.add("skills");
        aliases.add("prof");
        aliases.add("professions");
    }

    @Override
    public String getName() {
        return "professions";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "professions";
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) sender;
            Minecraft.getMinecraft().displayGuiScreen(new SkillsGUI(player));

        }
        else{
            System.out.println("This is a player only command.");
        }

    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
