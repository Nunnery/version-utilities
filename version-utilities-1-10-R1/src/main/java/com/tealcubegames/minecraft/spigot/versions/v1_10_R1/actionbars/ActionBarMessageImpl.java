/**
 * The MIT License
 * Copyright (c) 2015 Teal Cube Games
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
