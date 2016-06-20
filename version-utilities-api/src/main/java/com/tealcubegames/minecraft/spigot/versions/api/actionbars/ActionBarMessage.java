package com.tealcubegames.minecraft.spigot.versions.api.actionbars;

import org.bukkit.entity.Player;

public interface ActionBarMessage {

    void send(Player player);

    void send(Iterable<Player> players);

    String getMessage();

}
