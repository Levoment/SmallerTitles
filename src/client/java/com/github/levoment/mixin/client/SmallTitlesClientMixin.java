package com.github.levoment.mixin.client;

import com.github.levoment.SmallerTitlesConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public class SmallTitlesClientMixin {
	@WrapOperation(method = "render",
	at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I", ordinal = 1))
	private int SmallerTitlesMod$changeTitleLocation(DrawContext instance, TextRenderer textRenderer, Text text, int x, int y, int color, Operation<Integer> original) {
		if (SmallerTitlesConfig.showTitles) {
			return original.call(instance, textRenderer, text, x - SmallerTitlesConfig.titleXOffset, y - SmallerTitlesConfig.titleYOffset, color);
		} else {
			// Don't draw anything
			return 0;
		}
	}

	@WrapOperation(method = "render",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", ordinal = 0))
	private void SmallerTitlesMod$changeTitleSize(MatrixStack instance, float x, float y, float z, Operation<Void> original) {
		if (SmallerTitlesConfig.showTitles) original.call(instance, SmallerTitlesConfig.titleScaleInX, SmallerTitlesConfig.titleScaleInY, SmallerTitlesConfig.titleScaleInZ);
	}

	@WrapOperation(method = "render",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I", ordinal = 2))
	private int SmallerTitleMod$changeSubtitleLocation(DrawContext instance, TextRenderer textRenderer, Text text, int x, int y, int color, Operation<Integer> original) {
		if (SmallerTitlesConfig.showSubtitles) {
			return original.call(instance, textRenderer, text, x - SmallerTitlesConfig.subtitleXOffset, y - SmallerTitlesConfig.subtitleYOffset, color);
		} else {
			// Don't draw anything
			return 0;
		}
	}

	@WrapOperation(method = "render",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", ordinal = 1))
	private void changeTextSize(MatrixStack instance, float x, float y, float z, Operation<Void> original) {
		if (SmallerTitlesConfig.showSubtitles) original.call(instance, SmallerTitlesConfig.subtitleScaleInX, SmallerTitlesConfig.subtitleScaleInY, SmallerTitlesConfig.subtitleScaleInZ);
	}
}