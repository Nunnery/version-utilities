package com.tealcubegames.minecraft.spigot.versions.v1_10_R1.actionbars;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.tealcubegames.minecraft.spigot.versions.api.actionbars.ActionBarMessage;
import net.minecraft.server.v1_10_R1.ChatComponentText;
import net.minecraft.server.v1_10_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarMessageImpl implements ActionBarMessage {

    private static final byte ACTION_BAR_BYTE = 2;
    private final String message;

    public ActionBarMessageImpl(String message) {
        this.message = message;
    }

    @Override
    public void send(Player player) {
        Preconditions.checkNotNull(player);
        Preconditions.checkArgument(player instanceof CraftPlayer);
        ChatComponentText chatComponentText = new ChatComponentText(getMessage());
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chatComponentText, ACTION_BAR_BYTE);
        CraftPlayer craftPlayer = (CraftPlayer) player;
        craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

    @Override
    public void send(Iterable<Player> players) {
        Preconditions.checkNotNull(players);
        for (Player player : players) {
            send(player);
        }
    }

    @Override
    public String getMessage() {
        return Objects.firstNonNull(message, "");
    }

}
