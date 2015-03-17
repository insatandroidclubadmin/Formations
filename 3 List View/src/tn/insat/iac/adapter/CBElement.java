package tn.insat.iac.adapter;

public class CBElement {
	private String name;
	private int image;
	private boolean selected;
	public CBElement(String name, boolean selected,int image){
		this.name = name;
		this.selected = selected;
		this.image = image;
	}
	
	
	public int getImage() {
		return image;
	}


	public void setImage(int image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}



}
