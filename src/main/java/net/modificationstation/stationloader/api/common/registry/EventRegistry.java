package net.modificationstation.stationloader.api.common.registry;

import net.modificationstation.stationloader.api.common.StationLoader;
import net.modificationstation.stationloader.api.common.event.Event;

public final class EventRegistry extends Registry<Event<?>> {

    private EventRegistry(Identifier identifier) {
        super(identifier);
    }

    @Override
    public int getRegistrySize() {
        return Integer.MAX_VALUE;
    }

    public static final EventRegistry INSTANCE = new EventRegistry(Identifier.of(StationLoader.INSTANCE.getModID(), "events"));
}
