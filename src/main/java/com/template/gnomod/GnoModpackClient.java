package com.template.gnomod;

import com.template.gnomod.entity.ModEntities;
import com.template.gnomod.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class GnoModpackClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.BENEE6, Benee6Renderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BENEE6, Benee6Model::getTexturedModelData);

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof GenericContainerScreen || screen instanceof ShulkerBoxScreen) {
                Screens.getButtons(screen).add(createSortButton1(scaledWidth, scaledHeight));
                Screens.getButtons(screen).add(createSortButton2(scaledWidth, scaledHeight));
                Screens.getButtons(screen).add(createSortInitializeButton(scaledWidth, scaledHeight));
                Screens.getButtons(screen).add(createSortButton3(scaledWidth, scaledHeight));
                Screens.getButtons(screen).add(createSortButton4(scaledWidth, scaledHeight));
            }
        });
    }

    @NotNull
    private ButtonWidget createSortButton1(int scaledWidth, int scaledHeight) {
        int placementCoordinateX = (scaledWidth / 100) * 5;
        int placementCoordinateY = (scaledHeight / 100) * 20;
        int buttonWidth = 100;
        int buttonHeight = 16;

        ButtonWidget.Builder buttonBuilder = ButtonWidget.builder(Text.literal("Bubble sort"), button -> {
                    PacketByteBuf packet = PacketByteBufs.empty();
                    ClientPlayNetworking.send(GnoModpack.BUBBLE_SORT_REQUEST_ID, packet);
                })
                .dimensions(placementCoordinateX, placementCoordinateY, buttonWidth, buttonHeight);

        return buttonBuilder.build();
    }

    @NotNull
    private ButtonWidget createSortButton2(int scaledWidth, int scaledHeight) {
        int placementCoordinateX = (scaledWidth / 100) * 5;
        int placementCoordinateY = (scaledHeight / 100) * 30;
        int buttonWidth = 100;
        int buttonHeight = 16;

        ButtonWidget.Builder buttonBuilder = ButtonWidget.builder(Text.literal("Insertion sort"), button -> {
                    GnoModpack.LOGGER.debug("Sort Button pressed");
                    PacketByteBuf packet = PacketByteBufs.empty();
                    ClientPlayNetworking.send(GnoModpack.INSERTION_SORT_REQUEST_ID, packet);
                })
                .dimensions(placementCoordinateX, placementCoordinateY, buttonWidth, buttonHeight);

        return buttonBuilder.build();
    }

    @NotNull
    private ButtonWidget createSortInitializeButton(int scaledWidth, int scaledHeight) {
        int placementCoordinateX = (scaledWidth / 100) * 35;
        int placementCoordinateY = (scaledHeight / 100) * 5;
        int buttonWidth = 150;
        int buttonHeight = 16;

        ButtonWidget.Builder buttonBuilder = ButtonWidget.builder(Text.literal("Merge/Quick sort initializer"), button -> {
                    PacketByteBuf packet = PacketByteBufs.empty();
                    ClientPlayNetworking.send(GnoModpack.SORT_INITIALIZATION_REQUEST_ID, packet);
                })
                .dimensions(placementCoordinateX, placementCoordinateY, buttonWidth, buttonHeight);

        return buttonBuilder.build();
    }

    @NotNull
    private ButtonWidget createSortButton3(int scaledWidth, int scaledHeight) {
        int placementCoordinateX = (scaledWidth / 100) * 5;
        int placementCoordinateY = (scaledHeight / 100) * 50;
        int buttonWidth = 100;
        int buttonHeight = 16;

        ButtonWidget.Builder buttonBuilder = ButtonWidget.builder(Text.literal("Merge sort"), button -> {
                    PacketByteBuf packet = PacketByteBufs.empty();
                    ClientPlayNetworking.send(GnoModpack.MERGE_SORT_REQUEST_ID, packet);
                })
                .dimensions(placementCoordinateX, placementCoordinateY, buttonWidth, buttonHeight);

        return buttonBuilder.build();
    }

    @NotNull
    private ButtonWidget createSortButton4(int scaledWidth, int scaledHeight) {
        int placementCoordinateX = (scaledWidth / 100) * 5;
        int placementCoordinateY = (scaledHeight / 100) * 60;
        int buttonWidth = 100;
        int buttonHeight = 16;

        ButtonWidget.Builder buttonBuilder = ButtonWidget.builder(Text.literal("Quick sort"), button -> {
                    PacketByteBuf packet = PacketByteBufs.empty();
                    ClientPlayNetworking.send(GnoModpack.QUICK_SORT_REQUEST_ID, packet);
                })
                .dimensions(placementCoordinateX, placementCoordinateY, buttonWidth, buttonHeight);

        return buttonBuilder.build();
    }

}
