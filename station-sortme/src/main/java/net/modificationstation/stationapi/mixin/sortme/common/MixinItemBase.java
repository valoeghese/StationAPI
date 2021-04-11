package net.modificationstation.stationapi.mixin.sortme.common;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.client.event.model.ModelRegisterEvent;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.block.BlockMiningLevel;
import net.modificationstation.stationapi.api.common.event.item.ItemEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.common.item.EffectiveOnMeta;
import net.modificationstation.stationapi.api.common.item.StrengthOnMeta;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemBase.class)
public class MixinItemBase {

    @Environment(EnvType.CLIENT)
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(value = "NEW", target = "(ILnet/minecraft/item/tool/ToolMaterial;)Lnet/minecraft/item/tool/Shovel;", ordinal = 0, shift = At.Shift.BEFORE))
    private static void beforeItemRegister(CallbackInfo ci) {
        StationAPI.EVENT_BUS.post(new ModelRegisterEvent(ModelRegisterEvent.Type.ITEMS));
    }
}
