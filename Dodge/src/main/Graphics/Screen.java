package main.Graphics;

public class Screen {

	public int[] pixels;
	private int width, height;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void clear(int col){
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = col;
	}
	
	public void fillRect(int x, int y, int w, int h, int col){
		for(int yy = 0; yy < h; yy++){
			int yp = yy + y;
			if(yp < 0 || yp >= height) continue;
			for(int xx = 0; xx < w; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= width) continue;
				
				pixels[xp + yp * width] = col;
			}
		}
	}
	
	public void drawRect(int x, int y, int w, int h, int col){
		for(int yy = 0; yy < h; yy++){
			int yp = yy + y;
			if(yp < 0 || yp >= height) continue;
			for(int xx = 0; xx < w; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= width) continue;
				
				if(yy == 0 || yy == h-1 || xx == 0 || xx == w-1)
					pixels[xp + yp * width] = col;
			}
		}
	}

}
