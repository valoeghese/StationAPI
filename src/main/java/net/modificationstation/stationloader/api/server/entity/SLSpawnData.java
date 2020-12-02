package net.modificationstation.stationloader.api.server.entity;

import com.google.common.primitives.Doubles;
import net.minecraft.entity.EntityBase;
import net.minecraft.packet.AbstractPacket;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationloader.api.common.StationLoader;
import net.modificationstation.stationloader.api.common.entity.HasOwner;
import net.modificationstation.stationloader.api.common.factory.GeneralFactory;
import net.modificationstation.stationloader.api.common.packet.CustomData;

public interface SLSpawnData extends VanillaSpawnData {

    @Override
    default AbstractPacket getSpawnData() {
        EntityBase entityBase = (EntityBase) this;
        int ownerId = 0;
        if (entityBase instanceof HasOwner) {
            EntityBase owner = ((HasOwner) entityBase).getOwner();
            owner = owner == null ? entityBase : owner;
            ownerId = owner.entityId;
        }
        CustomData packet = GeneralFactory.INSTANCE.newInst(CustomData.class, StationLoader.INSTANCE.getModID() + ":spawn_entity");
        packet.set(new String[] {
                getSpawnModID()
        });
        packet.set(new byte[] {
                (byte) getSpawnID()
        });
        packet.set(new int[] {
                entityBase.entityId,
                MathHelper.floor(entityBase.x * 32),
                MathHelper.floor(entityBase.y * 32),
                MathHelper.floor(entityBase.z * 32),
                ownerId
        });
        if (ownerId > 0) {
            double var10 = 3.9D;
            packet.set(new short[]{
                    (short) (Doubles.constrainToRange(entityBase.velocityX, -var10, var10) * 8000),
                    (short) (Doubles.constrainToRange(entityBase.velocityY, -var10, var10) * 8000),
                    (short) (Doubles.constrainToRange(entityBase.velocityZ, -var10, var10) * 8000)
            });
        }
        setCustomSpawnData(packet);
        return packet.getPacketInstance();
    }

    String getSpawnModID();

    default void setCustomSpawnData(CustomData packet) { }
}
