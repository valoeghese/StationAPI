/*
 * Copyright (c) 2020 The Cursed Legacy Team.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.minecraftcursedlegacy.test;

import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import io.github.minecraftcursedlegacy.api.registry.TileItems;
import io.github.minecraftcursedlegacy.api.tileentities.TileEntities;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.recipe.SmeltingRecipeRegistry;
import net.minecraft.tile.Tile;
import net.minecraft.tile.TileWithEntity;
import net.minecraft.tile.entity.TileEntity;
import net.minecraft.tile.material.Material;

public class TileEntityTest implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Hello, Fabric Tile Entities World!");
		tileWithEntity = Registries.TILE.register(new Id("modid:tile_with_entity"),
				i -> new BasicTileWithEntity(i).setName("exampleBlockWithEntity"));
		tileWithEntityItem = TileItems.registerTileItem(new Id("modid:tile_with_entity"), tileWithEntity);
		tileEntityClass = BasicTileWithEntity.BasicTileEntity.class;
		TileEntities.registerTileEntity(tileEntityClass, new Id("modid:tile_entity"));

		SmeltingRecipeRegistry.getInstance().addSmeltingRecipe(Tile.DIRT.id, new ItemInstance(tileWithEntity));
	}

	public static Tile tileWithEntity;
	public static ItemType tileWithEntityItem;
	public static Class<? extends TileEntity> tileEntityClass;

	public static class BasicTileWithEntity extends TileWithEntity {
		protected BasicTileWithEntity(int i) {
			super(i, 69, Material.DIRT);
		}

		@Override
		protected TileEntity createTileEntity() {
			return new BasicTileEntity();
		}

		static class BasicTileEntity extends TileEntity {
			@Override
			public void tick() {
				System.out.println("test tile tick!");
			}
		}
	}
}
