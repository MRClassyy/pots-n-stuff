package net.usernaem.potsnstuff.client.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientUtils {

	public static String PotAmplifierValue(int number) {
		
		switch (number) {
		case 0: {
			return "";}
		case 1: {
			return " II";}
		case 2: {
			return " III";}
		case 3: {
			return " IV";}
		case 4: {
			return " V";}
		case 5: {
			return " VI";}
		case 6: {
			return " VII";}
		case 7: {
			return " VIII";}
		case 8: {
			return " IX";}
		case 9: {
			return " X";}
		default:
			return " X+";
		}
	}
	
}
