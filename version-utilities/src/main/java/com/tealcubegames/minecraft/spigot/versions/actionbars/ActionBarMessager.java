package com.tealcubegames.minecraft.spigot.versions.actionbars;

import com.tealcubegames.minecraft.spigot.versions.api.actionbars.ActionBarMessage;
import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.Bukkit;

public final class ActionBarMessager {

    private static final String NMS_VERSION;

    static {
        String serverPackageName = Bukkit.getServer().getClass().getPackage().getName();
        NMS_VERSION = serverPackageName.substring(serverPackageName.lastIndexOf("." + 1));
    }

    private ActionBarMessager() {
        // do nothing
    }

    public static ActionBarMessage createActionBarMessage(String message) {
        switch (NMS_VERSION) {
            case "v1_10_R1":
                return new com.tealcubegames.minecraft.spigot.versions.v1_10_R1.actionbars.ActionBarMessageImpl(message);
            default:
                throw new NotImplementedException(NMS_VERSION + " is not supported");
        }
    }

}
