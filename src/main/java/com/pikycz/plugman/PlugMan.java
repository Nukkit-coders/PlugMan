package com.pikycz.plugman;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import com.pikycz.plugman.utils.PluginUtil;

/**
 *
 * @author PikyCZ
 */
public class PlugMan extends PluginBase {

    public String PluginPrefix = "§7[§aPlugMan§7]";

    private static PlugMan instance;

    public static PlugMan getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().toLowerCase().equals("plugman")) {
            if (!sender.hasPermission("plugman.main")) {
                sender.sendMessage(PluginPrefix + TextFormat.RED + " You don't have permission to use this command.");
                return false;
            }

            if (args.length == 0) {
                sender.sendMessage(TextFormat.GOLD + "-- " + PluginPrefix + " --");
                sender.sendMessage(TextFormat.GREEN + "/plugman enable <plugin>" + TextFormat.YELLOW + "- Enable Plugin");
                sender.sendMessage(TextFormat.GREEN + "/plugman disable <plugin>" + TextFormat.YELLOW + "- Disable Plugin");
            } else {
                switch (args[0]) {

                    case "enable":
                        Plugin target = PluginUtil.getPluginByName(args, 1);
                        if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("*")) {
                            if (sender.hasPermission("plugman.all")) {
                                PluginUtil.enableAll();
                                sender.sendMessage("§9All plugins have been enabled");
                            } else {
                                sender.sendMessage("§cYou do not have permission to do this.");
                            }
                            return false;
                        }

                        if (target == null) {
                            sender.sendMessage("§cThat is not a valid plugin.");
                            return true;
                        }

                        if (target.isEnabled()) {
                            sender.sendMessage("§c" + target + " is already enabled.");
                            return true;
                        }

                        PluginUtil.enable(target);

                        sender.sendMessage("§9 " + target + "has been enabled.");
                        break;
                    case "disable":
                        Plugin target1 = PluginUtil.getPluginByName(args, 1);
                        if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("*")) {
                            if (sender.hasPermission("plugman.all")) {
                                PluginUtil.disableAll();
                                sender.sendMessage("§9All plugins have been disabled.");
                            } else {
                                sender.sendMessage("§cYou do not have permission to do this.");
                            }
                            return false;
                        }

                        if (target1 == null) {
                            sender.sendMessage("§cThat is not a valid plugin.");
                            return true;
                        }

                        if (!target1.isEnabled()) {
                            sender.sendMessage("§c" + target1 + "is already disabled.");
                            return true;
                        }

                        PluginUtil.disable(target1);

                        sender.sendMessage("§9" + target1 + "has been disabled.");
                        break;
                    default:
                        sender.sendMessage(PluginPrefix + " Unknow command.");
                        break;
                }
            }
        }
        return true;
    }

}
