package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.exceptions.InvalidPluginException;
import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.logger.Logman;

/**
 * Command to enable, disable or reload plugins
 *
 * @author Chris (damagefilter)
 */
public class PluginCommand implements NativeCommand {
    private boolean disable;
    private boolean reload;
    private boolean permanent = false;

    public PluginCommand(boolean disable, boolean reload) {
        this.reload = reload;
        this.disable = reload ? false : disable;
    }

    public void execute(MessageReceiver caller, String[] parameters) {
        checkConditions(parameters);
        String plugin = parameters[parameters.length - 1];

        if (reload) {
            reload(caller, plugin);
        }
        else {
            if (disable) {
                disable(caller, plugin, permanent);
            }
            else {
                enable(caller, plugin);
            }
        }
    }

    private void reload(MessageReceiver caller, String plugin) {
        try {
            if (Canary.manager().reloadPlugin(plugin)) {
                caller.notice(Translator.translateAndFormat("plugin reloaded", plugin));
            }
            else {
                caller.notice(Translator.translateAndFormat("plugin reloaded fail", plugin));
            }
        }
        catch (PluginLoadFailedException e) {
            caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            Logman.getLogman("pluginCommand").error("Failed to load plugin", e);
        }
        catch (InvalidPluginException e) {
            caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            Logman.getLogman("pluginCommand").error("Failed to load plugin", e);
        }
    }

    private void enable(MessageReceiver caller, String plugin) {
        // TODO: Take into consideration the permanent value!
        try {
            if (Canary.manager().enablePlugin(plugin)) {
                caller.notice(Translator.translateAndFormat("plugin enabled", plugin));
            }
            else {
                caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            }
        }
        catch (PluginLoadFailedException e) {
            caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            Logman.getLogman("pluginCommand").error("Failed to load plugin", e);
        }
    }

    private void disable(MessageReceiver caller, String plugin, boolean permanent) {
        if (Canary.manager().disablePlugin(plugin)) {
            //TODO: Permanent flag in canary
            if (permanent) {
//                Canary.manager().moveToDisabled(plugin);
            }
            caller.notice(Translator.translateAndFormat("plugin disabled", plugin));
        }
        else {
            caller.notice(Translator.translateAndFormat("plugin disabled fail", plugin));
        }
    }

    /**
     * Check if we have a permanent disable/enable requests
     *
     * @param params
     *
     * @return
     */
    private boolean getPermanentParameter(String[] params) {
        return params[params.length - 2].equalsIgnoreCase("-p");
    }

    /**
     * Analyze the command input and set the disable, permanent and reload booleans accordingly
     *
     * @param params
     */
    private void checkConditions(String[] params) {
        if (params[0].toLowerCase().startsWith("plugin", 1)) {
            if (params.length == 4) {
                // we have a permanent condition (still check if the flag is right!)
                this.permanent = getPermanentParameter(params);
            }
            if (params[1].equalsIgnoreCase("reload")) {
                reload = true;
                disable = false;
            }
            else if (params[1].equalsIgnoreCase("enable")) {
                disable = false;
                reload = false;
            }
            else {
                disable = true;
                reload = false;
            }
        }
        else {
            if (params.length == 3) {
                // we have a permanent condition (still check if the flag is right!)
                this.permanent = getPermanentParameter(params);
            }
            if (params[0].toLowerCase().contains("reloadplugin")) {
                reload = true;
                disable = false;
            }
            else if (params[0].toLowerCase().contains("enableplugin")) {
                disable = false;
                reload = false;
            }
            else if (params[0].toLowerCase().contains("disableplugin")) {
                disable = true;
                reload = false;
            }
            else {
                throw new CommandException("Found invalid command structure! Should be a plugin command. But command is " + params[0]);
            }

        }
    }

}