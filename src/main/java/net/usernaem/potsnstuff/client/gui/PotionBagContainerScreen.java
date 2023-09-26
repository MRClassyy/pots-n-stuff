package net.usernaem.potsnstuff.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.usernaem.potsnstuff.PotsNStuff;
import net.usernaem.potsnstuff.common.containers.PotionBagContainer;

public class PotionBagContainerScreen extends AbstractContainerScreen<PotionBagContainer>{
	private static final ResourceLocation TEXTURE = new ResourceLocation(PotsNStuff.MOD_ID_STRING, "textures/gui/potion_bag.png");

    //private final PlayerInventory playerInventory;
    private final int inventoryRows;

    public PotionBagContainerScreen(PotionBagContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
        this.inventoryRows = screenContainer.getInventoryRows();
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
    	this.font.draw(stack, this.title, 8, 6, 0x404040);
        this.font.draw(stack, this.playerInventoryTitle, 8, this.getYSize() - 95 + 3, 0x404040);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) return;

        renderBackground(stack);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int posX = (this.width - this.getXSize()) / 2;
        int posY = (this.height - this.getYSize()) / 2;
        blit(stack, posX, posY, 0, 0, this.getXSize(), 166);
        //blit(stack, posX, posY, 0, 0, this.getXSize(), this.inventoryRows * 18 + 17);
        //blit(stack, posX, posY + this.inventoryRows * 18 + 17, 0, 126, this.getXSize(), 96);
    }

}
