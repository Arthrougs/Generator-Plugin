package me.arthrougs;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class inventorySerializer {

	/*public static Inventory getInventory(Inventory inventory){
		ItemStack[] a = inventory.getContents();
		Inventory storage = Bukkit.getServer().createInventory(null, InventoryType.CHEST);
		
		for(int i = 0; i<a.length; i++){
			storage.setItem(i, a[i]);
		}
		return storage;
	}*/
	
	public static String inventoryToBase64(Inventory inventory) throws IllegalStateException{
		String content = toBase64(inventory);
		return content;
	}
	
	public static String itemStackArraytoBase64(ItemStack[] items) throws IllegalStateException{
		try{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
		
			dataOutput.writeInt(items.length);
			
			for(int i = 0; i < items.length; i++){
				dataOutput.writeObject(items[i]);
			}
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
			
		}catch(Exception e){
			 throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}
	
	public static String toBase64(Inventory inventory) {
		try{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			dataOutput.writeInt(inventory.getSize());
			
			for(int i = 0; i < inventory.getSize(); i++){
				dataOutput.writeObject(inventory.getItem(i));
			}
			
			dataOutput.close();
			
		    return Base64Coder.encodeLines(outputStream.toByteArray());
        
		} catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
	}
	
	 public static Inventory fromBase64(String data) throws IOException {
	        try {
	            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
	            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
	            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
	    
	            // Read the serialized inventory
	            for (int i = 0; i < inventory.getSize(); i++) {
	                inventory.setItem(i, (ItemStack) dataInput.readObject());
	            }
	            
	            dataInput.close();
	            return inventory;
	        } catch (ClassNotFoundException e) {
	            throw new IOException("Unable to decode class type.", e);
	        }
	    }
	 
	 public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
	    	try {
	            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
	            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
	            ItemStack[] items = new ItemStack[dataInput.readInt()];
	    
	            // Read the serialized inventory
	            for (int i = 0; i < items.length; i++) {
	            	items[i] = (ItemStack) dataInput.readObject();
	            }
	            
	            dataInput.close();
	            return items;
	        } catch (ClassNotFoundException e) {
	            throw new IOException("Unable to decode class type.", e);
	        }
	    }
}
